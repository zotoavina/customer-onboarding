import { Component } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-onboarding-form',
  templateUrl: './onboarding-form.component.html',
  styleUrls: ['./onboarding-form.component.css']
})
export class OnboardingFormComponent {
  onboardingFirstForm: FormGroup;
  onboardingSecondForm: FormGroup;
  onboardingThirdForm: FormGroup;
  isLinear = false;

  purposes: string[] = [ "Investment porfolio", "Account to operatelocally", 
  "Account to operate overseas", "Energy & commodiƟes financing"];

  entities: string[] = [ "Investment porfolio", "Account to operatelocally", 
  "Account to operate overseas", "Energy & commodiƟes financing"];

  activities: string[] = [ "Investment porfolio", "Account to operatelocally", 
  "Account to operate overseas", "Energy & commodiƟes financing"];

  countries: string[] = [ "Investment porfolio", "Account to operatelocally", 
  "Account to operate overseas", "Energy & commodiƟes financing"];

  constructor(private formBuilder: FormBuilder) {
    this.onboardingFirstForm = this.formBuilder.group({
      purpose: ['', Validators.required],
      company: ['', Validators.required],
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
  get document() {
    return this.onboardingThirdForm.get('document') as FormControl;
  }

  validation() {
    console.log(this.document);
  }
}
