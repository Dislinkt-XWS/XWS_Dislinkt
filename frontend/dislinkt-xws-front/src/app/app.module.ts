import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { MatStepperModule } from '@angular/material/stepper';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { TokenInterceptor } from './interceptor/token-interceptor';
import { NewsfeedComponent } from './components/newsfeed/newsfeed.component';
import { DateAgoPipe } from './pipes/date-ago.pipe';
import { UserProfileComponent } from './components/user-profile/user-profile.component';
import { MatSnackBarModule } from '@angular/material/snack-bar'
import { MatCheckboxModule} from '@angular/material/checkbox';
import { MatSelectModule } from '@angular/material/select';
import { MatInputModule } from '@angular/material/input';
import { MatChipsModule } from '@angular/material/chips';
import { MatIconModule } from '@angular/material/icon';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { SearchComponent } from './components/search/search.component';
import { NavigationComponent } from './components/navigation/navigation.component';
import { JobOffersComponent } from './components/job-offers/job-offers.component';
import { CreateJobOfferComponent } from './components/create-job-offer/create-job-offer.component';
import { ChatComponent } from './components/chat/chat.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegistrationComponent,
    NewsfeedComponent,
    DateAgoPipe,
    UserProfileComponent,
    SearchComponent,
    NavigationComponent,
    JobOffersComponent,
    CreateJobOfferComponent,
    ChatComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatStepperModule,
    HttpClientModule,
    ReactiveFormsModule,
    FormsModule,
    MatSnackBarModule,
    MatInputModule,
    MatChipsModule,
    MatIconModule,
    MatDatepickerModule,
    MatNativeDateModule,
    MatCheckboxModule,
    MatSelectModule
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
