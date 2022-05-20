import { animate, style, transition, trigger } from '@angular/animations';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserDto, UserGender } from 'src/app/model/user';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css'],
  animations: [
    trigger('slideInOut', [
      transition(':enter', [style({ opacity: 0 }), animate(600)]),
      transition(':leave', [animate(600, style({ opacity: 0 }))])
    ])
  ]
})
export class RegistrationComponent implements OnInit {

  visible: boolean;
  errorMessage = "";

  password: string
  username: string;
  email: string;
  phoneNumber: string;
  userGender: UserGender;
  firstName: string;
  lastName: string;
  dateOfBirth: Date;
  bio: string;
  isPrivate: boolean;

  constructor(public authService: AuthService, private router: Router) { }

  ngOnInit(): void {
    this.visible = false;
  }

  public signup() {
    let user: UserDto = {
      password: this.password,
      username: this.username,
      email: this.email,
      phoneNumber: this.phoneNumber,
      userGender: this.userGender,
      fullName: this.firstName + " " + this.lastName,
      dateOfBirth: new Date(this.dateOfBirth),
      bio: this.bio,
      isPrivate: this.isPrivate
    }

    this.authService.signup(user).subscribe(
      (data) => {
        console.log('response received: ');
        this.router.navigate(['/']);
      },
      (error) => {
        this.errorMessage = error.error
      }
    );
  }

}
