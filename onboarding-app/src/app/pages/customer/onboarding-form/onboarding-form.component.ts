import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { map } from 'rxjs';
import { DataReferenceServiceService } from 'src/app/services/data-reference-service.service';
import { Activity } from 'src/app/shared/model/activity';
import { Country } from 'src/app/shared/model/country';
import { DataResponse } from 'src/app/shared/model/data-response';
import { EntityType } from 'src/app/shared/model/entity-type';
import { Purpose } from 'src/app/shared/model/purpose';

@Component({
  selector: 'app-onboarding-form',
  templateUrl: './onboarding-form.component.html',
  styleUrls: ['./onboarding-form.component.css']
})
export class OnboardingFormComponent implements OnInit{
  onboardingFirstForm: FormGroup;
  onboardingSecondForm: FormGroup;
  onboardingThirdForm: FormGroup;
  isLinear = true;
  isActivityBanking = false;
  purposes: Purpose[] = [];
  entities: EntityType[] = [];
  activities: Activity[] = [];
  countries: Country[] = [];

  constructor(
    private formBuilder: FormBuilder, 
    private router: Router,
    private dataReferenceSrv: DataReferenceServiceService
  ) {

    this.onboardingFirstForm = this.formBuilder.group({
      purpose: ['', Validators.required],
      company: ['', [Validators.required, Validators.minLength(3)]],
      entity: ['', Validators.required],
      activity: ['', Validators.required],
      licence: ['', Validators.required],
      country: ['', Validators.required],
      registrationNumber: ['', Validators.required],
      dateOfIncorporation: ['', Validators.required]
    });
    this.onboardingSecondForm = this.formBuilder.group({
      director: ['', Validators.required],
      passport: ['', Validators.required],
      applicant: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]]
    });

    this.onboardingThirdForm = this.formBuilder.group({
      document: ['',Validators.required]
    });
  }

  ngOnInit(): void {
    this.initializeData();
    this.onboardingFirstForm.get('activity')?.valueChanges.subscribe(value => {
      this.isActivityBanking = value === '1';
    });
  }

  validation() {
    this.router.navigate(['/submitted']);
  }


  initializeData(){
    this.getAllCountries();
    this.getApplyingPurpose();
    this.getActivities();
    this.getEntityTypes();
  }

  getAllCountries(){
     this.dataReferenceSrv.getAllCountries()
    .pipe(
      map((res: DataResponse<Country> ) => {
        console.log(res.data);
        this.countries =  res.data;
      }))
    .subscribe();
  }
  
   getApplyingPurpose(){
     this.dataReferenceSrv.getApplyingPurpose()
    .pipe(
      map((res: DataResponse<Purpose> ) => {
        this.purposes =  res.data;
      }))
    .subscribe();
   }

  getActivities(){
    this.dataReferenceSrv.getActivities()
    .pipe(
      map((res: DataResponse<Activity> ) => {
      this.activities =  res.data;
    }))
  .subscribe();
 }

 getEntityTypes(){
  this.dataReferenceSrv.getEntityTypes()
  .pipe(
    map((res: DataResponse<EntityType> ) => {
    this.entities =  res.data;
  }))
 .subscribe();
}

}
