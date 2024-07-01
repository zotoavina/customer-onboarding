import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class HttpServiceService {

  constructor(private http: HttpClient) {}

  public post<T>(host: string, url: string, body: any): Observable<any> {
    return this.http.post<T>(`${host}/${url}`, body);
  }

  public get<T>(host: string, url: string): Observable<any> {
    return this.http.get<T>(`${host}/${url}`);
  }

  public put<T>(host: string, url: string, body: any): Observable<any> {
    return this.http.put<T>(`${host}/${url}`, body);
  }

  public delete<T>(host: string, url: string): Observable<any> {
    return this.http.delete<T>(`${host}/${url}`);
  }

  public patch<T>(host: string, url: string, body: any): Observable<any> {
    return this.http.patch<T>(`${host}/${url}`, body);
  }

  public head<T>(host: string, url: string): Observable<any> {
    return this.http.head<T>(`${host}/${url}`);
  }

  public options<T>(host: string, url: string): Observable<any> {
    return this.http.options<T>(`${host}/${url}`);
  }
}
