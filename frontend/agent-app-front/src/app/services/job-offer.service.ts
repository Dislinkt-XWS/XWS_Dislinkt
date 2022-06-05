import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { JobOffer, JobOfferDTO } from '../model/joboffer';

@Injectable({
  providedIn: 'root'
})
export class JobOfferService {

  constructor(public http: HttpClient) { }

  public createJobOffer(dto: JobOfferDTO) {
    return this.http.post('api/job-offers', dto);
  }

  public getAllJobOffers(): Observable<JobOffer[]>{
    return this.http.get<JobOffer[]>('api/job-offers/');
  }
}
