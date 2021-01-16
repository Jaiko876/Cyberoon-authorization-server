import { HTTP_INTERCEPTORS, HttpClientModule, HttpClientXsrfModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ErrorComponent } from './error/error.component';
import { InterInterceptor } from './inter.interceptor';
import { LogoutComponent } from './logout/logout.component';

@NgModule({
  declarations: [AppComponent, ErrorComponent, LogoutComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    HttpClientXsrfModule.withOptions(),
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: InterInterceptor,
      multi: true,
    },
  ],
  bootstrap: [AppComponent],
})
export class AppModule {}
