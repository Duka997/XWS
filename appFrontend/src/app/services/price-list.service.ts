import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Cjenovnik } from '../model/priceList';

@Injectable({
  providedIn: 'root'
})
export class PriceListService {

  constructor(private http: HttpClient) { }

  getCjenovnik(username: any) {
    return this.http.get<any>('http://localhost:8099/api/cjenovnik/getCjenovnikUsername/' + username);
  }

  dodajCjenovnik(cjenovnik: any){
    return this.http.post<any>('http://localhost:8099/api/cjenovnik/dodaj', cjenovnik);
  }

  izmjeniCjenovnik(cjenovnik: Cjenovnik){
    return this.http.post('http://localhost:8099/api/cjenovnik/izmjeni', cjenovnik);
  }
}
