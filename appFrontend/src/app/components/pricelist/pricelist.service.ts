import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Cjenovnik } from 'src/app/model/priceList';

@Injectable({
    providedIn: 'root'
})

export class CjenovnikService {


    private urlDodajCjenovnik : string = `http://localhost:8086/vozilo/api/cjenovnik/dodaj`;
    private urlGetCjenovnik : string = `http://localhost:8086/vozilo/api/cjenovnik/getCjenovnik`;
    private urlIzmjeniCjenovnik : string = `http://localhost:8086/vozilo/api/cjenovnik/izmjeni`;

    constructor(private http: HttpClient) { 
    }
    

   getCjenovnik(username: string): Observable<any> {
        return this.http.get<any>(`${this.urlGetCjenovnik}/${username}`);
    }

    dodajCjenovnik(cjenovnik: any): Observable<any> {
        return this.http.post(`${this.urlDodajCjenovnik}`, cjenovnik);
    }

    izmjeniCjenovnik(cjenovnik : Cjenovnik) : Observable<any> {
        return this.http.put(`${this.urlIzmjeniCjenovnik}`, cjenovnik);
      }

    
}