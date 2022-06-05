import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JobOffer } from 'src/app/model/jobOffer';
import { User } from 'src/app/model/user';
import { JobOfferService } from 'src/app/services/job-offer.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  users: User[]
  jobOffers: JobOffer[];

  constructor(private route: ActivatedRoute, private userService: UserService, private jobOfferService: JobOfferService) { }

  ngOnInit(): void {
    let criteria = String(this.route.snapshot.paramMap.get('criteria'))
    if (criteria) {
      this.searchUsers(criteria);
      this.searchJobOffers(criteria);
    }
  }

  searchUsers(criteria: String) {
    this.userService.searchUsers(criteria).subscribe(
      users => {
        this.users = users as User[]
        console.log(this.users);
      }  
    );
  }

  searchJobOffers(criteria: String) {
    this.jobOfferService.searchJobOffers(criteria).subscribe(
      jobOffers => {
        this.jobOffers = jobOffers as JobOffer[]
        console.log(this.jobOffers);
      }  
    );
  }

}
