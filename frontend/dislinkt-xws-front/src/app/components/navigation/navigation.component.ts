import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from 'src/app/model/user';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {

  currentUser: User;
  fullName: string = "";

  constructor(private router: Router, public authService: AuthService) { }

  ngOnInit(): void {
    setTimeout(() => {
      this.getCurrentUser();
    }, 300);
  }

  getCurrentUser() {
    this.authService.getCurrentUser().subscribe(data => {
      this.currentUser = data;
      this.fullName = this.currentUser.fullName;
    });
  }

  search(input: any) {
    if(input.value.length > 0) {
      //this.router.navigate(['search', input.value]);
      window.location.href = '/search/' + input.value;
    }
  }

  openProfile() {
    this.router.navigate(['profile', this.currentUser.id]);
  }

  logout() {
    this.authService.logout();
    this.router.navigate(['']);
  }

}
