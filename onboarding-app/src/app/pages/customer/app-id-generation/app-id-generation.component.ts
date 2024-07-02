import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router, Routes } from '@angular/router';

@Component({
  selector: 'app-app-id-generation',
  templateUrl: './app-id-generation.component.html',
  styleUrls: ['./app-id-generation.component.css']
})
export class AppIdGenerationComponent implements OnInit{


  applicationID: string | null = null;

  constructor(private router: ActivatedRoute){}
  
  ngOnInit(): void {
   this.router.paramMap.subscribe(params => {
     this.applicationID  = params.get('uuid');
  });
}

}
