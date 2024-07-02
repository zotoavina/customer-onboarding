import {Injectable} from '@angular/core';
import {environment} from 'src/environments/environment';
import {HttpServiceService} from './http-service.service';

@Injectable({
  providedIn: 'root'
})
export class ApplicationManagementService {

  baseUrl: string = environment.submissionHost;

  constructor(private http: HttpServiceService) {
  }

  private processApplication(applicationId: any, subPath: string) {
    return this.http.patch(this.baseUrl, subPath, applicationId);
  }

  proceedApplication(applicationId: any) {
    return this.processApplication(applicationId, "submission/management/proceed");
  }

  approveApplication(applicationId: any) {
    return this.processApplication(applicationId, "submission/management/approve");
  }

  rejectApplication(applicationId: any) {
    return this.processApplication(applicationId, " submission/management/reject");
  }
}
