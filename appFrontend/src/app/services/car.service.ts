import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CarService {
  
    constructor(private http: HttpClient) { }

    getVozila(username: string){
      return this.http.get<any>('http://localhost:8099/api/vozilo/getVozila/' + username);
    }

    getCarsByUser(userId: any) {
        return this.http.get<any>('http://localhost:8099/api/vozilo/get/' + userId);
    }

    getCarStatistics(ownersID: any) {
        return this.http.get<any>('http://localhost:8099/api/vozilo/statistics/' + ownersID);
    }

  }