import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../model/user';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  getToken() {
    let token = localStorage.getItem("User-token");
    return token == null ? '' : 'Bearer ' + token;
  }

  login(login: any) {
    return this.http.post<any>('http://localhost:8099/login', login);
  }

  register(user: any) {
    return this.http.post<any>('http://localhost:8099/register', user);
  }

  getUsers() {
    return this.http.get<any>('http://localhost:8099/users');
  }

  disableUser(id: any) {
    return this.http.get<any>('http://localhost:8099/disable/' +id);
  }

  enableUser(id: any) {
    return this.http.get<any>('http://localhost:8099/enable/'+ id);
  }

  deleteUser(id: number) {
    return this.http.delete('http://localhost:8099/'+id);
  }

  getRegistrationRequests() {
    return this.http.get<any>('http://localhost:8099/requests');
  }

  accept(id: any) {
    return this.http.post<any>('http://localhost:8099/accept/'+ id, {});
  }


  reject(id: any) {
    return this.http.post<any>('http://localhost:8099/reject/' + id, {});
  }

  disableEnableRent(id: number, privilege: boolean) {
    return this.http.get<any>('http://localhost:8099/rentPrivilege/' + privilege + "/" + id);
  }

  getUser(username: string) {
    return this.http.get<any>('http://localhost:8099/' + username);
  }

  editUser(user: User) {
  return this.http.patch('http://localhost:8099', {
                                                      id: user.id,
                                                      imeKompanije: user.imeKompanije,
                                                      poslovniID: user.poslovniID,
                                                      email: user.email,
                                                      name: user.name,
                                                      surname: user.surname,
                                                      address: user.address
                                                    });
  }


}
