import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { OnboardingFormComponent } from './pages/customer/onboarding-form/onboarding-form.component';

const routes: Routes = [
  {
    path:'onboarding',
    component: OnboardingFormComponent
  },
  {
    path: '',
    loadChildren: () =>
      import('./pages/pages.module').then(
        (m) => m.PagesModule
      ),
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
