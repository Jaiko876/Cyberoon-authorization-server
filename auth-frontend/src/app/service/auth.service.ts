import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';

const apiUrl = '//localhost:8080/auth';
@Injectable({
  providedIn: 'root',
})
export class AuthService {
  constructor(private http: HttpClient) {}

  login(username: string, password: string, rememberMe?: string): void {
    console.log('login post called');
    const formData = new FormData();
    formData.append('username', username);
    formData.append('password', password);
    formData.append('remember-me', 'on');

    this.http
      .post(`${apiUrl}/login`, formData, {
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
