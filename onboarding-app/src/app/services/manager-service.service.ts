import {Injectable} from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpServiceService} from "./http-service.service";
import { Login } from '../shared/model/login';
import { tap } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ManagerServiceService {

  baseUrl: string = environment.submissionHost;
  token!: string;

  constructor(private http: HttpServiceService) {
  }

  login(authReq: Login) {
    return this.http.post(this.baseUrl, "submission/manager/login", authReq).
    pipe(
      tap((response:any) => {
        this.token = response.data.token; // Store the received token 
        localStorage.setItem('token',this.token);
        console.log(this.token)
      })
    );
  }

  logout(): void {
    localStorage.removeItem('token');
  }
  



}
