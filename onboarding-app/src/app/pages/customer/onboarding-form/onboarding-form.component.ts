import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { map } from 'rxjs';
import { DataReferenceServiceService } from 'src/app/services/data-reference-service.service';
import { Country } from 'src/app/shared/model/country';

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


  purposes: string[] = [ "Investment porfolio", "Account to operatelocally", 
  "Account to operate overseas", "Energy & commodiƟes financing"];

  entities: string[] = [ "Investment porfolio", "Account to operatelocally", 
  "Account to operate overseas", "Energy & commodiƟes financing"];

  activities: string[] = [ "Banking", "Account to operatelocally", 
  "Account to operate overseas", "Energy & commodiƟes financing"];

  countries: Country[] = [];

  constructor(
    private formBuilder: FormBuilder, 
    private router: Router,
    private dataReferenceSrv: DataReferenceServiceService
  ) {
    this.getAllCountries();
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
    this.onboardingFirstForm.get('activity')?.valueChanges.subscribe(value => {
      this.isActivityBanking = value === 'Banking';
    });
  }

  validation() {
    this.router.navigate(['/submitted']);
  }

  getAllCountries(){
     this.dataReferenceSrv.getAllCountries()
    .pipe(
      map((data: { data: Country[]; }) => {
        this.countries =  data.data;
      }))
    .subscribe();
  }

}
