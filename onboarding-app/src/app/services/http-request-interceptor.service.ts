import { Injectable } from '@angular/core';
import {
  HttpRequest,
  HttpHandler,
  HttpEvent,
  HttpInterceptor,
  HTTP_INTERCEPTORS,
  HttpHeaders,
} from '@angular/common/http';
import { Observable, catchError, of } from 'rxjs';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class HttpRequestInterceptorService implements HttpInterceptor {

  requestHeaders = new HttpHeaders({
    'Access-Control-Allow-Origin':'*',
    'Content-Type': 'application/json',
    // 'X-Timezone-Offset': timeZone,
    Authorization: 'Bearer',
  });
  constructor(private router: Router) {}

  intercept(
    request: HttpRequest<unknown>,
    next: HttpHandler
  ): Observable<HttpEvent<unknown>> {
    const modifiedReq = request.clone({
      headers: this.requestHeaders,
    });
    return next.handle(modifiedReq).pipe(
      catchError((err) => {
        // this.router.navigate(['/server']);
        console.log('Error', err);
        return of(err);
      })
    );
  }
}

export const HttpInterceptorRequest = {
  provide: HTTP_INTERCEPTORS,
  useClass: HttpRequestInterceptorService,
  multi: true,
};

