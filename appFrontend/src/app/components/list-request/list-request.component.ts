import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/model/user';
import { AuthService } from 'src/app/services/auth.service';
import { ToastrService } from 'ngx-toastr';

@Component({
  selector: 'app-list-request',
  templateUrl: './list-request.component.html',
  styleUrls: ['./list-request.component.css']
})
export class ListRequestComponent implements OnInit {

  users: User[];

  constructor(private authService: AuthService,
    private _toastr: ToastrService) { }

  ngOnInit(): void {
    this.authService.getRegistrationRequests().subscribe(
      data=>{
        this.users = data;
      }
    )
  }

  refresh(){
    this.authService.getRegistrationRequests().subscribe(
      data=>{
        this.users = data;
      }
    );
  }

  
  accept(id):void{
    this.authService.accept(id).subscribe(
      data=>{
        this._toastr.success("User accepted", "Accept");
        this.refresh();
      },
      error=>{
        this._toastr.error("Error", "Accept");
      }
    );
  }

  reject(id):void{
    this.authService.reject(id).subscribe(
      data=>{
        this._toastr.success("User rejected", "Reject");
        this.refresh();
      },
      error=>{
        this._toastr.error("Error", "Reject");
      }
    );
  }
}
