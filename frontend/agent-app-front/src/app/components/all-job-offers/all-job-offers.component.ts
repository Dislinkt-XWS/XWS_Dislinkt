import { Component, OnInit } from '@angular/core';
import { JobOffer } from 'src/app/model/joboffer';
import { User } from 'src/app/model/user';
import { AuthService } from 'src/app/services/auth.service';
import { JobOfferService } from 'src/app/services/job-offer.service';

@Component({
  selector: 'app-all-job-offers',
  templateUrl: './all-job-offers.component.html',
  styleUrls: ['./all-job-offers.component.css']
})
export class AllJobOffersComponent implements OnInit {

  jobOffers: JobOffer[] = []
  currentUser: User

  constructor(public authService: AuthService, public jobOfferService: JobOfferService ) { }

  ngOnInit(): void {
    this.authService.whoami().subscribe(data => this.currentUser = data);
    this.getAllJobOffers();
  }

  
  getAllJobOffers() {
    this.jobOfferService.getAllJobOffers().subscribe(data => this.jobOffers = data);
  }

  public logOut() {
    this.authService.logOut();
    window.location.href = "/login"
  }

}
