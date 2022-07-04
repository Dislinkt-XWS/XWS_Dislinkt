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

  public addEducation(experience: any) {
    return this.http.post('api/experiences/education', experience);
  }

  public addWork(work: any) {
    return this.http.post('api/experiences/work', work);
  }

  public updateExperience(experience: any) {
    return this.http.put('api/experiences', experience);
  }

  public removeEducation(id: string) {
    return this.http.delete('api/experiences/education/' + id);
  }

  public removeWork(id: string) {
    return this.http.delete('api/experiences/work/' + id);
  }

  public addSkill(skill: any) {
    return this.http.post('api/skills', skill);
  }

  public removeSkill(id: string) {
    return this.http.delete('api/skills/' + id);
  }

  public addInterest(interest: any) {
    return this.http.post('api/interests', interest);
  }

  public removeInterest(id: string) {
    return this.http.delete('api/interests/' + id);
  }

  public follow(userId: string, userToFollow: string | null) {
    let dto = {
      "userId": userId,
      "userToFollowId": userToFollow
    }

    return this.http.post('api/users/follow', dto);
  }

  public searchUsers(criteria: String) {
    return this.http.get('api/users/search/' + criteria);
  }

  public approveFollow(id: string, userToApprove: string) {
    let dto = {
      "userId": id,
      "userToApproveIds": userToApprove
    }

    return this.http.post('api/users/follow/approve', dto);
  }

  public unfollow(userId: string, userToUnfollow: string | null) {
    let dto = {
      "userId": userId,
      "userToUnfollowId": userToUnfollow
    }

    return this.http.post('api/users/unfollow', dto);
  }

  public searchAllUsers(criteria: String) {
    return this.http.get('api/users/search-all/' + criteria);
  }

  public getNotificationsForUser(id: string): Observable<Notification[]> {
    return this.http.get<Notification[]>('/api/notifications/' + id);
  }
}
