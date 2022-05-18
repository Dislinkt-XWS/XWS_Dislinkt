import { Component, OnInit } from '@angular/core';
import { User, UserDto } from 'src/app/model/user';
import { AuthService } from 'src/app/services/auth.service';
import { PostsService } from 'src/app/services/posts.service';

@Component({
  selector: 'app-newsfeed',
  templateUrl: './newsfeed.component.html',
  styleUrls: ['./newsfeed.component.css']
})
export class NewsfeedComponent implements OnInit {
  currentUser: User;
  fullName: string = "";

  constructor(public authService: AuthService, public postsService: PostsService) { }

  ngOnInit(): void {
    setTimeout(() => {
      this.getCurrentUser();
    }, 500);
  }

  getCurrentUser() {
    this.authService.getCurrentUser().subscribe(data => {
      this.currentUser = data;
      this.fullName = this.currentUser.fullName;
    });
  }

  getFullName() {
    return this.currentUser.fullName;
  }

}
