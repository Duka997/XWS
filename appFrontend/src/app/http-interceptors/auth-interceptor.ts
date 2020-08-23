import { Injectable } from "@angular/core";
import { HttpInterceptor, HttpRequest, HttpHandler, HttpEvent } from '@angular/common/http';
import { Observable } from 'rxjs';
import {AuthService} from '../services/auth.service'


@Injectable()
export class AuthInterceptor implements HttpInterceptor {

    constructor(private auth: AuthService) {

    }

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        const authToken = this.auth.getToken();
        if (authToken != '') {
            const authReq = req.clone({
                headers: req.headers.set('AuthToken', authToken)
            });

            return next.handle(authReq);
        } else {
            const request = req.clone({
                headers: req.headers.set('Content-Type', 'application/json')
            });
            return next.handle(request);
        }
    }

}