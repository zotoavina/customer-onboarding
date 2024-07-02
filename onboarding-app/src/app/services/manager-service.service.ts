import {Injectable} from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpServiceService} from "./http-service.service";
import { Login } from '../shared/model/login';
import { map, tap } from 'rxjs';
import { Router } from '@angular/router';
import { DataResponse } from '../shared/model/data-response';

@Injectable({
  providedIn: 'root'
})
export class ManagerServiceService {

  baseUrl: string = environment.submissionHost;
  token!: string;
  role!: string;
  private isAuthenticated = false;
  private authSecretKey = 'Bearer Token';
  private roleKey = 'ROLE';

  constructor(
    private http: HttpServiceService,
    private router: Router) {
      this.isAuthenticated = !!localStorage.getItem(this.authSecretKey);
  }

  login(authReq: Login) {
    return this.http.post(this.baseUrl, "submission/manager/login", authReq).
    pipe(
      map((res: DataResponse<any> ) => {
        return res;
      }),
      tap((response:any) => {
        this.token = response.data.token; // Store the received token 
        this.role = response.data.role? response.data.role : "";
        localStorage.setItem(this.authSecretKey ,this.token);
        localStorage.setItem(this.roleKey, this.role);
        this.isAuthenticated = true;
      })
    );
  }

  logout(): void {
    localStorage.removeItem(this.authSecretKey);
    localStorage.removeItem(this.roleKey);
    this.isAuthenticated = false;
    this.router.navigate(["/login"]);
  }

  isAuthenticatedUser(): boolean {
    return this.isAuthenticated;
  }

  getRole() {
    return localStorage.getItem(this.roleKey);
  }


}
