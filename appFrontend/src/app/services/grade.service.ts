import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class GradeService {

  constructor(private http: HttpClient) { }

  getOcjene(id: number){
    return this.http.get<any>('http://localhost:8086/vozilo/api/ocjena/vozilo/'+id);
  }

  kreirajOcjenu(grade: any){
    return this.http.post('http://localhost:8086/vozilo/api/ocjena', grade);
  }

  provjeriOcjenu(id: number, username: string) {
    return this.http.get<any>('http://localhost:8086/vozilo/api/ocjena/provjeri/' + username + "/" + id);
  }
  
}
