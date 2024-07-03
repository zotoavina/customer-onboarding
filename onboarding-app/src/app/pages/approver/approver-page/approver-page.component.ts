import {Component, OnInit} from '@angular/core';
import { Customer } from '../../../shared/model/customer';
import { ApplicationSelectionService } from 'src/app/services/application-selection.service';
import { Router } from '@angular/router';
import { DataResponse } from 'src/app/shared/model/data-response';
import { map } from 'rxjs';

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

  constructor(
    private applicationSelectionSrv : ApplicationSelectionService,
    private router : Router){}

  ngOnInit(): void {
    this.getProcessedApplication();
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
    approve(appId : string){
      console.log("Approve " + appId);
    }

    reject(appId : string){
      console.log("reject " + appId);
    }
}
