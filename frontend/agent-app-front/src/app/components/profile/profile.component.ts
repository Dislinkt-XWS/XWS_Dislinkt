import { Component, OnInit } from '@angular/core';
import { MatCheckboxChange } from '@angular/material/checkbox';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Company } from 'src/app/model/company';
import { JobOfferDTO } from 'src/app/model/joboffer';
import { User, UserDto } from 'src/app/model/user';
import { AuthService } from 'src/app/services/auth.service';
import { CompanyService } from 'src/app/services/company.service';
import { JobOfferService } from 'src/app/services/job-offer.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  apiKey:string;
  currentUser: User;
  errorMessage!: string;

  constructor(public authService: AuthService, public matSnackBar: MatSnackBar,  public router: Router) { }

  ngOnInit(): void {
    setTimeout(() => {
      this.authService.whoami().subscribe(data => this.currentUser = data)

    });
  }
  public update(){
    this.authService.updateApiKey(this.apiKey).subscribe(
      (data) => {
        console.log('Api key updated');
        this.router.navigate(['/profile'])
      },
      (error) => {
        this.errorMessage = error.error
      }
    )
  }
  public logOut() {
    this.authService.logOut();
    window.location.href = "/login"
  }
}
