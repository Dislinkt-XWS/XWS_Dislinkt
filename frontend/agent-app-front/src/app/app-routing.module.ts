import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AllCompaniesComponent } from './components/all-companies/all-companies.component';
import { AllJobOffersComponent } from './components/all-job-offers/all-job-offers.component';
import { ApproveCompanyComponent } from './components/approve-company/approve-company.component';
import { CompanyComponent } from './components/company/company.component';
import { CreateCompanyComponent } from './components/create-company/create-company.component';
import { CreateJobOfferComponent } from './components/create-job-offer/create-job-offer.component';
import { LoginComponent } from './components/login/login.component';
import { ProfileComponent } from './components/profile/profile.component';
import { RegistrationComponent } from './components/registration/registration.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'signup', component: RegistrationComponent },
  { path: 'register-company', component: CreateCompanyComponent },
  { path: 'approve', component: ApproveCompanyComponent },
  { path: 'companies', component: AllCompaniesComponent },
  { path: 'companies/:id', component: CompanyComponent },
  { path: 'my-companies', component: AllCompaniesComponent },
  { path: '', pathMatch: "full", component: LoginComponent },
  { path: 'create-job-offer', component: CreateJobOfferComponent},
  { path: 'job-offers', component: AllJobOffersComponent },
  {path: 'profile', component: ProfileComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
