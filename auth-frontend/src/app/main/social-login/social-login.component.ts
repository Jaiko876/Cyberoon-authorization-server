import { ChangeDetectionStrategy, Component, OnInit } from '@angular/core';

import { AuthService } from 'src/app/service/auth.service';

@Component({
  selector: 'app-social-login',
  templateUrl: './social-login.component.html',
  styleUrls: ['./social-login.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class SocialLoginComponent implements OnInit {
  constructor(private authService: AuthService) {}

  ngOnInit(): void {}

  googleLogin(): void {
    this.authService.googleLogin();
  }
}
