import { Component, OnInit } from '@angular/core';
import { JobOffer } from 'src/app/model/jobOffer';
import { User } from 'src/app/model/user';
import { AuthService } from 'src/app/services/auth.service';
import { JobOfferService } from 'src/app/services/job-offer.service';

@Component({
  selector: 'app-job-offers',
  templateUrl: './job-offers.component.html',
  styleUrls: ['./job-offers.component.css']
})
export class JobOffersComponent implements OnInit {

  jobOffers: JobOffer[] = []
  currentUser: User

  constructor(public authService: AuthService, public jobOfferService: JobOfferService ) { }

  ngOnInit(): void {
    setTimeout(() => {
      this.getCurrentUser();
    }, 300);

    this.getAllJobOffers();
  }

  getCurrentUser() {
    this.authService.getCurrentUser().subscribe(data => this.currentUser = data);
  }

  getAllJobOffers() {
    this.jobOfferService.getAllJobOffers().subscribe(data => this.jobOffers = data);
  }

}
