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
    Authorization: 'Bearer ' + localStorage.getItem('Bearer Token'),
  });

  private whitelist: string[] = [
    'api/v1/submission/manager/login',
    'api/v1/submissions',
    'api/v1/submissions/client'
    // Add other URLs you want to exclude from interception
  ];

  constructor(private router: Router) {}

  private shouldIntercept(req: HttpRequest<any>): boolean {
    // Exclude URLs in the whitelist from interception
    return !this.whitelist.some(url => req.url.includes(url));
  }

  intercept(request: HttpRequest<unknown>,next: HttpHandler): Observable<HttpEvent<unknown>> {
    console.log("intercept");
    const modifiedReq = request.clone({
      headers: this.requestHeaders,
    });
    if(this.shouldIntercept(request)){
      return next.handle(modifiedReq).pipe(
        catchError((err) => {
          // this.router.navigate(['/server']);
          console.log('Error', err);
          return of(err);
        })
      );
    }
    return next.handle(request);
  }
}



export const HttpInterceptorRequest = {
  provide: HTTP_INTERCEPTORS,
  useClass: HttpRequestInterceptorService,
  multi: true,
};

