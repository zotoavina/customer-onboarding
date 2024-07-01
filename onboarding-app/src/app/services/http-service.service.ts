import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class HttpServiceService {
  host: string = environment.host;

  constructor(private http: HttpClient) {}

  public post<T>(url: string, body: any): Observable<any> {
    return this.http.post<T>(`${this.host}/${url}`, body);
  }

  public get<T>(url: string): Observable<any> {
    return this.http.get<T>(`${this.host}/${url}`);
  }

  public put<T>(url: string, body: any): Observable<any> {
    return this.http.put<T>(`${this.host}/${url}`, body);
  }

  public delete<T>(url: string): Observable<any> {
    return this.http.delete<T>(`${this.host}/${url}`);
  }

  public patch<T>(url: string, body: any): Observable<any> {
    return this.http.patch<T>(`${this.host}/${url}`, body);
  }

  public head<T>(url: string): Observable<any> {
    return this.http.head<T>(`${this.host}/${url}`);
  }

  public options<T>(url: string): Observable<any> {
    return this.http.options<T>(`${this.host}/${url}`);
  }
}
