import { Component } from '@angular/core';
import { Customer } from '../../../shared/model/customer';

@Component({
  selector: 'app-approver-page',
  templateUrl: './approver-page.component.html',
  styleUrls: ['./approver-page.component.css']
})
export class ApproverPageComponent {

  processorTableHeader : string[] = [
    "Company","Entity", "Activity", "Country", "Registration number", "Action"
  ];
  
  submittedApplication: Customer[] = []; 
  
  ngOnInit(): void {
    this.getSubmittedApplication();
  }
  
  getSubmittedApplication(){
    this.submittedApplication = []
  }
    proceed(appId : string){
      console.log("Approve " + appId);
    }
  
    reject(appId : string){
      console.log("reject " + appId);
    }
}
