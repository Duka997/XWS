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


const routes: Routes = [
  { path: '', redirectTo: 'homepage', pathMatch: 'full' },
  { path: 'homepage', component: HomepageComponent,
    children: [
      { path: '', redirectTo: 'login', pathMatch: 'full' },
      { path: 'login', component: LoginComponent },
      { path: 'signup', component: SignupComponent },
    ]
  }, 

  { path: 'homepageAdmin', component: HomepageAdminComponent,
    children: [
      { path: 'addAgent', component: SignupComponent },
      { path: 'addCarMark', component: AddMarkaAutomobila },
      { path: 'addCarClass', component: DodajKlasuAutomobila },
      { path: 'addGearboxType', component: DodajTipMjenjaca },
      { path: 'addFuelType', component: AddTipGoriva },

      { path: 'users', component: ListUsersComponent},
      { path: 'requests', component: ListRequestComponent},

      { path: 'addCar', component: AddCarComponent },

    ]
  }, 

  { path: 'homepageAgent', component: HomepageAgentComponent,
    children: [
      { path: 'signup', component: SignupComponent }
    ]
  }, 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
