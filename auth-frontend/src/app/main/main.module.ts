import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';

import { MainComponent } from '../main/main.component';
import { LoginComponent } from './login/login.component';
import { MainRoutingModule } from './main-routing.module';
import { RegistrationComponent } from './registration/registration.component';
import { SocialLoginComponent } from './social-login/social-login.component';
import { TuiInputModule } from '@taiga-ui/kit';

@NgModule({
  declarations: [
    MainComponent,
    LoginComponent,
    RegistrationComponent,
    SocialLoginComponent,
  ],
  imports: [
    CommonModule,
    ReactiveFormsModule,
    MainRoutingModule,
    TuiInputModule,
  ],
})
export class MainModule {}
