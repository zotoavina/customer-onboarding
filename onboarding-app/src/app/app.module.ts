import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { OnboardingFormComponent } from './customer/onboarding-form/onboarding-form.component';
import { DashboardComponent } from './processor/dashboard/dashboard.component';

@NgModule({
  declarations: [
    AppComponent,
    OnboardingFormComponent,
    DashboardComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
