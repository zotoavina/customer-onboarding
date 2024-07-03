import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { OnboardingFormComponent } from './pages/customer/onboarding-form/onboarding-form.component';
import { LoginComponent } from './pages/login/login.component';

const routes: Routes = [
  {
    path:'',
    component: OnboardingFormComponent
  },
  {
    path:'login',
    component: LoginComponent
  },
  {
    path: 'mcb',
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
