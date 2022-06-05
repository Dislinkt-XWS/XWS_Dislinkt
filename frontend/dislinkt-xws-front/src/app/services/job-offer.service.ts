import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { JobOffer, JobOfferDTO } from '../model/jobOffer';

@Injectable({
  providedIn: 'root'
})
export class JobOfferService {

  constructor(private http: HttpClient) { }

  public searchJobOffers(criteria: String) {
    return this.http.get('api/joboffers/search/' + criteria);
  }

  public createJobOffer(dto: JobOfferDTO) {
    return this.http.post('api/joboffers', dto);
  }

  public getAllJobOffers(): Observable<JobOffer[]>{
    return this.http.get<JobOffer[]>('api/joboffers');
  }
}
