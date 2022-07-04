import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Message } from '../model/message';

@Injectable({
  providedIn: 'root'
})
export class MessageService {

  constructor(private http: HttpClient) { }

  public getChat(user1: string, user2: string) : Observable<Message[]> {
    return this.http.get<Message[]>('api/messages/chat/' + user1 + '/' + user2);
  }
}
