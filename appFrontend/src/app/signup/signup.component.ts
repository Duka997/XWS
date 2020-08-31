import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';
import { Router, ActivatedRoute } from '@angular/router';
import { User } from '../model/user';
import { ToastrService } from 'ngx-toastr';

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
               private toastr: ToastrService,
              private route: ActivatedRoute,) { 
                 this.user = {
                   name : '',
                   surname : '',
                   username : '',
                   password: '',
                   phone: '',
                   roles: [],
                   enabled: null,
                   deleted: null,
                   isAdmin: null,
                   id: null,
                   imeKompanije:'',
                   poslovniID:'',
                   email:'',
                   address:'',
                   numCancelled: 0
                 }
               }

  ngOnInit(): void {
    this.role = localStorage.getItem('user-role');
  }

  signUp():void{
    if(this.user.username.trim() == "" || this.user.name.trim() == "" || this.user.surname.trim() == "" || 
      this.user.email.trim() == "" || this.user.address.trim() == "" || this.user.password.trim() == ""){
        this.toastr.warning('Please, fill all fields!', 'Sign up');
        return;
    }
    if(this.role == 'ROLE_ADMIN'){
      this.user.roles.push("ROLE_AGENT");
    }else{
      this.user.roles.push("ROLE_USER");
    }
    
    this.authService.register(this.user).subscribe(
      data => {
        this.toastr.success('User successfully registered!', 'Sign up');
        this.router.navigate(["login"], { relativeTo: this.route.parent });
      },
      error => {
        this.toastr.error("Error during registration", "Sign up!");
      }
    );
  }

}
