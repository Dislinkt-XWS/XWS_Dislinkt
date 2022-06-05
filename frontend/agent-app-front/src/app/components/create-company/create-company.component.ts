import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { CompanyDto } from 'src/app/model/company';
import { User } from 'src/app/model/user';
import { AuthService } from 'src/app/services/auth.service';
import { CompanyService } from 'src/app/services/company.service';

@Component({
  selector: 'app-create-company',
  templateUrl: './create-company.component.html',
  styleUrls: ['./create-company.component.css']
})
export class CreateCompanyComponent implements OnInit {

  name: string;
  description: string;
  yearOfEstablishment: number;
  email: string;
  phoneNumber: string;
  address: string;
  city: string;
  country: string;
  currentUser: User

  constructor(public companyService: CompanyService, public authService: AuthService, public matSnackBar: MatSnackBar) { }

  ngOnInit(): void {
    setTimeout(() => this.authService.whoami().subscribe(data => this.currentUser = data));
  }

  public create() {
    let dto: CompanyDto = {
      ownerId: this.currentUser.id,
      name: this.name,
      description: this.description,
      yearOfEstablishment: this.yearOfEstablishment,
      email: this.email,
      phoneNumber: this.phoneNumber,
      address: this.address,
      city: this.city,
      country: this.country,
    };

    this.companyService.createComapny(dto).subscribe(
      (data) => {
        this.matSnackBar.open("Company registration request successfully sent!", "Dismiss", {
          duration: 1000
        });
      },
      (error) => {
        this.matSnackBar.open(error.error, "Dissmiss", {
          duration: 1000
        })
      }
    )
  }

  public logOut() {
    this.authService.logOut();
    window.location.href = "/login"
  }

}
