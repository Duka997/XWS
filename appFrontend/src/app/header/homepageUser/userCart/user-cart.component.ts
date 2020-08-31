import { Component, OnInit } from '@angular/core';
import { AdsService } from 'src/app/services/ads.service';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import { Oglas } from 'src/app/model/Ad';
import { Vozilo } from 'src/app/model/car';
import { ICart } from './ICart';
import { IRentRequest } from './IRequestRent';
import { IBundle } from './IBundle';
import { NgbModalRef, NgbModal } from '@ng-bootstrap/ng-bootstrap';

@Component({
    selector: 'app-user-cart',
    templateUrl: './user-cart.component.html',
  })
  export class UserCartComponent implements OnInit {

    ads: Oglas[] = [];
    bundleCheckBox: boolean = false;

    requests: IRentRequest[] = [];
    bundles: IBundle[] = [];
    bundles2: IRentRequest[] = [];

    modal: NgbModalRef;
    mode: string = 'ADD';
  
    vehicle: Vozilo;

    constructor(private adsService: AdsService,
        private toastr: ToastrService,
        private router: Router,
        private ngbModal: NgbModal) { }

    ngOnInit(): void {
        this.getAll();
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

    private getAll(): void {
        var userId = parseInt(localStorage.getItem('id'));
        this.adsService.getAllAdsForCart(userId).subscribe(data => {
          this.ads = data;
          console.log("Svi oglasi u korpi: ", this.ads);
        }, error => {
          this.toastr.error('There was an error while getting all vehicles');
        });
    }

    removeFromCart(oglasId: number) {
        this.adsService.removeAdFromCart(oglasId).subscribe(data => {
            alert('Removed from cart!');
        })
    }

    addToRequest(oglas: Oglas) {
      let rentRequest: IRentRequest = {
        id: oglas.id,
        oglasId: oglas.adId,
        od: oglas.od,
        doo: oglas.doo,
        mjestoPreuzimanja: oglas.mjestoPreuzimanja,
        bundleId: oglas.bundleId
      };
      this.requests.push(rentRequest);
      console.log("Svi pojedinacni zahtevi: ", this.requests);
    }

    addToBundle(oglas: Oglas) {
      let rentRequest: IRentRequest = {
        id: oglas.id,
        oglasId: oglas.adId,
        od: oglas.od,
        doo: oglas.doo,
        mjestoPreuzimanja: oglas.mjestoPreuzimanja,
        bundleId: oglas.bundleId
      };
      this.bundles2.push(rentRequest);
      console.log("Svi bundle zahtevi: ", this.bundles2);
    }

    submitBunddle() {
      console.log("bundles2 lenght: ",this.bundles2.length);
      if(this.bundles2.length > 3) {
        alert("3 vehicles is max");
        this.bundles2 = [];
      } else {
        let bundle: IBundle = {
          requests: []
        };
        this.bundles2.forEach(element => {
          bundle.requests.push(this.createRentRequest(element));
        });
        console.log("bundle", bundle);
        this.bundles.push(bundle);
        this.bundles2 = [];
      }
      
    }

    createRentRequest(request: IRentRequest) {
      let rentRequest: IRentRequest = {
        id: request.id,
        oglasId: request.oglasId,
        od: request.od,
        doo: request.doo,
        mjestoPreuzimanja: request.mjestoPreuzimanja,
        bundleId: request.bundleId
      };
      return rentRequest;
    }

    sendRequest() {
      let cart = {
        requests: this.requests,
        bundles: this.bundles
      }
      console.log("cart: ", cart);
      var userId = parseInt(localStorage.getItem('id'));
      this.adsService.rentACarRequest(cart, userId).subscribe(() => {
        console.log("user id:", userId);
        alert("success");
        this.bundles = [];
        this.requests = [];
        cart.bundles = [];
        cart.requests = [];
      })
    }

    seeVehicle(sadrzaj: any, selectedVehicle: Vozilo) {
      //this.clear();
      //this.refresh();
  
      this.ads.forEach(element => {
          if(element.vozilo.id == selectedVehicle.id) {
              this.vehicle = element.vozilo;
              console.log("Vehicle: ", this.vehicle);
          }
      });
  
      this.mode = 'ADD';
      this.modal = this.ngbModal.open(sadrzaj,{size: 'lg'});
    }

  }