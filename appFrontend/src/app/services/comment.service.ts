import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  constructor(private http: HttpClient) { }

  getKomentari(id: number){
    return this.http.get<any>('http://localhost:8099/api/komentar/getKomentareVozilo/'+id);
  }

  dodajKomentar(komentar: any){
    return this.http.post<any>('http://localhost:8099/api/komentar', komentar);
  }

  dodajKomentarOdgovor(komentar: any){
    return this.http.post<any>('http://localhost:8099/api/komentar/odgovor', komentar);
  }

  provjeriKomentar(id: number, username: string) {
    return this.http.get<any>('http://localhost:8099/api/komentar/provjeri/' + username + "/" + id);
  }

  getKomentare(){
    return this.http.get<any>('http://localhost:8099/api/komentar');
  }

  prihvatiIliOdbij(id: number,flag: any){
    return this.http.get<any>('http://localhost:8099/api/komentar/odobri/'+flag+'/'+id);
  }
}
