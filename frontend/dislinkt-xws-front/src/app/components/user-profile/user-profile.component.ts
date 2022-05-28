import { Component, OnInit } from '@angular/core';
import { Experience } from 'src/app/model/experience';
import { User } from 'src/app/model/user';
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
  experience: Experience[]

  constructor(public authService: AuthService, public userService: UserService) { }

  ngOnInit(): void {
    this.getCurrentUser();
  }

  getCurrentUser() {
    this.authService.getCurrentUser().subscribe(data => {
      this.currentUser = data;
      this.fullName = this.currentUser.fullName;

      setTimeout(() => {
        this.getExperience();
      }, 200)
    });
  }

  getExperience() {
    this.userService.getExperienceForUser(this.currentUser.id).subscribe(data => this.experience = data);
  }

}
