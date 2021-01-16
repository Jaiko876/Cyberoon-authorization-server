import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { catchError } from 'rxjs/operators';

import { Observable, throwError } from 'rxjs';

@Injectable()
export class InterInterceptor implements HttpInterceptor {
  constructor() {}

  intercept(
    request: HttpRequest<unknown>,
    next: HttpHandler
  ): Observable<HttpEvent<unknown>> {
    return next.handle(request).pipe(
      catchError((err: any, caught: Observable<HttpEvent<unknown>>) => {
        if (err.status === 403) {
          console.log('403');
        }
        console.log(err);
        console.log(caught);

        return throwError(err);
      })
    );
  }
}
