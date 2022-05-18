import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';
import { environment } from 'src/environments/environment';
import { User } from '../model/user';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  public login(username: string, password: string) {
    let loginDto = {
      usernameOrEmail: username,
      password: password
    }

    return this.http.post('/api/users/login', loginDto, { responseType: 'text' }).pipe(
      map((res: any) => {
        console.log('Login success');
        localStorage.setItem('jwt', res);
        console.log(res);
      })
    );
  }

  public logOut(): void {
    localStorage.removeItem('jwt');
  }

  public signup(user: User) {
    return this.http.post('/api/users', user, { responseType: 'text' });
  }
}
