  
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Oglas } from 'src/app/model/add';

@Injectable({
    providedIn: 'root'
})
export class DodajOglasService{
    _url = 'http://localhost:8099/api/oglasi/dodaj';
    _url2 = 'http://localhost:8099/api/vozilo/get';
   

    constructor(private _http: HttpClient) { }

   dodajOglas(oglas: Oglas) {
       return  this._http.post<any>(this._url, oglas);
    }

    
    getVozila():Observable<any>{
        return this._http.get<Oglas[]>(this._url2);
    }
}