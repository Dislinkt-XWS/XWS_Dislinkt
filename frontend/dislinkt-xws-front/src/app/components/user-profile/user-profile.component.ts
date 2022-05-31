import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar'
import { Experience } from 'src/app/model/experience';
import { Interest, Skill } from 'src/app/model/skill';
import { User, UserDto } from 'src/app/model/user';
import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {

  currentUser: User;
  fullName: string = "";
  experience: Experience[] = []
  education: Experience[] = []
  workExperience: Experience[] = []
  skills: Skill[]
  interests: Interest[]

  editingMode: boolean = false;
  updatedUser: UserDto

  constructor(public authService: AuthService, public userService: UserService, private matSnackBar: MatSnackBar) { }

  ngOnInit(): void {
    this.getCurrentUser();
  }

  getCurrentUser() {
    this.authService.getCurrentUser().subscribe(data => {
      this.currentUser = data;
      this.fullName = this.currentUser.fullName;

      setTimeout(() => {
        this.getExperience();
        this.userService.getSkillsForUser(this.currentUser.id).subscribe(data => this.skills = data);
        this.userService.getInterestsForUser(this.currentUser.id).subscribe(data => this.interests = data);
      }, 200)
    });
  }

  getExperience() {
    this.userService.getExperienceForUser(this.currentUser.id).subscribe(data => {
      this.experience = data;
      this.getEducation();
    });
  }

  getEducation() {
    for (let e of this.experience) {
      if (this.currentUser.workExperience.indexOf(e.id) !== -1) {
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

  logout() {
    this.authService.logout();
    window.location.href = "/"
  }
}
