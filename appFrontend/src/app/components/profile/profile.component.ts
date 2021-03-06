import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/model/user';
import { AuthService } from 'src/app/services/auth.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  role: string = '';
  username: string = '';
  user: User = {id: null, address: null, deleted: null,email: null,enabled: null,imeKompanije: null,isAdmin: null,name: null,numCancelled: null,
    password: null,phone: null,poslovniID: null,roles: null,surname: null,username: null};
  mode: string = 'VIEW';

  constructor(private authService: AuthService,
    private _toastr: ToastrService) {
      
     }

  ngOnInit(): void {
    this.role = localStorage.getItem('user-role');
    this.username = localStorage.getItem('username');
    this.authService.getUser(this.username).subscribe(
      data => {
        this.user = data;
      },
      error => {
        this._toastr.error("error geting user", "User");
      }
    );
  }

  edit(){
    this.mode = 'EDIT';
  }

  cancel(){
    this.authService.getUser(this.username).subscribe(
      data => {
        this.user = data;
      },
      error => {
        this._toastr.error("error geting user", "User");
      }
    );
    this.mode = 'VIEW';
  }

  save(){
    this.authService.editUser(this.user).subscribe(
      data => {
        this._toastr.success("Information successfully updated", "Edit");
        this.mode = 'VIEW';
      },
      error => {
        this._toastr.error("Error editing user", "Edit user");
      }
    );
  }

}
