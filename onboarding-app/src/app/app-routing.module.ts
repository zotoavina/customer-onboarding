import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { OnboardingFormComponent } from './customer/onboarding-form/onboarding-form.component';
import { DashboardComponent } from './processor/dashboard/dashboard.component';

const routes: Routes = [
  {
    path:'',
    component: OnboardingFormComponent
  },
  {
    path:'dashboard',
    component: DashboardComponent
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
