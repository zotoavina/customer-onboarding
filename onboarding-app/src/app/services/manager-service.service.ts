import {Injectable} from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpServiceService} from "./http-service.service";

@Injectable({
  providedIn: 'root'
})
export class ManagerServiceService {

  baseUrl: string = environment.submissionHost;

  constructor(private http: HttpServiceService) {
  }

  login(authReq: any) {
    this.http.post(this.baseUrl, "submission/manager/login", authReq);
  }
}
