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
    return this.http.post<any>('http://localhost:8083/login', login);
  }

  register(user: any) {
    return this.http.post<any>('http://localhost:8083/register', user);
  }

  getUsers() {
    return this.http.get<any>('http://localhost:8086/user/users');
  }

  disableUser(id: any) {
    return this.http.get<any>('http://localhost:8086/user/disable/' +id);
  }

  enableUser(id: any) {
    return this.http.get<any>('http://localhost:8086/user/enable/'+ id);
  }

  deleteUser(id: number) {
    return this.http.delete('http://localhost:8086/user/'+id);
  }

  getRegistrationRequests() {
    return this.http.get<any>('http://localhost:8086/user/requests');
  }

  accept(id: any) {
    return this.http.post<any>('http://localhost:8086/user/accept/'+ id, {});
  }


  reject(id: any) {
    return this.http.post<any>('http://localhost:8086/user/reject/' + id, {});
  }

  disableEnableRent(id: number, privilege: boolean) {
    return this.http.get<any>('http://localhost:8086/user/rentPrivilege/' + privilege + "/" + id);
  }

  getUser(username: string) {
    return this.http.get<any>('http://localhost:8086/user/' + username);
  }

  editUser(user: User) {
  return this.http.patch('http://localhost:8086/user', {
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
