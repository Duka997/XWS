import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AdsService } from 'src/app/services/ads.service';
import { RequestService } from 'src/app/services/requests.service';
import { IAllRequests } from 'src/app/header/homepage-agent/rent-requests/irequests.all';
import { IRentRequest } from 'src/app/header/homepageUser/userCart/IRequestRent';

@Component({
    selector: 'app-user-request-history',
    templateUrl: './userRequestHistory.component.html'
  })
  export class UserRequstHistoryComponent implements OnInit {

    all: IRentRequest[];
    pending: IRentRequest[];
    paid: IRentRequest[];
    finished: IRentRequest[];
    
    constructor(  private router: Router,
        private toastr: ToastrService,
        private requestService: RequestService) { }
    
    ngOnInit(): void {
        this.getRequests();
    }

    getRequests() {
        var userId = parseInt(localStorage.getItem('id'));
        this.requestService.getRequestsHistory(userId)
          .subscribe((data: IAllRequests) => {
            this.all = data.all;
            this.pending = data.pending;
            this.paid = data.paid;
            this.finished = data.finished;
            console.log("data: ", data);
          });
    }
  

  }