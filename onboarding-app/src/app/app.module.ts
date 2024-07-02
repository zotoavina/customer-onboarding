import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { OnboardingFormComponent } from './pages/customer/onboarding-form/onboarding-form.component';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatStepperModule} from '@angular/material/stepper';
import {MatButtonModule} from '@angular/material/button';
import { PagesComponent } from './pages/pages.component';
import { FooterComponent } from './shared/component/footer/footer.component';
import { NavbarComponent } from './shared/component/navbar/navbar.component';
import {HttpClientModule} from "@angular/common/http";
import { LoginComponent } from './pages/login/login.component';
import { AuthGuard } from './shared/security/auth.guard';
import { HttpInterceptorRequest } from './services/http-request-interceptor.service';
import { NgxExtendedPdfViewerModule } from 'ngx-extended-pdf-viewer';

@NgModule({
  declarations: [
    AppComponent,
    OnboardingFormComponent,
    PagesComponent,
    FooterComponent,
    NavbarComponent,
    LoginComponent
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
    MatInputModule,
    HttpClientModule,
    NgxExtendedPdfViewerModule
    
  ],
  providers: [AuthGuard, HttpInterceptorRequest],
  bootstrap: [AppComponent]
})
export class AppModule { }
