import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from '../app/login/login.component';
import {SignupComponent} from '../app/signup/signup.component'
import { HomepageComponent } from './header/homepage/homepage.component';
import { HomepageAdminComponent } from './header/homepage-admin/homepage-admin.component';
import { HomepageAgentComponent } from './header/homepage-agent/homepage-agent.component';

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
    ]
  }, 

  { path: 'homepageAgent', component: HomepageAgentComponent,
    children: [
      
    ]
  }, 
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
