import { Component, OnInit } from '@angular/core';
import { RequestService } from 'src/app/services/requests.service';
import { ToastrService } from 'ngx-toastr';
import { IAllRequests } from './irequests.all';
import { IRentRequest } from 'src/app/components/userCart/IRequestRent';

@Component({
  selector: 'app-rent-request',
  templateUrl: './rent-requests.component.html'
})
export class RentRequestsComponent implements OnInit {

    requests = [];

    all: IRentRequest[];
    pending: IRentRequest[];
    paid: IRentRequest[];
    finished: IRentRequest[];

    constructor(private requestService: RequestService,
        private toastr: ToastrService) { }

    ngOnInit(): void {
        this.getRequests();
    }

    getRequests() {
        var userId = parseInt(localStorage.getItem('id'));
        this.requestService.getRequests(userId)
          .subscribe((data: IAllRequests) => {
            this.all = data.all;
            this.pending = data.pending;
            this.paid = data.paid;
            this.finished = data.finished;
            console.log("data: ", data);
          });
    }

    accept(req: IRentRequest) {
        console.log("potvrdjen: ",req);
        if (req.bundleId == -1)
            this.requestService.acceptRequest(req.id).subscribe(
                data => { this.getRequests() },
                error => { 
                    if(error.status == 500) {
                        this.toastr.warning('Car is already rented for that period', 'Rent');
                    } 
                });
        else
        this.requestService.acceptBundle(req.bundleId)
            .subscribe(data => { this.getRequests() },
            error => { 
                if(error.status == 500) {
                    this.toastr.warning('Car is already rented for that period', 'Rent');
                } 
             });
    }

    cancel(req: IRentRequest) {
        if (req.bundleId == -1)
            this.requestService.cancelRequest(req.id).subscribe(
                data => { 
                    this.getRequests() 
                }, error => { this.toastr.error(error.error) }
            );
        else
            this.requestService.cancelBundle(req.bundleId).subscribe(
                data => { 
                    this.getRequests() 
                }, error => { this.toastr.error(error.error) }
            );

    }

}