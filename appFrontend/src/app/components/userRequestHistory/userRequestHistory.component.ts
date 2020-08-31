import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AdsService } from 'src/app/services/ads.service';
import { RequestService } from 'src/app/services/requests.service';
import { IAllRequests } from 'src/app/header/homepage-agent/rent-requests/irequests.all';
import { IRentRequest } from 'src/app/header/homepageUser/userCart/IRequestRent';
import { Oglas } from 'src/app/model/add';
import { IRentRequestHistory } from './model/IRequestRent';
import { IAllRequestsHistory } from './model/irequests.all';

@Component({
    selector: 'app-user-request-history',
    templateUrl: './userRequestHistory.component.html'
  })
  export class UserRequstHistoryComponent implements OnInit {

    all: IRentRequestHistory[];
    pending: IRentRequestHistory[];
    paid: IRentRequestHistory[];
    finished: IRentRequestHistory[];

    ads: Oglas[];
    
    constructor(  private router: Router,
        private toastr: ToastrService,
        private requestService: RequestService,
        private adsService: AdsService) { }
    
    ngOnInit(): void {
        this.getRequests();
    }

    getRequests() {
        var userId = parseInt(localStorage.getItem('id'));
        this.requestService.getRequestsHistory(userId)
          .subscribe((data: IAllRequestsHistory) => {
            this.all = data.all;
            this.pending = data.pending;
            this.paid = data.paid;
            this.finished = data.finished;
            console.log("data: ", data);
        });

    }

    posaljiPor(userId:any){
        localStorage.setItem("primalac", userId);
        this.router.navigate(['homepageUser/reply']);
    }
  

  }