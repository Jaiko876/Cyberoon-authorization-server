import {Component, OnInit} from '@angular/core';
import {FormBuilder} from '@angular/forms';

import {AuthService} from '../../service/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit {
  loginForm = this.fb.group({
    name: [''],
    password: [''],
  });

  constructor(private fb: FormBuilder, private authService: AuthService) {}

  ngOnInit(): void {}

  onSubmit(): void {
    console.log('onSubmit called');

    this.authService.login(
      this.loginForm.value.name,
      this.loginForm.value.password
    );
  }
}
