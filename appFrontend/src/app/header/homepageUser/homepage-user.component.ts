import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { AdsService } from 'src/app/services/ads.service';
import { Oglas } from 'src/app/model/Ad';

@Component({
  selector: 'app-homepage-user',
  templateUrl: './homepage-user.component.html'
})
export class HomepageUserComponent implements OnInit {

  oglasi = [];

  constructor(  private router: Router,
                private toastr: ToastrService,
                private adsService: AdsService ) { }

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
    this.adsService.addAdToCart(oglas).subscribe(data => {
        this.toastr.success('Ad has been added to cart');
      }, error => {
        this.toastr.error(error.error.message);
      });
  }
}