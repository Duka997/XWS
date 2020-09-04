import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CarService {
  
    constructor(private http: HttpClient) { }

    getVozila(username: string){
      return this.http.get<any>('http://localhost:8086/vozilo/api/vozilo/getVozila/' + username);
    }

    getCarsByUser(username: any) {
        return this.http.get<any>('http://localhost:8086/vozilo/api/vozilo/getVozila/' + username);
    }

    getCarStatistics(ownersID: any) {
        return this.http.get<any>('http://localhost:8086/vozilo/api/vozilo/statistics/' + ownersID);
    }

  }
