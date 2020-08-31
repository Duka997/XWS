import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/model/user';
import { AuthService } from 'src/app/services/auth.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-list-users',
  templateUrl: './list-users.component.html',
  styleUrls: ['./list-users.component.css']
})
export class ListUsersComponent implements OnInit {
  users: User[];

  constructor(private authService: AuthService,
    private _toastr: ToastrService) { }

  ngOnInit(): void {
    this.authService.getUsers().subscribe(
      data=>{
        this.users = data;
      }
    )
  }

  refresh(){
    this.authService.getUsers().subscribe(
      data=>{
        this.users = data;
      }
    );
  }

  delete(id:number):void{
    this.authService.deleteUser(id).subscribe(
      data=>{
        this._toastr.success("User successfully deleted", "Delete");
        this.refresh();
      },
      error=>{
        this._toastr.error("Error", "Delete");
      }
    );
  }

  disable(id):void{
    this.authService.disableUser(id).subscribe(
      data=>{
        this._toastr.success("User successfully disabled", "Disable");
        this.refresh();
      },
      error=>{
        this._toastr.error("Error", "Disable");
      }
    );
  }

  enable(id):void{
    this.authService.enableUser(id).subscribe(
      data=>{
        this._toastr.success("User successfully enabled", "Enable");
        this.refresh();
      },
      error=>{
        this._toastr.error("Error", "Enable");
      }
    );
  }

  checkPrivilege(roles: string[]){
    if(roles.indexOf('CREATE_RESERVATION') > -1)
        return true;
    else
        return false;
  }

  disableRent(id: number){
    this.authService.disableEnableRent(id, false).subscribe(
      data=>{
        this._toastr.success("User successfully disabled for renting", "Rent");
        this.refresh();
      },
      error=>{
        this._toastr.error("Error", "Rent");
      }
    );
  }

  enableRent(id: number){
    this.authService.disableEnableRent(id, true).subscribe(
      data=>{
        this._toastr.success("User successfully enabled for renting", "Rent");
        this.refresh();
      },
      error=>{
        this._toastr.error("Error", "Rent");
      }
    );
  }
}
