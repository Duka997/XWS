import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Oglas } from '../model/Ad';
import { Vozilo } from '../model/car';
import { ICart } from '../header/homepageUser/userCart/ICart';

@Injectable({
    providedIn: 'root'
  })
  export class AdsService {
  
    constructor(private http: HttpClient) { }

    getAds() {
        return this.http.get<any>('http://localhost:8099/api/oglas/get');
    }

    addAdToCart(oglas: Oglas): Observable<any> {
        return this.http.post('http://localhost:8099/api/oglas/dodajUKorpu', oglas);
    }

    getAllAdsForCart(): Observable<any> {
        return this.http.get('http://localhost:8099/api/oglas/getCartAds');
    }

    removeAdFromCart(id: number) {
        return this.http.post('http://localhost:8099/api/oglas/remove/'+id, {});
    }

    rentACarRequest(cart: ICart, userId: any): Observable<any> {
        return this.http.post('http://localhost:8099/api/oglas/request/'+userId, cart);
    }

  }