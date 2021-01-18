import { HttpErrorResponse } from '@angular/common/http';
import { ChangeDetectionStrategy, Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { catchError } from 'rxjs/operators';

import { BehaviorSubject, of, ReplaySubject } from 'rxjs';
import { AuthService } from 'src/app/service/auth.service';
import { ValidationErrors } from 'src/app/service/validationErrors.model';

enum FormControlName {
  name = 'name',
  email = 'email',
  password = 'password',
  confirmPassword = 'confirmPassword',
}
@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class RegistrationComponent implements OnInit {
  readonly formControlName = FormControlName;
  readonly registrationForm: FormGroup = this.fb.group({
    [FormControlName.name]: ['', Validators.required],
    [FormControlName.email]: ['', [Validators.required, Validators.email]],
    [FormControlName.password]: ['', Validators.required],
    [FormControlName.confirmPassword]: ['', Validators.required],
  });
  readonly validationMessage$ = new ReplaySubject<string>(1);
  readonly showResult$ = new BehaviorSubject<boolean>(false);

  get name(): string {
    return String(this.registrationForm?.get(FormControlName.name)?.value);
  }

  get password(): string {
    return String(this.registrationForm?.get(FormControlName.password)?.value);
  }

  get email(): string {
    return String(this.registrationForm?.get(FormControlName.email)?.value);
  }

  constructor(private fb: FormBuilder, private authService: AuthService) {}

  ngOnInit(): void {}

  onSubmit(): void {
    this.registrationForm.markAllAsTouched();
    if (!this.registrationForm.valid) {
      return;
    }
    this.showResult$.next(true);
    this.authService
      .registration(this.name, this.email, this.password)
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
