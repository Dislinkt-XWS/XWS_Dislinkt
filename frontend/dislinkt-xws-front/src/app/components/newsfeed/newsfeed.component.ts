import { Component, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { Router } from '@angular/router';
import { Comment, NewPostDto, Post } from 'src/app/model/post';
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

  comments: Comment[] = [];
  commentContent: string;

  postContent: string;
  imagePath: File;
  formData: FormData = new FormData();
  allUsers: User[];

  constructor(public authService: AuthService, public postsService: PostsService, private sanitizer: DomSanitizer, private router: Router) { }

  ngOnInit(): void {
    setTimeout(() => {
      this.getCurrentUser();
      this.getNewsfeed();
    }, 300);

    this.authService.getAllUsers().subscribe(data => this.allUsers = data);
    this.postsService.getComments().subscribe(data => this.comments = data);
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
    this.postsService.newsfeed().subscribe(data => this.posts = data.sort((a, b) => new Date(b.datePosted).getTime() - new Date(a.datePosted).getTime()));
  }

  createPost() {
    if (this.imagePath !== undefined)
      this.formData.append('image', this.imagePath);

    this.formData.append('textContent', this.postContent);

    this.postsService.createPost(this.formData).subscribe(
      (data) => {
        console.log('uspeh');
        this.getNewsfeed();
        this.postContent = "";
      },
      (error) => {
        console.error('nesto ne valja');
      }
    )
  }

  selectedFiles(event: any) {
    this.formData = new FormData();
    this.imagePath = event.target.files.item(0);
  }

  getPhoto(post: Post) {
    return this.sanitizer.bypassSecurityTrustUrl('data:image/jpeg;base64,' + post.imagePath);
  }

  getUserById(userId: string) {
    for (let u of this.allUsers) {
      if (userId === u.id)
        return u.fullName;
    }

    return "";
  }

  countLikes(post: Post) {
    return post.userLikes.length;
  }

  likePost(post: Post) {
    if (post.userLikes.indexOf(this.currentUser.id) === -1) {
      this.postsService.likePost(post.id).subscribe(data => this.ngOnInit()); //promijeni ako skontas
    }
    else
      this.postsService.unlikePost(post.id).subscribe(data => this.ngOnInit());
  }

  dislikePost(post: Post) {
    if (post.userLikes.indexOf(this.currentUser.id) === -1) {
      this.postsService.dislikePost(post.id).subscribe(data => this.ngOnInit());
    }
    else
      this.postsService.undislikePost(post.id).subscribe(data => this.ngOnInit());
  }

  alreadyLiked(post: Post) {
    if (post.userLikes.indexOf(this.currentUser.id) === -1) {
      return false;
    }

    return true;
  }

  alreadyDisliked(post: Post) {
    if (post.userDislikes.indexOf(this.currentUser.id) === -1) {
      return false;
    }

    return true;
  }

  getComments(postId: string) {
    let foundComments: Comment[] = []
    for (let comment of this.comments) {
      if (comment.postId === postId)
        foundComments.push(comment);
    }

    return foundComments.sort((a, b) => new Date(b.datePosted).getTime() - new Date(a.datePosted).getTime());
  }

  comment(postId: string) {
    let commentDto = {
      userId: this.currentUser.id,
      textContent: this.commentContent,
      postId: postId
    }

    this.postsService.comment(commentDto).subscribe(data => {
      this.ngOnInit();
      this.commentContent = "";
    })
  }

}
