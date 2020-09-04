import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Pretraga } from '../model/pretraga';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SearchService {

  constructor(private http: HttpClient) { }

  getMarke() {
    return this.http.get<any>('http://localhost:8099/api/marka/get');
  }

  getMjenjaci() {
    return this.http.get<any>('http://localhost:8099/api/tipmjenjaca/get');
  }

  getGoriva() {
    return this.http.get<any>('http://localhost:8099/api/tipgoriva/get');
  }

  getKlase() {
    return this.http.get<any>('http://localhost:8099/api/klasa/get');
  }

  getMjestaPruzimanja() {
    return this.http.get<any>('http://localhost:8099/api/oglas/mjesta');
  }

  getModeli(id: number) {
    return this.http.get<any>('http://localhost:8099/api/marka/models/'+ id);
  }

  pretraziOglase(pretraga: Pretraga, page: number, sort: string): Observable<any>  {
    return this.http.post(`http://localhost:8099/api/oglas/pretraga/`+page+'/'+sort, pretraga);
  }

  getOneOglas(id: number) {
    return this.http.get<any>('http://localhost:8099/api/oglas/'+ id);
  }
}
