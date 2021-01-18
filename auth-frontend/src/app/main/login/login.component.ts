import { HttpErrorResponse } from '@angular/common/http';
import { ChangeDetectionStrategy, Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { catchError } from 'rxjs/operators';

import { AuthService } from '../../service/auth.service';
import { BehaviorSubject, of, ReplaySubject } from 'rxjs';
import { ValidationErrors } from 'src/app/service/validationErrors.model';

enum FormControlName {
  name = 'name',
  password = 'password',
  rememberMe = 'rememberMe',
}
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class LoginComponent implements OnInit {
  readonly formControlName = FormControlName;
  readonly loginForm: FormGroup = this.fb.group({
    [FormControlName.name]: ['', Validators.required],
    [FormControlName.password]: ['', Validators.required],
    [FormControlName.rememberMe]: [''],
  });
  readonly validationMessage$ = new ReplaySubject<string>(1);
  readonly showResult$ = new BehaviorSubject<boolean>(false);

  get name(): string {
    return String(this.loginForm?.get(FormControlName.name)?.value);
  }

  get password(): string {
    return String(this.loginForm?.get(FormControlName.password)?.value);
  }

  get rememberMe(): boolean {
    return Boolean(this.loginForm?.get(FormControlName.rememberMe)?.value);
  }

  constructor(private fb: FormBuilder, private authService: AuthService) {}

  ngOnInit(): void {}

  onSubmit(): void {
    this.loginForm.markAllAsTouched();
    if (!this.loginForm.valid) {
      return;
    }
    this.showResult$.next(true);
    this.validationMessage$.next('');

    this.authService
      .login(this.name, this.password, this.rememberMe)
      .pipe(
        catchError((error: HttpErrorResponse) => {
          if (error?.error?.code) {
            this.validationMessage$.next(ValidationErrors[error.error.code]);
          }
          if (error.url?.includes('?code=')) {
            console.warn(`REDIRECTING MANUALLY TO ${error.url}`);

            window.location.replace(error.url);
          }
          this.validationMessage$.next(error.message);
          return of();
        })
      )
      .subscribe();
  }
}
