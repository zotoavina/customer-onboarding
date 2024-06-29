import { Component, OnInit } from '@angular/core';
import { Customer } from 'src/app/shared/model/customer';

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

ngOnInit(): void {
  this.getSubmittedApplication();
}

getSubmittedApplication(){
  this.submittedApplication = [
    {
        id:12,
        purpose:"1",
        company:"ABC",
        entity:"3",
        activity:"5",
        licence:"dqdzz545a",
        country:"2",
        registrationNumber:"56774CV",
        dateOfIncorporation:"2024/05/02",
        director:"John DOE",
        passport:"1235 4552 421DF",
        applicant:"Jane DOE",
        email: "janedoe@gmail.com",
        document: "photo.png,doc.pdf",
    },
    {
      id:32,
      purpose:"1",
      company:"ABC",
      entity:"3",
      activity:"5",
      licence:"dqdzz545a",
      country:"2",
      registrationNumber:"56774CV",
      dateOfIncorporation:"2024/05/02",
      director:"John DOE",
      passport:"1235 4552 421DF",
      applicant:"Jane DOE",
      email: "janedoe@gmail.com",
      document: "photo.png,doc.pdf",
  },
  {
    id:4,
    purpose:"1",
    company:"ABC",
    entity:"3",
    activity:"5",
    licence:"dqdzz545a",
    country:"2",
    registrationNumber:"56774CV",
    dateOfIncorporation:"2024/05/02",
    director:"John DOE",
    passport:"1235 4552 421DF",
    applicant:"Jane DOE",
    email: "janedoe@gmail.com",
    document: "photo.png,doc.pdf",
  },
  {
    id:3,
    purpose:"1",
    company:"ABC",
    entity:"3",
    activity:"5",
    licence:"dqdzz545a",
    country:"2",
    registrationNumber:"56774CV",
    dateOfIncorporation:"2024/05/02",
    director:"John DOE",
    passport:"1235 4552 421DF",
    applicant:"Jane DOE",
    email: "janedoe@gmail.com",
    document: "photo.png,doc.pdf",
  }
  ]
}

  edit(appId : number){
    console.log("edit" + appId);
  }

  viewDocuments(appId : number){
    console.log("view documents of " + appId);
  }

  proceed(appId : number){
    console.log("proceed " + appId);
  }

  reject(appId : number){
    console.log("reject " + appId);
  }

}
