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
  selector: 'app-ads-list',
  templateUrl: './adsList.component.html'
})
export class AdsListComponent implements OnInit {

  oglasi = [];
  po =  new Poruka(null,null,null,null);

  modal: NgbModalRef;
  mode: string = 'ADD';

  vehicle: Vozilo;

  constructor(  private router: Router,
                private toastr: ToastrService,
                private adsService: AdsService,
                private _porukaServis: PorukaService,
                private ngbModal: NgbModal ) { }

  ngOnInit(): void {
    this.adsService.getAds().subscribe(data => {
        this.oglasi = data;
        console.log("Svi oglasi: ", this.oglasi);
    }, error => {
        this.toastr.error('There was an error while getting all vehicles');
    })
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

  addToCart(oglas: Oglas) {
    console.log("Primalac : ", oglas.userId);
    var userId = parseInt(localStorage.getItem('id'));
    this.adsService.addAdToCart(oglas,userId).subscribe(data => {
        this.toastr.success('Ad has been added to cart');
      }, error => {
        this.toastr.error(error.error.message);
      });
  }
  inbox(){
    var userId = parseInt(localStorage.getItem('id'));
    console.log("Primalac id je ", userId )
    this.router.navigate(['inbox']);
  }

  posaljiPor(userId:any){
    localStorage.setItem("primalac", userId);
    this.router.navigate(['homepageUser/reply']);
  }

  seeVehicle(sadrzaj: any, selectedVehicle: Vozilo) {
    //this.clear();
    //this.refresh();

    this.oglasi.forEach(element => {
        if(element.vozilo.id == selectedVehicle.id) {
            this.vehicle = element.vozilo;
            console.log("Vehicle: ", this.vehicle);
        }
    });

    this.mode = 'ADD';
    this.modal = this.ngbModal.open(sadrzaj,{size: 'lg'});
  }
  
}