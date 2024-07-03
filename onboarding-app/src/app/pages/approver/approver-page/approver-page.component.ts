import {Component, OnInit} from '@angular/core';
import { Customer } from '../../../shared/model/customer';
import { ApplicationSelectionService } from 'src/app/services/application-selection.service';
import { Router } from '@angular/router';
import { DataResponse } from 'src/app/shared/model/data-response';
import { map } from 'rxjs';
import {Kpi} from "../../../shared/model/kpi";
import {ApplicationManagementService} from "../../../services/application-management.service";

@Component({
  selector: 'app-approver-page',
  templateUrl: './approver-page.component.html',
  styleUrls: ['./approver-page.component.css']
})
export class ApproverPageComponent implements OnInit{

  processorTableHeader : string[] = [
    "Company","Entity", "Activity", "Country", "Registration number", "Action"
  ];

  proceededApplication: Customer[] = [];
  appKpi: Kpi = new Kpi();

  constructor(
    private applicationSelectionSrv : ApplicationSelectionService,
    private appManagementSrv: ApplicationManagementService,
    private router : Router){}

  ngOnInit(): void {
    this.getProcessedApplication();
    this.getAppKpi();
  }



  getProcessedApplication(){
     this.applicationSelectionSrv.getListOfProceededApplication().pipe(
      map((res: DataResponse<Customer[]>) => res)).subscribe(
        (res) => {
           if(res.code === 200){
            console.log(res.data);
            this.proceededApplication = res.data;
           }
        }
      )
  }

  getAppKpi() {
    this.applicationSelectionSrv.getApplicationKpi().pipe(
      map((res: DataResponse<Kpi>) => res)).subscribe(
      res => {
        if (res.code === 200) {
          console.log(res.data);
          this.appKpi = res.data;
        }
      }
    )
  }
    approve(appId : string){
      console.log("Approve " + appId);
      let app = {
        "applicationUUID": appId
      };
      this.appManagementSrv.approveApplication(app)
        .subscribe(res => {
          if (res.code === 200) {
            console.log("Proceeded");
            this.proceededApplication = this.proceededApplication
              .filter(appli => appli.applicationId != appId);
            this.appKpi.approved++;
          }
        })
    }

    reject(appId : string){
      console.log("reject " + appId);
      console.log("reject " + appId);
      let app = {
        "applicationUUID": appId
      };
      this.appManagementSrv.rejectApplication(app)
        .pipe(
          map((res: DataResponse<string>) => res)).subscribe(
        (res) => {
          if (res.code === 200) {
            console.log("Rejected");
            this.proceededApplication = this.proceededApplication
              .filter(appli => appli.applicationId != appId);
            this.appKpi.rejected++;
          }
        }
      )
    }

  protected readonly RangeError = RangeError;
}
