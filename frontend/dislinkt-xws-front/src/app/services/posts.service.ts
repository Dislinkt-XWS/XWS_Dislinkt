import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Comment, NewPostDto, Post } from '../model/post';

@Injectable({
  providedIn: 'root'
})
export class PostsService {

  constructor(private http: HttpClient) { }

  public newsfeed(id: string): Observable<Post[]> {
    return this.http.get<Post[]>('/api/posts/newsfeed/' + id);
  }

  public createPost(newPost: FormData) {
    return this.http.post('/api/posts', newPost);
  }

  public likePost(id: string, userId: string): Observable<Post> {
    let dto = {
      "userId": userId,
      "postId": id
    }
    return this.http.post<Post>('/api/posts/like', dto);
  }

  public dislikePost(id: string, userId: string): Observable<Post> {
    let dto = {
      "userId": userId,
      "postId": id
    }
    return this.http.post<Post>('/api/posts/dislike', dto);
  }

  public unlikePost(id: string, userId: string): Observable<Post> {
    let dto = {
      "userId": userId,
      "postId": id
    }
    return this.http.post<Post>('/api/posts/unLike', dto);
  }

  public undislikePost(id: string, userId: string): Observable<Post> {
    let dto = {
      "userId": userId,
      "postId": id
    }
    return this.http.post<Post>('/api/posts/unDislike', dto);
  }

  public getComments(): Observable<Comment[]> {
    return this.http.get<Comment[]>('api/comments');
  }

  public comment(commentDto: any) {
    return this.http.post('api/comments', commentDto);
  }
}
