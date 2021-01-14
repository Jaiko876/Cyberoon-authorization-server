import { HttpClientModule, HttpClientXsrfModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ErrorComponent } from './error/error.component';
import { LogoutComponent } from './logout/logout.component';
import { TuiRootModule } from '@taiga-ui/core/components/root';

@NgModule({
  declarations: [AppComponent, ErrorComponent, LogoutComponent],
  imports: [
    BrowserModule,
    AppRoutingModule,
    TuiRootModule,
    HttpClientModule,
    HttpClientXsrfModule.withOptions(),
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
