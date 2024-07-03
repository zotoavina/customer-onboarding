import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { map } from 'rxjs';
import { ApplicationSelectionService } from 'src/app/services/application-selection.service';
import { CustomerServiceService } from 'src/app/services/customer-service.service';
import { DataReferenceServiceService } from 'src/app/services/data-reference-service.service';
import { BANKING_UUID } from 'src/app/shared/constant/all-constant';
import { Activity } from 'src/app/shared/model/activity';
import { Country } from 'src/app/shared/model/country';
import { Customer } from 'src/app/shared/model/customer';
import { DataResponse } from 'src/app/shared/model/data-response';
import { EntityType } from 'src/app/shared/model/entity-type';
import { Purpose } from 'src/app/shared/model/purpose';

@Component({
  selector: 'app-edit-application',
  templateUrl: './edit-application.component.html',
  styleUrls: ['./edit-application.component.css']
})
export class EditApplicationComponent implements OnInit{
  onboardingFirstForm!: FormGroup;
  onboardingSecondForm!: FormGroup;
  isLinear = true;
  isActivityBanking = false;
  purposes: Purpose[] = [];
  entities: EntityType[] = [];
  activities: Activity[] = [];
  countries: Country[] = [];
  customerApplication! : Customer;
  formData = new FormData();

  constructor(
    private formBuilder: FormBuilder, 
    private router: Router,
    private dataReferenceSrv: DataReferenceServiceService,
    private customerSrv: CustomerServiceService,
    private activatedRoute : ActivatedRoute,
    private applicationSelectionSrv : ApplicationSelectionService
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
   
  }

  async ngOnInit(): Promise<void> {
    this.activatedRoute.paramMap.subscribe(async params => {
      const applicationID = params.get('uuid');
      if (applicationID) {
        try {
          this.customerApplication = await this.getApplicationByUUID(applicationID);
          console.log(this.customerApplication);
          this.updateForm();
        } catch (error) {
          console.error('Error fetching application:', error);
          // Gérer l'erreur (par exemple, afficher un message à l'utilisateur)
        }
      }
    });

    this.onboardingFirstForm.get('activityUuid')?.valueChanges.subscribe(value => {
      this.isActivityBanking = value === BANKING_UUID;
    });
  }

  updateForm(): void {
    if (this.customerApplication) {
      this.onboardingFirstForm.patchValue({
        purposeUuid: this.customerApplication.applyingPurposeUuid,
        companyName: this.customerApplication.companyName,
        entityTypeUuid: this.customerApplication.entityTypeUuid,
        activityUuid: this.customerApplication.activityUuid,
        licence: this.customerApplication.licence,
        countryName: this.customerApplication.countryName,
        registrationNumber: this.customerApplication.registrationNumber,
        incorporationDate: this.customerApplication.dateOfIncorporation
      });

      this.onboardingSecondForm.patchValue({
        directorName: this.customerApplication.directorName,
        directorPassportNumber: this.customerApplication.directorPassportNumber,
        nameOfApplicant: this.customerApplication.nameOfApplicant,
        emailForCom: this.customerApplication.emailForCom
      });
    }
  }

  async getApplicationByUUID(uuid: string): Promise<any> {
    try {
      return await this.applicationSelectionSrv.getApplicationByUUID(uuid).pipe(
        map((res : DataResponse<Customer>) => res.data)).toPromise();
    } catch (error) {
      console.error('Error fetching application:', error);
      throw error; // Rejeter l'erreur pour gérer dans ngOnInit
    }
  }

  edit() {
    this.getOnboardingFirstFormValue();
    this.getOnboardingSecondFormValue();
    
    // *********************************************************** //
    // TODO : ito no ovaina an'ilay api update
    // this.customerSrv.postCustomerForm(this.formData).subscribe(
    //   (res) =>{
    //     console.log(res);
    //     if(res.code === 201){
    //       var uuid: string = res.data;
    //       this.router.navigate(['submitted/' + uuid]);
    //     }
    //   }
    // );
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
      map((res: DataResponse<Country[]> ) => {
        console.log(res.data);
        this.countries =  res.data;
      }))
    .subscribe();
  }
  
   getApplyingPurpose(){
     this.dataReferenceSrv.getApplyingPurpose()
    .pipe(
      map((res: DataResponse<Purpose[]> ) => {
        this.purposes =  res.data;
      }))
    .subscribe();
   }

  getActivities(){
    this.dataReferenceSrv.getActivities()
    .pipe(
      map((res: DataResponse<Activity[]> ) => {
      this.activities =  res.data;
    }))
  .subscribe();
 }

 getEntityTypes(){
  this.dataReferenceSrv.getEntityTypes()
  .pipe(
    map((res: DataResponse<EntityType[]> ) => {
    this.entities =  res.data;
  }))
 .subscribe();
}
}
