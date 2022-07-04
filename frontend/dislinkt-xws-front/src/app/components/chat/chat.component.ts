import { Component, OnInit } from '@angular/core';
import { Message } from 'src/app/model/message';
import { User } from 'src/app/model/user';
import { AuthService } from 'src/app/services/auth.service';
import { MessageService } from 'src/app/services/message.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-chat',
  templateUrl: './chat.component.html',
  styleUrls: ['./chat.component.css']
})
export class ChatComponent implements OnInit {

  constructor(private authService: AuthService, private userService : UserService, private messageService : MessageService) { }
  users: User[] = [];
  currentUser: User;
  chatUser: User;
  messages: Message[] = [];
  message = new Message('', '', '', '', null);

  ngOnInit(): void {
    this.getCurrentUser();
    this.userService.getChatUsers().subscribe(data => {
      this.users = data
      if(this.users.length > 0) {
        this.chatUser = this.users[0];
        this.getChat();
      }
    });
  }

  openChat(user: User) {
    this.chatUser = user;
    this.getChat();
  }

  getChat() {
    this.messageService.getChat(this.currentUser.id, this.chatUser.id).subscribe(data => this.messages = data);
  }

  getCurrentUser() {
    this.authService.getCurrentUser().subscribe(data => this.currentUser = data);
  }

  sendMessage() {
    this.message.senderId = this.currentUser.id;
    this.message.receiverId = this.chatUser.id;
    this.messageService.sendMessage(this.message).subscribe(() => this.getChat());
  }
}
