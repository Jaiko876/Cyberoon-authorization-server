import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { LoginDTO } from './loginDTO.model';
import { RegistrationDTO as registrationDTO } from './registrationDTO.model';
import { Observable } from 'rxjs';

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
  ): Observable<unknown> {
    const loginModel: LoginDTO = { username, password, isRememberMeEnabled };

    return this.http.post(`${apiUrl}/login`, loginModel, {
      observe: 'response' as 'body',
    });
  }

  registration(username: string, email: string, password: string): void {
    const registrationModel: registrationDTO = { username, email, password };

    this.http
      .post(`${apiUrl}/registration`, registrationModel, {
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

  googleLogin(): void {
    window.location.replace(`${apiUrl}/oauth2/authorization/google`);
  }
}
