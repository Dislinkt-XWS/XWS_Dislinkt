import { Component, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { User } from 'src/app/model/user';
import { Notification } from 'src/app/model/notification';
import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-notifications',
  templateUrl: './notifications.component.html',
  styleUrls: ['./notifications.component.css']
})
export class NotificationsComponent implements OnInit {

  currentUser: User;
  fullName: string = "";
  notifications: any[];

  constructor(public authService: AuthService, public userService: UserService, private sanitizer: DomSanitizer, private router: Router) { }

  ngOnInit(): void {
    setTimeout(() => {
      this.getCurrentUser();
    }, 300);
  }

  getCurrentUser() {
    this.authService.getCurrentUser().subscribe(data => {
      this.currentUser = data;
      this.fullName = this.currentUser.fullName;
      this.getNotifications();
    });
  }

  getNotifications() {
    this.userService.getNotificationsForUser(this.currentUser.id).subscribe(data => this.notifications = data);
  }

}
