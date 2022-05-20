import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { NewPostDto, Post } from '../model/post';

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

}
