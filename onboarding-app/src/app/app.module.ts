import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { OnboardingFormComponent } from './customer/onboarding-form/onboarding-form.component';
import { DashboardComponent } from './processor/dashboard/dashboard.component';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatStepperModule} from '@angular/material/stepper';
import {MatButtonModule} from '@angular/material/button';
import { FooterComponent } from './shared/component/footer/footer.component';
import { AppIdGenerationComponent } from './customer/app-id-generation/app-id-generation.component';
import { ApproverPageComponent } from './approver-page/approver-page.component';

@NgModule({
  declarations: [
    AppComponent,
    OnboardingFormComponent,
    DashboardComponent,
    FooterComponent,
    AppIdGenerationComponent,
    ApproverPageComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    MatButtonModule,
    MatFormFieldModule,
    MatStepperModule,
    MatInputModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
