import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ApproveCompanyComponent } from './components/approve-company/approve-company.component';
import { CreateCompanyComponent } from './components/create-company/create-company.component';
import { LoginComponent } from './components/login/login.component';
import { RegistrationComponent } from './components/registration/registration.component';

const routes: Routes = [
  { path: 'login', component: LoginComponent },
  { path: 'signup', component: RegistrationComponent },
  { path: 'register-company', component: CreateCompanyComponent },
  { path: 'approve', component: ApproveCompanyComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
