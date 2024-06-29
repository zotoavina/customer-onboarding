import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-app-id-generation',
  templateUrl: './app-id-generation.component.html',
  styleUrls: ['./app-id-generation.component.css']
})
export class AppIdGenerationComponent implements OnInit{


  applicationID!: string ;
  
  ngOnInit(): void {
    this.applicationID = "556 124 J80 4K0";
  }

}
