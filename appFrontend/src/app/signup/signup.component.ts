import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { Router, ActivatedRoute } from '@angular/router';
import { User } from '../model/user';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})
export class SignupComponent implements OnInit {

  user:User;
  role: string = '';

  constructor( private authService: AuthService, 
               private router: Router,
              private route: ActivatedRoute,) { 
                 this.user = {
                   name : '',
                   surname : '',
                   username : '',
                   password: '',
                   phone: '',
                   roles: [],
                   enabled: null,
                   isAdmin: null,
                   id: null,
                   imeKompanije:'',
                   poslovniID:'',
                   email:'',
                   address:''
                 }
               }

  ngOnInit(): void {
    this.role = localStorage.getItem('user-role');
  }

  signUp():void{
    if(this.role == 'ROLE_ADMIN'){
      this.user.roles.push("ROLE_AGENT");
    }else{
      this.user.roles.push("ROLE_USER");
    }
    
    this.authService.register(this.user).subscribe(
      data => {
        alert('User successfully registered!');
        this.router.navigate(["login"], { relativeTo: this.route.parent });
      },
      error => {
        alert("Error during registration");
      }
    );
  }

}
