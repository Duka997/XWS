import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from '../app/login/login.component';
import {SignupComponent} from '../app/signup/signup.component'
import { HomepageComponent } from './header/homepage/homepage.component';
import { HomepageAdminComponent } from './header/homepage-admin/homepage-admin.component';
import { HomepageAgentComponent } from './header/homepage-agent/homepage-agent.component';
import { AddMarkaAutomobila } from './components/addMarkuAutomobila/addMarkuAutomobila.component';
import { DodajKlasuAutomobila } from './components/addCarClass/addCarClass.component';
import { DodajTipMjenjaca } from './components/addGearboxType/addGearboxType.component';
import { AddTipGoriva } from './components/addFuelType/addFuelType.component';
import { ListUsersComponent } from './components/list-users/list-users.component';
import { ListRequestComponent } from './components/list-request/list-request.component';

import { AddCarComponent } from './components/addCar/addCar.component';
import { DodajOglasComponent } from './components/addAdd/addAdd.component';
import { RentRequestsComponent } from './header/homepage-agent/rent-requests/rent-requests.component';
import { HomepageUserComponent } from './header/homepageUser/homepage-user.component';
import { UserCartComponent } from './header/homepageUser/userCart/user-cart.component';
import { SearchComponent } from './components/search/search.component';
import { PriceListComponent } from './components/price-list/price-list.component';
import { CommentAndGradesComponent } from './components/comment-and-grades/comment-and-grades.component';
import { CommentAdminComponent } from './components/comment-admin/comment-admin.component';
import { CarStatComponent } from './components/carStatistics/carStat.component';
import { OccupationComponent } from './components/occupation/occupation.component';
import {Inbox} from './components/inbox/inbox.component';
import {MessageComponent} from '../app/message/message.component'
import { from } from 'rxjs';
import { CarEntitiesComponent } from './components/car-entities/car-entities.component';
import { ReportComponent } from './components/report/report.component';
import { ProfileComponent } from './components/profile/profile.component';
import { UserRequstHistoryComponent } from './components/userRequestHistory/userRequestHistory.component';


const routes: Routes = [
  { path: '', redirectTo: 'homepage', pathMatch: 'full' },
  { path: 'homepage', component: HomepageComponent,
    children: [
      { path: '', redirectTo: 'login', pathMatch: 'full' },
      { path: 'login', component: LoginComponent },
      { path: 'signup', component: SignupComponent },
      { path: 'search', component: SearchComponent },
    ]
  }, 

  { path: 'homepageAdmin', component: HomepageAdminComponent,
    children: [
      { path: '', redirectTo: 'carEntities', pathMatch: 'full' },
      { path: 'addAgent', component: SignupComponent },
      { path: 'comments', component: CommentAdminComponent },
      { path: 'addCarMark', component: AddMarkaAutomobila },
      { path: 'addCarClass', component: DodajKlasuAutomobila },
      { path: 'addGearboxType', component: DodajTipMjenjaca },
      { path: 'addFuelType', component: AddTipGoriva },
      { path: 'users', component: ListUsersComponent},
      { path: 'requests', component: ListRequestComponent},
      { path: 'carEntities', component: CarEntitiesComponent },
      { path: 'profile', component: ProfileComponent },
    ]
  }, 

  { path: 'homepageAgent', component: HomepageAgentComponent,
    children: [
      { path: 'signup', component: SignupComponent },
      { path: 'search', component: SearchComponent },
      { path: 'pricelist', component: PriceListComponent },
      { path: 'commentsAndGrades', component: CommentAndGradesComponent},
      { path: 'requests', component: RentRequestsComponent },
      { path: 'carStat', component: CarStatComponent },
      { path: 'addCar', component: AddCarComponent },
      { path: 'addAdd', component: DodajOglasComponent },
      { path: 'carOccupation', component: OccupationComponent },
      { path: 'inbox', component: Inbox  },
      { path: 'inbox/reply', component: MessageComponent},
      { path: 'report', component: ReportComponent  },
      { path: 'profile', component: ProfileComponent },
    ]
  },

  { path: 'homepageUser', component: HomepageUserComponent ,
    children: [
      //{ path: 'addAdd', component: DodajOglasComponent },
      { path: 'userAd', component: DodajOglasComponent  },
      { path: 'inbox', component: Inbox  },
  ]
  },
  { path: 'homepageUser/reply', component:  MessageComponent},
  { path: 'homepageUser/addAdd', component:  DodajOglasComponent},
  { path: 'homepageUser/userCart', component: UserCartComponent  }, 
  { path: 'homepageUser/userCart/inbox', component: Inbox  },
  { path: 'homepageUser/userCart/userAd', component: DodajOglasComponent  },
  { path: 'homepageUser/userRequests', component: UserRequstHistoryComponent  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
