import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { LoginDTO } from '../dtos/loginDTO.model';
import { RegistrationDTO } from '../dtos/registrationDTO.model';
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

  registration(
    username: string,
    email: string,
    password: string
  ): Observable<unknown> {
    const registrationModel: RegistrationDTO = { username, email, password };

    return this.http.post(`${apiUrl}/registration`, registrationModel, {
      observe: 'response' as 'body',
    });
  }

  googleLogin(): void {
    window.location.replace(`${apiUrl}/oauth2/authorization/google`);
  }
}
