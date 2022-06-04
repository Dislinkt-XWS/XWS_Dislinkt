import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Role, User } from 'src/app/model/user';
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
  currentUser: User;

  constructor(public authService: AuthService, public router: Router) { }

  ngOnInit(): void {
  }

  public login() {
    this.authService.login(this.username, this.password).subscribe(
      (data) => {
        console.log('response received: ');

        setTimeout(() => {
          this.authService.whoami().subscribe(data => {
            this.currentUser = data;
            console.log(this.currentUser.role)
            if (this.currentUser.role === Role.ADMIN)
              this.router.navigate(['/approve']);
            else if (this.currentUser.role === Role.OWNER)
              this.router.navigate(['/companies'])
            else
              this.router.navigate(['/register-company'])
          })
        })

      },
      (error) => {
        this.errorMessage = 'Invalid credentials';
        console.error('error caught');
      }
    );
  }

}
