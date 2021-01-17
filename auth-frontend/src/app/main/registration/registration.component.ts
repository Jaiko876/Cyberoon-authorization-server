import { ChangeDetectionStrategy, Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { AuthService } from 'src/app/service/auth.service';

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
  readonly registrationForm: FormGroup = this.fb.group({
    [FormControlName.name]: ['', Validators.required],
    [FormControlName.email]: ['', Validators.required],
    [FormControlName.password]: ['', Validators.required],
    [FormControlName.confirmPassword]: ['', Validators.required],
  });

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
    this.authService.register(this.name, this.email, this.password);
  }
}
