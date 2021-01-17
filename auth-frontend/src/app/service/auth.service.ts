import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { LoginDTO } from './loginDTO.model';
import { RegisterDTO } from './registerDTO.model';

const apiUrl = '//localhost:8080/auth';
@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(private http: HttpClient) {}

  login(
    username: string,
    password: string,
    isRememberMeEnabled: boolean
  ): void {
    const loginModel: LoginDTO = { username, password, isRememberMeEnabled };

    this.http
      .post(`${apiUrl}/login`, loginModel, {
        observe: 'response' as 'body',
      })
      .subscribe(
        (data) => {
          console.log('success');
          console.log(data);
        },
        (err: HttpErrorResponse) => {
          if (err.url) {
            console.warn(`REDIRECTING MANUALLY TO ${err.url}`);

            window.location.replace(err.url);
          }
          console.log('error');
          console.log(err);
        }
      );
  }

  register(username: string, email: string, password: string): void {
    const registerModel: RegisterDTO = { username, email, password };

    this.http
      .post(`${apiUrl}/register`, registerModel, {
        observe: 'response' as 'body',
      })
      .subscribe(
        (data) => {
          console.log('success');
          console.log(data);
        },
        (err: HttpErrorResponse) => {
          // if (err.url) {
          //   console.warn(`REDIRECTING MANUALLY TO ${err.url}`);

          //   window.location.replace(err.url);
          // }
          console.log('error');
          console.log(err);
        }
      );
  }

  googleLogin(): void {
    window.location.replace(`${apiUrl}/oauth2/authorization/google`);
  }
}
