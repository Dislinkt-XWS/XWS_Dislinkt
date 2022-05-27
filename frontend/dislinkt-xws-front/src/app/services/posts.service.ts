import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Comment, NewPostDto, Post } from '../model/post';

@Injectable({
  providedIn: 'root'
})
export class PostsService {

  constructor(private http: HttpClient) { }

  public newsfeed(): Observable<Post[]> {
    return this.http.get<Post[]>('/api/posts/newsfeed');
  }

  public createPost(newPost: FormData) {
    return this.http.post('/api/posts', newPost);
  }

  public likePost(id: string): Observable<Post> {
    return this.http.post<Post>('/api/posts/like', id);
  }

  public dislikePost(id: string): Observable<Post> {
    return this.http.post<Post>('/api/posts/dislike', id);
  }

  public unlikePost(id: string): Observable<Post> {
    return this.http.post<Post>('/api/posts/unLike', id);
  }

  public undislikePost(id: string): Observable<Post> {
    return this.http.post<Post>('/api/posts/unDislike', id);
  }

  public getCommentsByPost(): Observable<Comment[]> {
    return this.http.get<Comment[]>('api/comments');
  }

}
