import { Component, OnInit } from '@angular/core';
import { FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Role, UserDto } from 'src/app/model/user';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  errorMessage!: string
  firstName!: string
  lastName!: string
  username!: string
  password!: string
  email!: string

  checkEmail = new FormControl('', [Validators.email]);
  checkFirstName = new FormControl('', [Validators.required]);
  checkLastName = new FormControl('', [Validators.required]);
  checkUsername = new FormControl('', [Validators.required]);
  checkPassword = new FormControl('', [Validators.required]);

  constructor(public authService: AuthService, public router: Router) { }

  ngOnInit(): void {
  }

  isButtonDisabled() {
    if (this.checkEmail.invalid || this.checkFirstName.invalid || this.checkLastName.invalid
      || this.checkPassword.invalid || this.checkUsername.invalid)
      return true;

    return false;
  }

  public signup() {
    let user: UserDto = {
      password: this.password,
      username: this.username,
      email: this.email,
      firstName: this.firstName,
      lastName: this.lastName
    }

    this.authService.signup(user).subscribe(
      (data) => {
        console.log('response received: ');
        this.router.navigate(['/login']);
      },
      (error) => {
        this.errorMessage = error.error
      }
    );
  }

}
