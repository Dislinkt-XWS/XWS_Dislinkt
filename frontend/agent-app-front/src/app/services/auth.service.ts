import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { User, UserDto } from '../model/user';

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
  public updateApiKey(apiKey: string){
    return this.http.put('/api/auth/apikey', apiKey, { responseType: 'text' } )
  }
  public logOut(): void {
    localStorage.removeItem('jwt');
  }

  public signup(user: UserDto) {
    return this.http.post('/api/auth/', user, { responseType: 'text' });
  }

  public whoami(): Observable<User> {
    return this.http.get<User>('/api/auth/current-user');
  }
}
