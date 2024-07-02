import { Component, OnInit } from '@angular/core';
import { map } from 'rxjs';
import { ApplicationSelectionService } from 'src/app/services/application-selection.service';
import { Customer } from 'src/app/shared/model/customer';
import { DataResponse } from 'src/app/shared/model/data-response';

@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit{


processorTableHeader : string[] = [
  "Company","Entity", "Activity", "Country", "Registration number","Action", "Process action"
];

submittedApplication: Customer[] = []; 

constructor(private applicationSelectionSrv : ApplicationSelectionService){}


ngOnInit(): void {
  this.getSubmittedApplication();
}

getSubmittedApplication(){
  this.submittedApplication = [];
  this.applicationSelectionSrv.getListOfSubmittedApplication().pipe(
    map((res: DataResponse<Customer>) => res)).subscribe(
      (res) => {
         if(res.code === 200){
          console.log(res.data);
          this.submittedApplication = res.data;
         }

      }
    )

}

  edit(appId : string){
    console.log("edit" + appId);
  }

  viewDocuments(appId : string){
    console.log("view documents of " + appId);
  }

  proceed(appId : string){
    console.log("proceed " + appId);
  }

  reject(appId : string){
    console.log("reject " + appId);
  }

}
