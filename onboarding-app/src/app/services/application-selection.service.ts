import {Injectable} from '@angular/core';
import {environment} from "../../environments/environment";
import {HttpServiceService} from "./http-service.service";
import { Observable } from 'rxjs';
import { Customer } from '../shared/model/customer';

@Injectable({
  providedIn: 'root'
})
export class ApplicationSelectionService {


  baseUrl: string = environment.submissionHost;

  constructor(private http: HttpServiceService) {
  }

  clientGetApplication(applicationId: any) {
    return this.http.post(this.baseUrl, "submissions/client", applicationId);
  }

  getApplicationByUUID(uuid: String){
    let additionalPath: string = "submission/management/" + uuid;
    return this.http.get(this.baseUrl, additionalPath);
  }

  getListOfSubmittedApplication() {
    return this.http.get(this.baseUrl, "submission/management/submitted");
  }

  getListOfProceededApplication() {
    return this.http.get(this.baseUrl, "submission/management/proceeded");
  }
}
