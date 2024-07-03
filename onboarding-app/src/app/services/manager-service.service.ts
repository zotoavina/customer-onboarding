import {Injectable} from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpServiceService} from "./http-service.service";
import { Login } from '../shared/model/login';
import { map, tap } from 'rxjs';
import { Router } from '@angular/router';
import { DataResponse } from '../shared/model/data-response';
import { ROLE } from '../shared/constant/all-constant';

@Injectable({
  providedIn: 'root'
})
export class ManagerServiceService {

  baseUrl: string = environment.submissionHost;
  token!: string;
  role: string | null = null;
  private isAuthenticated = false;
  private authSecretKey = 'Bearer Token';
  private roleKey = 'ROLE';

  constructor(
    private http: HttpServiceService,
    private router: Router) {
      this.isAuthenticated = !!localStorage.getItem(this.authSecretKey);
  }

  login(authReq: Login) {
    return this.http.post(this.baseUrl, "submission/manager/login", authReq);
  }

  setAuthenticated(token:string, role: string){
    this.token = token;
    this.role=role;
    localStorage.setItem(this.authSecretKey ,this.token);
    localStorage.setItem(this.roleKey, this.role!);
    this.isAuthenticated = true;
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
    this.role = localStorage.getItem(this.roleKey);
    return this.role;
  }

  isProcessor(){
    if(this.getRole()?.localeCompare(ROLE.PROCESSOR) === 0){  return true}
    return false;
  }

  isApprover(){
    if(this.getRole()?.localeCompare(ROLE.APPROVER) === 0){  return true}
    return false;
  }

}
