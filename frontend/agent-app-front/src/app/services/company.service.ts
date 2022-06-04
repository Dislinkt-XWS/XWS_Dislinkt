import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Company, CompanyDto, Status } from '../model/company';

@Injectable({
  providedIn: 'root'
})
export class CompanyService {

  constructor(public http: HttpClient) { }

  public createComapny(dto: CompanyDto) {
    return this.http.post('api/companies', dto);
  }

  public update(company: Company) {
    return this.http.put('api/companies', company);
  }

  public getAllPending(): Observable<Company[]> {
    return this.http.get<Company[]>('api/companies/pending');
  }

  public approveCompany(id: string, status: Status) {
    return this.http.put('api/companies/' + id, status);
  }
}
