import { Component, OnInit } from '@angular/core';
import { AdsService } from 'src/app/services/ads.service';
import { ToastrService } from 'ngx-toastr';
import { Router } from '@angular/router';
import { Oglas } from 'src/app/model/Ad';
import { Vozilo } from 'src/app/model/car';
import { ICart } from './ICart';
import { IRentRequest } from './IRequestRent';
import { IBundle } from './IBundle';

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

    max3: any = 0;

    constructor(private adsService: AdsService,
        private toastr: ToastrService,
        private router: Router) { }

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
        this.adsService.getAllAdsForCart().subscribe(data => {
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
        oglasId: oglas.id,
        od: oglas.od,
        doo: oglas.doo,
        mjestoPreuzimanja: oglas.mjestoPreuzimanja,
      };
      this.requests.push(rentRequest);
      console.log("Svi pojedinacni zahtevi: ", this.requests);
    }

    addToBundle(oglas: Oglas) {
      let rentRequest: IRentRequest = {
        oglasId: oglas.id,
        od: oglas.od,
        doo: oglas.doo,
        mjestoPreuzimanja: oglas.mjestoPreuzimanja,
      };
      this.bundles2.push(rentRequest);
      this.max3++;
      console.log("Svi bundle zahtevi: ", this.bundles2);
    }

    submitBunddle() {
      if(this.max3 > 3) {
        alert("3 vehicles is max");
        this.max3 = 0;
        this.bundles = [];
      } else {
        let bundle: IBundle = {
          requests: []
        };
        this.bundles2.forEach(element => {
          bundle.requests.push(this.createRentRequest(element));
        });
        console.log("asd", bundle);
        this.bundles.push(bundle);
        this.max3 = 0;
      }
      
    }

    createRentRequest(request: IRentRequest) {
      let rentRequest: IRentRequest = {
        oglasId: request.oglasId,
        od: request.od,
        doo: request.doo,
        mjestoPreuzimanja: request.mjestoPreuzimanja
      };
      return rentRequest;
    }

    sendRequest() {
      let cart = {
        requests: this.requests,
        bundles: this.bundles
      }
      console.log("gds:", cart);
      this.adsService.rentACarRequest(cart).subscribe(() => {
        alert("success");
        this.bundles = [];
        this.requests = [];
      })
    }

  }