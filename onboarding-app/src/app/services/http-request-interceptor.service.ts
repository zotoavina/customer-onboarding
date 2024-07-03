import {Injectable} from '@angular/core';
import {
  HTTP_INTERCEPTORS,
  HttpEvent,
  HttpHandler,
  HttpHeaders,
  HttpInterceptor,
  HttpRequest,
} from '@angular/common/http';
import {catchError, Observable, of} from 'rxjs';
import {Router} from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class HttpRequestInterceptorService implements HttpInterceptor {


  private whitelist: string[] = [
    'api/submission/manager/login',
    'api/submissions',
    'api/submissions/client'
  ];

  constructor(private router: Router) {
  }

  private shouldIntercept(req: HttpRequest<any>): boolean {
    // Exclude URLs in the whitelist from interception
    return !this.whitelist.some(url => req.url.includes(url));
  }

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    console.log("intercept");
    let headers = request.headers;

    if (request.url.includes('/update') || request.body instanceof FormData) {
      headers = headers.set('Content-Type', 'multipart/form-data');
    } else {
      headers = headers.set('Content-Type', 'application/json');
    }
    headers = headers.set('Access-Control-Allow-Origin', '*')

    if (this.shouldIntercept(request)) {
      headers = headers.set('Authorization', 'Bearer ' + localStorage.getItem('Bearer Token'));
      const modifiedReq = request.clone({ headers });
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

