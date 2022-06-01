import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  hide = true;
  username!: string;
  password!: string;
  errorMessage!: string;

  constructor(public authService: AuthService, public router: Router) { }

  ngOnInit(): void {
  }

  public login() {
    this.authService.login(this.username, this.password).subscribe(
      (data) => {
        console.log('response received: ');
        this.router.navigate(['/']);
      },
      (error) => {
        this.errorMessage = 'Invalid credentials';
        console.error('error caught');
      }
    );
  }

}
