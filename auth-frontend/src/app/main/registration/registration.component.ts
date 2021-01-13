import {Component, OnInit} from '@angular/core';
import {FormBuilder} from '@angular/forms';

import {AuthService} from 'src/app/service/auth.service';

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.scss'],
})
export class RegistrationComponent implements OnInit {
  registrationForm = this.fb.group({
    name: [''],
    email: [''],
    password: [''],
  });

  constructor(private fb: FormBuilder, private authService: AuthService) {}

  ngOnInit(): void {}

  onSubmit(): void {
    console.log('onSubmit called');

    this.authService.login(
      this.registrationForm.value.name,
      this.registrationForm.value.password
    );
  }
}
