import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatCardModule } from '@angular/material/card';
import { MatInputModule } from '@angular/material/input';
import { MatIconModule } from '@angular/material/icon';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { TokenInterceptor } from './interceptor/token-interceptor';
import { CreateCompanyComponent } from './components/create-company/create-company.component';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { MatCheckboxModule} from '@angular/material/checkbox';
import { ApproveCompanyComponent } from './components/approve-company/approve-company.component';
import { AllCompaniesComponent } from './components/all-companies/all-companies.component';
import { CompanyComponent } from './components/company/company.component'
import { MatSelectModule } from '@angular/material/select';
import { CreateJobOfferComponent } from './components/create-job-offer/create-job-offer.component';
import { AllJobOffersComponent } from './components/all-job-offers/all-job-offers.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegistrationComponent,
    CreateCompanyComponent,
    ApproveCompanyComponent,
    AllCompaniesComponent,
    CompanyComponent,
    CreateJobOfferComponent,
    AllJobOffersComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatCardModule,
    MatInputModule,
    MatIconModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    MatSnackBarModule,
    MatSelectModule,
    MatCheckboxModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
