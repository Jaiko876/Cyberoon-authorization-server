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

  constructor(private fb: FormBuilder, private authService: AuthService) {}

  ngOnInit(): void {}

  onSubmit(): void {
    console.log('onSubmit called');

    this.authService.login(
      this.loginForm.value.name,
      this.loginForm.value.password,
      this.loginForm.value.rememberMe
    );
  }
}
