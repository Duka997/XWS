import { Component, OnInit } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { RequestService } from 'src/app/services/requests.service';
import { IAllRequests } from 'src/app/header/homepage-agent/rent-requests/irequests.all';
import { IRentRequest } from 'src/app/header/homepageUser/userCart/IRequestRent';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { Vozilo } from 'src/app/model/car';
import { AdsService } from 'src/app/services/ads.service';
import { IReport } from 'src/app/model/Report';
import { Oglas } from 'src/app/model/Ad';

@Component({
    selector: 'app-report',
    templateUrl: './report.component.html'
  })
  export class ReportComponent implements OnInit {

    all: IRentRequest[];
    pending: IRentRequest[];
    paid: IRentRequest[];
    finished: IRentRequest[];
    finishedReq: IRentRequest;

    kilometrage: number;
    commentReport: string = '';

    modal: NgbModalRef;
    mode: string = 'ADD';

    car: Vozilo;
    allAds: Oglas[];
    ad: Oglas;
    report: IReport;
  
    constructor(private _toastr: ToastrService,
                private requestService: RequestService,
                private ngbModal: NgbModal,
                private adsService: AdsService) {
        this.finishedReq = {
            bundleId:null,
            doo:null,
            od:null,
            mjestoPreuzimanja:null,
            id:null,
            oglasId:null
        }

    }
  
  
    ngOnInit(): void {
        this.getRequests();
        this.getAds();
    }

    getRequests() {
        var userId = parseInt(localStorage.getItem('id'));
        this.requestService.getRequests(userId)
          .subscribe((data: IAllRequests) => {
            this.all = data.all;
            this.pending = data.pending;
            this.paid = data.paid;
            this.finished = data.finished;
            console.log("requests: ", data);
          });
    }

    getAds() {
        var userId = parseInt(localStorage.getItem('id'));
        this.adsService.getAds()
        .subscribe(data => {
            this.allAds = data;
            console.log("ads: ", data);
        });
    }

    openRequest(sadrzaj: any, selectedFinished: IRentRequest) {
        this.clear();
        //this.refresh();

        this.allAds.forEach(element => {
            if(element.id == selectedFinished.oglasId) {
                this.ad = element;
                console.log("Ad: ", this.ad);
            }
        });

        this.mode = 'ADD';
        this.modal = this.ngbModal.open(sadrzaj,{size: 'lg'});
    }

    clear(){
        this.finishedReq.id = null;
        this.finishedReq.bundleId =null;
        this.finishedReq.doo=null;
        this.finishedReq.od=null;
        this.finishedReq.mjestoPreuzimanja=null;
        this.finishedReq.oglasId=null;
      }

    sendReport() {
        if (this.commentReport == undefined || this.commentReport == "" ||
        this.kilometrage == undefined || this.kilometrage == null || this.kilometrage == 0) {
            alert("Enter info properly!");
            return;
        }

        console.log(this.commentReport, " ", this.kilometrage);

        let newReport: IReport = {
            id: null,
            kilometraza: this.kilometrage,
            commentReport: this.commentReport,
            oglasId: this.ad.id,
            userId: this.ad.user.id,
          };
          this.requestService.createReport(newReport).subscribe(data => {
            this.report = data;
            console.log("Report: ", this.report);
          }, err => {
            alert("Error");
          });

    }
}