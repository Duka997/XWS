import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class OccupationService {

  constructor(private http: HttpClient) { }

  setOccupied(occupied: any) {
    return this.http.post<any>("http://localhost:8099/occupied", occupied);
  }

  getOccupations(userId: any) {
    return this.http.get<any>("http://localhost:8099/occupied/user/" + userId);
  }
}