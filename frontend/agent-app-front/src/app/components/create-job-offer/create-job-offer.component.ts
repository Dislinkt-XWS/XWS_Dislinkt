import { Component, OnInit } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Company } from 'src/app/model/company';
import { JobOfferDTO } from 'src/app/model/joboffer';
import { User } from 'src/app/model/user';
import { AuthService } from 'src/app/services/auth.service';
import { CompanyService } from 'src/app/services/company.service';
import { JobOfferService } from 'src/app/services/job-offer.service';

@Component({
  selector: 'app-create-job-offer',
  templateUrl: './create-job-offer.component.html',
  styleUrls: ['./create-job-offer.component.css']
})
export class CreateJobOfferComponent implements OnInit {

  
  position: string;
  jobDescription: string;
  requirements: string;
  isPublished: boolean;
  isPublishedString: string;
  companyId: string;
  companyForOffer: Company;
  myCompanies: any[];
  currentUser: User

  constructor(public jobOfferService: JobOfferService, public companyService: CompanyService, public authService: AuthService, public matSnackBar: MatSnackBar) { }

  ngOnInit(): void {
    setTimeout(() => {
      this.authService.whoami().subscribe(data => this.currentUser = data)
      this.companyService.getAllCompanies().subscribe(data => this.myCompanies = data)
    });
  }

  public create() {
    if (this.isPublishedString == "Yes") {
      this.isPublished = true;
    }
    else {
      this.isPublished = false;
    }

    let dto: JobOfferDTO = {
      position: this.position,
      requirements: this.requirements,
      jobDescription: this.jobDescription,
      publisherId: this.currentUser.id,
      isPublished: this.isPublished,
      companyId: this.companyForOffer.id
    };
 
    this.jobOfferService.createJobOffer(dto).subscribe(
      (data) => {
        this.matSnackBar.open("Job offer registration sucessful!", "Dismiss", {
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
