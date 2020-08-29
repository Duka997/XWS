import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class GradeService {

  constructor(private http: HttpClient) { }

  getOcjene(id: number){
    return this.http.get<any>('http://localhost:8099/api/ocjena/vozilo/'+id);
  }
  
}
