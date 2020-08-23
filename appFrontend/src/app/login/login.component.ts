import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthService } from '../services/auth.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  username: '';
  password: '';

  constructor(private router: Router, private route: ActivatedRoute, private authService: AuthService,private toastr: ToastrService) { }

  ngOnInit(): void {
  }

  signup(){
    this.router.navigate(["signup"], { relativeTo: this.route.parent });
    //ovo relativeTo da se otvori u istom ruteru u kome je, posto imamo tri rutera, za svaku rolu
  }

  login(){
    let login = {
      username: this.username,
      password: this.password
    }

    this.authService.login(login).subscribe(
      data =>{
        localStorage.clear();
        localStorage.setItem("user-token", data.accessToken);
        localStorage.setItem("expires-in", data.expiresIn);
        localStorage.setItem("refresh-token", data.refreshToken);
        localStorage.setItem("username", data.username);
        localStorage.setItem("user-role", data.role);
        localStorage.setItem("id", data.id);

        if(data.role == 'ROLE_ADMIN'){
          this.router.navigate(['homepageAdmin']);
        }else if(data.role != ''){
          this.router.navigate(['homepageAgent']);
        }

        this.toastr.success('Successfully logged in', 'Login'); //ima success, error, warning i info(tekst poruke, naslov)
      },
      error =>{
        alert("Try again!");
      }

    )
  }
}
