import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class JobOfferService {

  constructor(private http: HttpClient) { }

  public searchJobOffers(criteria: String) {
    return this.http.get('api/joboffers/search/' + criteria);
  }
}
