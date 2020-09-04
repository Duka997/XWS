import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AdsService } from 'src/app/services/ads.service';
import { RequestService } from 'src/app/services/requests.service';
import { Oglas } from 'src/app/model/add';
import { IRentRequestHistory } from './model/IRequestRent';
import { IAllRequestsHistory } from './model/irequests.all';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { CommentService } from 'src/app/services/comment.service';
import { GradeService } from 'src/app/services/grade.service';

@Component({
    selector: 'app-user-request-history',
    templateUrl: './userRequestHistory.component.html',
    styleUrls: ['./userRequestHistory.component.css']
  })
  export class UserRequstHistoryComponent implements OnInit {

    all: IRentRequestHistory[];
    pending: IRentRequestHistory[];
    paid: IRentRequestHistory[];
    finished: IRentRequestHistory[];
    request: IRentRequestHistory;

    ads: Oglas[];
    tekst: string;
    ocjena: number = 0;
    myModal: NgbModalRef;
    userRole: string;
    checkCommentFlag: boolean = false;
    checkGradeFlag: boolean = false;
    
    constructor(  private router: Router,
        private _toastr: ToastrService,
        private requestService: RequestService,
        private adsService: AdsService,
        private modalService: NgbModal,
        private commentService: CommentService,
         private gradeService: GradeService,) { }
        
    
    ngOnInit(): void {
        this.getRequests();
        this.userRole = localStorage.getItem("user-role");
    }

    checkComment(id) {
      this.commentService.provjeriKomentar(id, localStorage.getItem('username')).subscribe(
        data => {
          this.checkCommentFlag = data;
        }
      );
    }
  
    checkGrade(id) {
      this.gradeService.provjeriOcjenu(id, localStorage.getItem('username')).subscribe(
        data => {
          this.checkGradeFlag = data;
        }
      );
    }

    open(content, req) {
        this.myModal = this.modalService.open(content);
        this.request = req;
        this.checkComment(this.request.oglas.id);
        this.checkGrade(this.request.oglas.id);
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

    cancelRequest(adId: any) {
        this.requestService.cancelRequest(adId).subscribe( 
            data=>{
                alert('Request is canceled!');
                this.getRequests();
               } ,
                error=> console.error('Error!',error)
        );
    }

    send() {
        if (this.tekst == '' && this.ocjena == 0) {
          this._toastr.info("Please, fill the information")
          return;
        }
    
        if (this.tekst != "") {
         let comment = {
            id: null,
            tekst: this.tekst,
            odobren: false,
            oglasId: this.request.oglas.id,
            //voziloId: this.request.oglas.vozilo.id,
            userId: localStorage.getItem('id'),
            role: localStorage.getItem('user-role'),
            userUsername: localStorage.getItem('username')
          };
          this.commentService.dodajKomentar(comment).subscribe(
            data => {
              this._toastr.success("Comment succesfully created", "Comment");
            },
            error => {
              if (error.status == 400)
                this._toastr.info("Comment already sent", "Comment");
              else
                this._toastr.error("Error creating comment", "Comment");
            }
          );
        }
    
        if (this.ocjena != 0) {
          let grade = {
            id: null,
            ocjena: this.ocjena,
            oglasId: this.request.oglas.id,
            //voziloId: this.request.oglas.vozilo.id,
            userId: localStorage.getItem('id'),
            userUsername: localStorage.getItem('username')
          };
          this.gradeService.kreirajOcjenu(grade).subscribe(
            data => {
              this._toastr.success("Grade succesfully created", "Grade");
            },
            error => {
              if (error.status == 400)
                this._toastr.info("Grade already sent", "Grade");
              else
                this._toastr.error("Error creating grade", "Grade");
            }
          );
        }
        this.tekst = '';
        this.ocjena = 0;
        this.myModal.close();
      }
  

  }