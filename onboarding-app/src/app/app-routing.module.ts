import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { OnboardingFormComponent } from './customer/onboarding-form/onboarding-form.component';
import { DashboardComponent } from './processor/dashboard/dashboard.component';
import { AppIdGenerationComponent } from './customer/app-id-generation/app-id-generation.component';
import { ApproverPageComponent } from './approver-page/approver-page.component';

const routes: Routes = [
  {
    path:'',
    component: OnboardingFormComponent
  },
  {
    path:'dashboard',
    component: DashboardComponent
  },
  {
    path:'submitted',
    component: AppIdGenerationComponent
  },
  {
    path:'approver',
    component: ApproverPageComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
