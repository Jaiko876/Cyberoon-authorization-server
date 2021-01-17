import { ChangeDetectionStrategy, Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { AuthService } from '../../service/auth.service';

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

    this.authService.login(this.name, this.password, this.rememberMe).subscribe(
      (data) => {
        console.log('success');
        console.log(data);
      },
      (err) => {
        if (err.url) {
          console.warn(`REDIRECTING MANUALLY TO ${err.url}`);

          window.location.replace(err.url);
        }
        console.log('error');
        console.log(err);
      }
    );
  }
}
