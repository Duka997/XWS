import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AdsService } from 'src/app/services/ads.service';
import { Oglas } from 'src/app/model/Ad';
import {Poruka} from '../../model/poruka';
import {PorukaService} from '../../services/poruka.service';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { Vozilo } from 'src/app/model/car';

@Component({
  selector: 'app-homepage-user',
  templateUrl: './homepage-user.component.html'
})
export class HomepageUserComponent implements OnInit {

  constructor(  private router: Router) { }

  ngOnInit(): void {

  }

  logout(){
    localStorage.removeItem("user-token");
    localStorage.removeItem("expires-in");
    localStorage.removeItem("username");
    localStorage.removeItem("user-role");
    localStorage.removeItem("refresh-token");
    localStorage.removeItem("id");
    this.router.navigate(['homepage']);
  }
  
}