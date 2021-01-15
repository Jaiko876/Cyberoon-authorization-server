import { ChangeDetectionStrategy, Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { AuthService } from '../../service/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class LoginComponent implements OnInit {
  readonly loginForm: FormGroup = this.fb.group({
    name: ['', Validators.required],
    password: ['', Validators.required],
    rememberMe: [''],
  });

  get name(): string {
    return (this.loginForm?.get('name')?.value as string) || '';
  }

  get password(): string {
    return (this.loginForm?.get('password')?.value as string) || '';
  }

  get rememberMe(): boolean {
    return !!this.loginForm?.get('rememberMe')?.value;
  }

  constructor(private fb: FormBuilder, private authService: AuthService) {}

  ngOnInit(): void {}

  onSubmit(): void {
    console.log('onSubmit called');

    this.authService.login(this.name, this.password, this.rememberMe);
  }
}
