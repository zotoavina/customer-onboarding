import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule, Routes } from '@angular/router';
import { PagesComponent } from './pages.component';
import { DashboardComponent } from './processor/dashboard/dashboard.component';
import { AppIdGenerationComponent } from './customer/app-id-generation/app-id-generation.component';
import { ApproverPageComponent } from './approver/approver-page/approver-page.component';
import { AuthGuard } from '../shared/security/auth.guard';
import { VisualisationComponent } from './processor/visualisation/visualisation.component';
import { NgxExtendedPdfViewerModule } from 'ngx-extended-pdf-viewer';

const ROUTES: Routes = [
  {
    path: '',
    component: PagesComponent,
    children: [
      {
        path:'dashboard',
        component: DashboardComponent,
        canActivate: [AuthGuard],
        data: {
          role: 'PROCESSOR'
        }
      },
      {
        path:'visualisation',
        component: VisualisationComponent,
        canActivate: [AuthGuard],
        data: {
          role: 'PROCESSOR'
        }
      },
      {
        path:'submitted/:uuid',
        component: AppIdGenerationComponent
      },
      {
        path:'approver',
        component: ApproverPageComponent,
        canActivate: [AuthGuard],
        data: {
          role: 'APPROVER'
        }
      }
    ],
  },
];

@NgModule({
  declarations: [
    DashboardComponent, 
    AppIdGenerationComponent, 
    ApproverPageComponent,
    VisualisationComponent],
  imports: [CommonModule, NgxExtendedPdfViewerModule, RouterModule.forChild(ROUTES)]
})

export class PagesModule { }
