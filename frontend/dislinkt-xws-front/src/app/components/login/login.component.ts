import { animate, style, transition, trigger } from '@angular/animations';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css'],
  animations: [
    trigger('slideInOut', [
      transition(':enter', [style({ opacity: 0 }), animate(600)]),
      transition(':leave', [animate(600, style({ opacity: 0 }))])
    ])
  ]
})
export class LoginComponent implements OnInit {

  username: string;
  password: string;

  errorMessage = "";
  constructor(public authService: AuthService, private router: Router) { }

  ngOnInit(): void {
  }

  public login() {
    this.authService.login(this.username, this.password).subscribe(
      (data) => {
        console.log('response received: ');
        this.router.navigate(['/feed']);
      },
      (error) => {
        this.errorMessage = 'Invalid credentials';
        console.error('error caught');
      }
    );
  }

}
