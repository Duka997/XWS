import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { IReport } from '../model/Report';

@Injectable({
    providedIn: 'root'
  })
  export class RequestService {
  
    constructor(private http: HttpClient) { }
  
    getRequests(userId: any) {
        return this.http.get<any>('http://localhost:8086/zahtjev/api/request/getRequests/'+userId);
    }

    getRequestsHistory(userId: any) {
        return this.http.get<any>('http://localhost:8086/zahtjev/api/request/getRequestsHistory/'+userId);
    }

    acceptRequest(reqId: any) {
        return this.http.post<any>('http://localhost:8086/zahtjev/api/request/accept/'+reqId, {});
    }

    cancelRequest(reqId: any) {
        return this.http.post<any>('http://localhost:8086/zahtjev/api/request/cancel/'+reqId, {});
    }

    acceptBundle(bundleId: any) {
        return this.http.post<any>('http://localhost:8086/zahtjev/api/bundle/accept/'+bundleId, {});
    }

    cancelBundle(bundleId: any) {
        return this.http.post<any>('http://localhost:8086/zahtjev/api/bundle/cancel/'+bundleId, {});
    }

    createReport(report: IReport) {
        return this.http.post<any>("http://localhost:8086/vozilo/api/report/add", report);
      }

}