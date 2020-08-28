import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
    providedIn: 'root'
  })
  export class CarService {
  
    constructor(private http: HttpClient) { }

    getCars() {
        return this.http.get<any>('http://localhost:8099/api/vozilo/get');
    }

    getCarStatistics(ownersID: any) {
        return this.http.get<any>('http://localhost:8099/api/vozilo/statistics/'+ownersID);
    }

  }