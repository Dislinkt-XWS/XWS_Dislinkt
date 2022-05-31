import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Experience } from '../model/experience';
import { Interest, Skill } from '../model/skill';
import { UserDto } from '../model/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  public getExperienceForUser(id: string): Observable<Experience[]> {
    return this.http.get<Experience[]>('api/experiences/user/' + id);
  }

  public getSkillsForUser(id: string): Observable<Skill[]> {
    return this.http.get<Skill[]>('api/skills/user/' + id);
  }

  public getInterestsForUser(id: string): Observable<Interest[]> {
    return this.http.get<Interest[]>('api/interests/user/' + id);
  }

  public updateProfile(user: UserDto) {
    return this.http.put('api/users', user);
  }
}
