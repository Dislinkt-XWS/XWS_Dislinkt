import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Experience } from '../model/experience';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  public getExperienceForUser(id: string): Observable<Experience[]> {
    return this.http.get<Experience[]>('api/experiences/user/' + id);
  }
}
