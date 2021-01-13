import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

import {ErrorComponent} from './error/error.component';
import {LogoutComponent} from './logout/logout.component';

const routes: Routes = [
  {
    path: '',
    loadChildren: () =>
      import('src/app/main/main.module').then((m) => m.MainModule),
  },
  {
    path: 'logout',
    component: LogoutComponent,
  },
  {
    path: 'error',
    component: ErrorComponent,
  },
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}
