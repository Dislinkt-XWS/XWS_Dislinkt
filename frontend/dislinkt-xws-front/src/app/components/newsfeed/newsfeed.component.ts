import { Component, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { NewPostDto, Post } from 'src/app/model/post';
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
  posts: Post[];

  postContent: string;
  imagePath: File;
  formData: FormData;

  constructor(public authService: AuthService, public postsService: PostsService, private sanitizer: DomSanitizer) { }

  ngOnInit(): void {
    setTimeout(() => {
      this.getCurrentUser();
      this.getNewsfeed();
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

  getNewsfeed() {
    this.postsService.newsfeed().subscribe(data => this.posts = data);
  }

  createPost() {
    this.postsService.createPost(this.formData).subscribe(
      (data) => {
        console.log('uspeh');
        this.getNewsfeed();
      },
      (error) => {
        console.error('nesto ne valja');
      }
    )
  }

  selectedFiles(event: any) {
    this.formData = new FormData();

    this.imagePath = event.target.files.item(0);
    this.formData.append('image', this.imagePath);
    this.formData.append('textContent', this.postContent);
  }

  getPhoto(post: Post) {
    return this.sanitizer.bypassSecurityTrustUrl('data:image/jpeg;base64,' + post.imagePath);
  }

}
