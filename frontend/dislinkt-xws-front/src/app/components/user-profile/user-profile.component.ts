import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar'
import { Experience } from 'src/app/model/experience';
import { Interest, Skill } from 'src/app/model/skill';
import { User, UserDto } from 'src/app/model/user';
import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/user.service';
import { COMMA, ENTER } from '@angular/cdk/keycodes';
import { MatChipInputEvent } from '@angular/material/chips';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  currentUser: User;
  fullName: string = "";
  fullNameNav: string = "";
  experience: Experience[] = []
  education: Experience[] = []
  workExperience: Experience[] = []
  skills: Skill[]
  interests: Interest[]
  apitoken: string | null;
  editingMode: boolean = false;
  updatedUser: UserDto

  startExp: Date
  endExp: Date
  expName: string
  expRole: string
  editEducation: boolean = false
  editWork: boolean = false

  userId: string;
  userFromId: User
  followRequests: User[] = []

  addOnBlur = true;
  readonly separatorKeysCodes = [ENTER, COMMA] as const;

  notFound: boolean = false;

  constructor(public authService: AuthService, public userService: UserService, private matSnackBar: MatSnackBar,
    public activatedRoute: ActivatedRoute) { }

  ngOnInit(): void {
    this.userId = this.activatedRoute.snapshot.paramMap.get('id') as string;
    this.getCurrentUser();
    this.getUserProfile();
  }

  showButton() {
    return this.currentUser.id != this.userId && this.userFromId.followers.indexOf(this.currentUser.id) === -1 &&
      this.userFromId.followRequests.indexOf(this.currentUser.id) === -1
  }

  private getUserProfile() {
    this.authService.getUserById(this.userId).subscribe(data => {
      this.userFromId = data;
      this.fullName = this.userFromId.fullName;

      setTimeout(() => {
        this.showProfile();
        this.getExperience();
        this.userService.getSkillsForUser(this.userId).subscribe(data => this.skills = data);
        this.userService.getInterestsForUser(this.userId).subscribe(data => this.interests = data);
        this.showButton();
        this.showUnfollow();
      }, 200);
    });
  }

  getCurrentUser() {
    this.authService.getCurrentUser().subscribe(data => {
      this.currentUser = data;
      this.fullNameNav = this.currentUser.fullName
      this.getFollowRequests();
    });
  }

  getExperience() {
    this.userService.getExperienceForUser(this.userId).subscribe(data => {
      this.experience = data;
      this.getEducation();
    });
  }

  getEducation() {
    this.workExperience = []
    this.education = []
    for (let e of this.experience) {
      if (this.userFromId.workExperience.indexOf(e.id) !== -1) {
        this.workExperience.push(e)
      }
      else
        this.education.push(e)
    }
  }

  updateProfile() {
    this.userService.updateProfile(this.currentUser).subscribe(data => {
      this.fullName = this.currentUser.fullName;
      window.location.reload();
    },
      (error) => {
        this.matSnackBar.open(error.error, 'Dissmiss', {
          duration: 2000
        })
      });
  }

  addSkill(event: MatChipInputEvent): void {
    const value = (event.value || '').trim();

    if (value) {
      this.userService.addSkill({ name: value }).subscribe(data => {
        this.userService.getSkillsForUser(this.userId).subscribe(data => this.skills = data);
      })
    }
    event.chipInput!.clear();
  }

  removeSkill(skill: Skill): void {
    this.userService.removeSkill(skill.id).subscribe();

    setTimeout(() => {
      this.userService.getSkillsForUser(this.userId).subscribe(data => this.skills = data);
    }, 200)
  }

  addInterest(event: MatChipInputEvent): void {
    const value = (event.value || '').trim();

    if (value) {
      this.userService.addInterest({ name: value }).subscribe(data => {
        this.userService.getInterestsForUser(this.userId).subscribe(data => this.interests = data);
      })
    }
    event.chipInput!.clear();
  }

  removeInterest(skill: Skill): void {
    this.userService.removeInterest(skill.id).subscribe();

    setTimeout(() => {
      this.userService.getInterestsForUser(this.userId).subscribe(data => this.interests = data);
    }, 200)
  }

  addExperience() {
    let experience = {
      userId: this.userId,
      establishmentName: this.expName,
      start: this.startExp,
      end: this.endExp
    }
    this.userService.addEducation(experience).subscribe(data => window.location.reload())
  }

  addWork() {
    let experience = {
      userId: this.userId,
      establishmentName: this.expName,
      start: this.startExp,
      end: this.endExp,
      role: this.expRole
    }
    this.userService.addWork(experience).subscribe(data => window.location.reload())
  }

  updateExp(experience: Experience) {
    this.userService.updateExperience(experience).subscribe(data => { this.editEducation = false; this.editWork = false });
  }

  deleteExp(experience: Experience) {
    this.userService.removeWork(experience.id).subscribe(data => {
      this.getExperience();
      this.editWork = false;
    })
  }

  deleteEducation(experience: Experience) {
    this.userService.removeEducation(experience.id).subscribe(data => {
      this.getEducation();
      this.editEducation = false;
    })
  }

  followUser() {
    this.userService.follow(this.currentUser.id, this.userId).subscribe(data => window.location.reload());
  }

  generateApiToken() {
    this.authService.generateApiToken().subscribe(
      (data) => this.apitoken = data);
  }

  isPrivate() {
    if (this.userId != this.currentUser.id && this.userFromId.isPrivate &&
      this.userFromId.followers.indexOf(this.currentUser.id) === -1)
      return true;

    return false;
  }

  getFollowRequests() {
    let allUsers: User[] = [];
    this.authService.getAllUsers().subscribe(data => {
      allUsers = data;
      for (let id of this.currentUser.followRequests) {
        for (let user of allUsers) {
          if (id === user.id)
            this.followRequests.push(user);
        }
      }
    });
  }

  approveFollow(id: string) {
    this.userService.approveFollow(this.currentUser.id, id).subscribe(data => this.getFollowRequests());
  }

  unfollowUser() {
    this.userService.unfollow(this.currentUser.id, this.userId).subscribe(data => window.location.reload());
  }

  showUnfollow() {
    return this.currentUser.id != this.userId && this.userFromId.followers.indexOf(this.currentUser.id) !== -1
  }

  showBlocked() {
    return this.currentUser.id != this.userId
  }

  show() {
    return this.currentUser.id != this.userId && this.userFromId.followRequests.indexOf(this.currentUser.id) !== -1
  }

  blockUser() {
    this.userService.block(this.currentUser.id, this.userId).subscribe(data => window.location.reload());
  }

  showProfile() {
    console.log(this.currentUser)
    if (this.currentUser.blockedUsers.indexOf(this.userId) !== -1)
      this.notFound = true;
  }
}
