import { Component, OnInit } from '@angular/core';
import { MatCheckboxChange } from '@angular/material/checkbox';
import { MatSnackBar } from '@angular/material/snack-bar';
import { JobOfferDTO } from 'src/app/model/jobOffer';
import { User } from 'src/app/model/user';
import { AuthService } from 'src/app/services/auth.service';
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
  myCompanies: any[];
  currentUser: User


  constructor(public authService: AuthService, public jobOfferService: JobOfferService, public matSnackBar: MatSnackBar) { }

  ngOnInit(): void {
    setTimeout(() => {
      this.getCurrentUser();
    }, 300);
  }

  getCurrentUser() {
    this.authService.getCurrentUser().subscribe(data => this.currentUser = data);
  }

  public create() {
    if (this.isPublishedString == "Yes") {
      this.isPublished = true;
    }
    else {
      this.isPublished = false;
    }

    //TODO: ID kompanije ispravno postaviti 

    let dto: JobOfferDTO = {
      id: "",
      position: this.position,
      requirements: this.requirements,
      jobDescription: this.jobDescription,
      publisherId: this.currentUser.id,
      isPublished: this.isPublished,
      companyId: ""  
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
  
  public isPublishedCheck(arg: MatCheckboxChange) {
    this.isPublished = arg.checked;
  }
}
