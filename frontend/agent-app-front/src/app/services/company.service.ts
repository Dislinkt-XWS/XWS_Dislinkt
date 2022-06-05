import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Company, CompanyDto, Status } from '../model/company';
import { Experience } from '../model/experience';

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

  public getAllCompanies(): Observable<Company[]> {
    return this.http.get<Company[]>('api/companies/');
  }

  public getCompanyById(id: string | null): Observable<Company> {
    return this.http.get<Company>('api/companies/' + id);
  }

  public getCompaniesByOwner(): Observable<Company[]> {
    return this.http.get<Company[]>('api/companies/user')
  }

  public findAllComments(id: string | null): Observable<Experience[]> {
    return this.http.get<Experience[]>('api/experience/companies/' + id + '/comments')
  }

  public findAllSalaries(id: string | null): Observable<Experience[]> {
    return this.http.get<Experience[]>('api/experience/companies/' + id + '/salaries')
  }

  public findAllInterviews(id: string | null): Observable<Experience[]> {
    return this.http.get<Experience[]>('api/experience/companies/' + id + '/interviews')
  }

  public comment(comment: any) {
    return this.http.post('api/experience', comment);
  }
}
