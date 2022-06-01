import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map } from 'rxjs/operators';
import { UserDto } from '../model/user';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  public login(username: string, password: string) {
    let loginDto = {
      username: username,
      password: password
    }

    return this.http.post('/api/auth/login', loginDto, { responseType: 'text' }).pipe(
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

  public signup(user: UserDto) {
    return this.http.post('/api/auth/', user, { responseType: 'text' });
  }
}
