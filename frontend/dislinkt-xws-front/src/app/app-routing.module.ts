import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ChatComponent } from './components/chat/chat.component';
import { CreateJobOfferComponent } from './components/create-job-offer/create-job-offer.component';
import { JobOffersComponent } from './components/job-offers/job-offers.component';
import { LoginComponent } from './components/login/login.component';
import { NewsfeedComponent } from './components/newsfeed/newsfeed.component';
import { RegistrationComponent } from './components/registration/registration.component';
import { SearchComponent } from './components/search/search.component';
import { UserProfileComponent } from './components/user-profile/user-profile.component';

const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'signup', component: RegistrationComponent },
  { path: 'feed', component: NewsfeedComponent },
  { path: 'profile/:id', component: UserProfileComponent },
  { path: 'search/:criteria', component: SearchComponent},
  { path: 'job-offers', component: JobOffersComponent},
  { path: 'create-job-offer', component: CreateJobOfferComponent},
  { path: 'chat', component: ChatComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
