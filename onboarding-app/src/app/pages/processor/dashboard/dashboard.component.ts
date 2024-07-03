import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
import {map} from 'rxjs';
import {ApplicationSelectionService} from 'src/app/services/application-selection.service';
import {Customer} from 'src/app/shared/model/customer';
import {DataResponse} from 'src/app/shared/model/data-response';
import {ApplicationManagementService} from "../../../services/application-management.service";
import {Kpi} from "../../../shared/model/kpi";

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {


  processorTableHeader: string[] = [
    "Company", "Entity", "Activity", "Country", "Registration number", "Action", "Process action"
  ];

  submittedApplication: Customer[] = [];
  appKpi: Kpi = new Kpi();

  constructor(
    private applicationSelectionSrv: ApplicationSelectionService,
    private appManagementSrv: ApplicationManagementService,
    private router: Router) {
  }


  ngOnInit(): void {
    this.getSubmittedApplication();
    this.getAppKpi();
  }

  getSubmittedApplication() {
    this.applicationSelectionSrv.getListOfSubmittedApplication().pipe(
      map((res: DataResponse<Customer[]>) => res)).subscribe(
      (res) => {
        if (res.code === 200) {
          this.submittedApplication = res.data;
        }

      }
    )
  }

  getAppKpi() {
    this.applicationSelectionSrv.getApplicationKpi().pipe(
      map((res: DataResponse<Kpi>) => res)).subscribe(
      res => {
        if (res.code === 200) {
          this.appKpi = res.data;
        }
      }
    )
  }

  edit(appId: string) {
    this.router.navigate(['mcb/edit/' + appId]);
  }

  viewDocument(appId: string) {
    console.log("view documents of " + appId);

  }

  proceed(appId: string) {
    console.log("proceed " + appId);
    let app = {
      "applicationUUID": appId
    };
    this.appManagementSrv.proceedApplication(app)
      .subscribe(res => {
        if (res.code === 200) {
          console.log("Proceeded");
          this.submittedApplication = this.submittedApplication
            .filter(appli => appli.applicationId != appId);
          this.appKpi.proceeded++;
        }
      })
  }

  reject(appId: string) {
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
          this.submittedApplication = this.submittedApplication
            .filter(appli => appli.applicationId != appId);
          this.appKpi.rejected++;
        }
      }
    )
  }

}
