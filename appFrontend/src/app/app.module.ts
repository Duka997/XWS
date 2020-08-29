import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { NgModule } from '@angular/core';
import {RouterModule} from '@angular/router'
import { ToastrModule } from 'ngx-toastr';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { SignupComponent } from './signup/signup.component';

import { MatSliderModule } from '@angular/material/slider';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatButtonModule} from '@angular/material/button';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';

import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AuthInterceptor } from './http-interceptors/auth-interceptor';
import { HomepageComponent } from './header/homepage/homepage.component';
import { HomepageAdminComponent } from './header/homepage-admin/homepage-admin.component';
import { HomepageAgentComponent } from './header/homepage-agent/homepage-agent.component';
import { FormsModule } from '@angular/forms';
import { AddMarkaAutomobila } from './components/addMarkuAutomobila/addMarkuAutomobila.component';
import { DodajKlasuAutomobila } from './components/addCarClass/addCarClass.component';
import { DodajTipMjenjaca } from './components/addGearboxType/addGearboxType.component';
import { AddTipGoriva } from './components/addFuelType/addFuelType.component';
import { ListUsersComponent } from './components/list-users/list-users.component';
import { ListRequestComponent } from './components/list-request/list-request.component';
import { AddCarComponent } from './components/addCar/addCar.component';
import { DodajOglasComponent } from './components/addAdd/addAdd.component';
import { SearchComponent } from './components/search/search.component';
import { RentRequestsComponent } from './header/homepage-agent/rent-requests/rent-requests.component';
import { HomepageUserComponent } from './header/homepageUser/homepage-user.component';
import { UserCartComponent } from './header/homepageUser/userCart/user-cart.component';
import { PriceListComponent } from './components/price-list/price-list.component';
import { CommentAndGradesComponent } from './components/comment-and-grades/comment-and-grades.component';
import { CommentAdminComponent } from './components/comment-admin/comment-admin.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { CarStatComponent } from './components/carStatistics/carStat.component';
import { OccupationComponent } from './components/occupation/occupation.component';
import {Inbox} from '../app/components/inbox/inbox.component';
import { MessageComponent } from './message/message.component';

@NgModule({
  declarations: [

    AppComponent,
    LoginComponent,
    SignupComponent,
    HomepageComponent,
    HomepageAdminComponent,
    HomepageAgentComponent,
    RentRequestsComponent,
    HomepageUserComponent,
    UserCartComponent,
    AddMarkaAutomobila,
    DodajKlasuAutomobila,
    DodajTipMjenjaca,
    AddTipGoriva,
    ListUsersComponent,
    ListRequestComponent,
    AddCarComponent,
    SearchComponent,
    DodajOglasComponent,
    PriceListComponent,
    CommentAndGradesComponent,
    CommentAdminComponent,
    CarStatComponent,
    OccupationComponent,
    Inbox,
    MessageComponent,
  ],
  imports: [
    HttpClientModule,
    BrowserModule,
    AppRoutingModule,
    MatSliderModule,
    MatToolbarModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    RouterModule,
    NgbModule,
    FormsModule,
    BrowserAnimationsModule,
    ToastrModule.forRoot({
      timeOut: 3000,
      preventDuplicates: true,
      resetTimeoutOnDuplicate: true,
      progressBar: true,
      enableHtml: true
    }),
  ],
  providers: [{ provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true }],
  bootstrap: [AppComponent]
})
export class AppModule { }
