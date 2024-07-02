import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { map } from 'rxjs';
import { CustomerServiceService } from 'src/app/services/customer-service.service';
import { DataReferenceServiceService } from 'src/app/services/data-reference-service.service';
import { Activity } from 'src/app/shared/model/activity';
import { FirstFormRequest } from 'src/app/shared/model/application-request';
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
  formData = new FormData();

  constructor(
    private formBuilder: FormBuilder, 
    private router: Router,
    private dataReferenceSrv: DataReferenceServiceService,
    private customerSrv: CustomerServiceService
  ) {

    this.initializeData();
    this.onboardingFirstForm = this.formBuilder.group({
      purposeUuid: ['', Validators.required],
      companyName: ['', [Validators.required, Validators.minLength(3)]],
      entityTypeUuid: ['', Validators.required],
      activityUuid: ['', Validators.required],
      licence: [''],
      countryName: ['', Validators.required],
      registrationNumber: ['', Validators.required],
      incorporationDate: ['', Validators.required]
    });
    this.onboardingSecondForm = this.formBuilder.group({
      directorName: ['', Validators.required],
      directorPassportNumber: ['', Validators.required],
      nameOfApplicant: ['', Validators.required],
      emailForCom: ['', [Validators.required, Validators.email]]
    });

    this.onboardingThirdForm = this.formBuilder.group({
      file: ['']
    });
  }

  ngOnInit(): void {
   
    this.onboardingFirstForm.get('activity')?.valueChanges.subscribe(value => {
      this.isActivityBanking = value === '1';
    });
  }

  validation() {
    this.getOnboardingFirstFormValue();
    this.getOnboardingSecondFormValue();
    this.customerSrv.postCustomerForm(this.formData).subscribe(
      (res) =>{
        console.log(res);
        if(res.code === 201){
          var uuid: string = res.data;
          this.router.navigate(['submitted/' + uuid]);
        }
      }
    );
    
    console.log(this.formData.get("file"));
    // this.router.navigate(['/submitted']);
  }

  uploadFile(event : any){
    const file = event.target.files[0];
    this.formData.append('file',file);
  }

  getOnboardingFirstFormValue(){
    if(this.onboardingFirstForm.valid){
      const firstValue = this.onboardingFirstForm.value;
      this.formData.append("purposeUuid",firstValue.purposeUuid);
      this.formData.append("companyName",firstValue.companyName);
      this.formData.append("entityTypeUuid",firstValue.entityTypeUuid);
      this.formData.append("activityUuid",firstValue.activityUuid);
      this.formData.append("licence",firstValue.licence);
      this.formData.append("countryName",firstValue.countryName);
      this.formData.append("registrationNumber",firstValue.registrationNumber);
      this.formData.append("incorporationDate",firstValue.incorporationDate);
    }
  }

  getOnboardingSecondFormValue(){
    if(this.onboardingSecondForm.valid){
      const secondFormValue = this.onboardingSecondForm.value;
      this.formData.append("directorName",secondFormValue.directorName);
      this.formData.append("directorPassportNumber",secondFormValue.directorPassportNumber);
      this.formData.append("nameOfApplicant",secondFormValue.nameOfApplicant);
      this.formData.append("emailForCom",secondFormValue.emailForCom);
    }
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
