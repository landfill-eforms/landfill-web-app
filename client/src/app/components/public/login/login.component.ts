import {Component, OnInit} from '@angular/core';
import {Http, Response, Headers} from '@angular/http';
import {Router} from '@angular/router';
import 'rxjs/Rx';

import {JwtHelper} from 'angular2-jwt';

@Component({
    selector: 'login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

    jwtHelper: JwtHelper = new JwtHelper();

    constructor (
        private router:Router,
        private http:Http,
    ) {}

    ngOnInit() {

    }

    login(event, username, password) {
    event.preventDefault();
        let body = JSON.stringify({username, password});
        let contentHeaders = new Headers({
            'Accept': 'application/json',
            'Content-Type': 'text/plain',
            // 'Access-Control-Expose-Headers': 'etag'
        });
        // contentHeaders.append('Accept', 'application/json');
        //contentHeaders.append('Content-Type', 'wtf');
        // contentHeaders.append('Access-Control-Expose-Headers', 'etag');

        this.http.post('http://localhost:8080/login', body, {headers: contentHeaders})
        .subscribe(
            (res) => {
                let jwtToken:string = res.headers.get("Authorization").replace("Bearer ", "");
                console.log("JWT Token:", jwtToken);
                console.log(
                    this.jwtHelper.decodeToken(jwtToken),
                    this.jwtHelper.getTokenExpirationDate(jwtToken),
                    this.jwtHelper.isTokenExpired(jwtToken)
                );
                localStorage.setItem('id_token', jwtToken);
            // localStorage.setItem('id_token', response.json().id_token);
            // this.router.navigate(['home']);
            },
            error => {
            //alert(error.text());
            console.log(error.text());
        });

        // this.http.post('http://localhost:8080/login', body, {headers: contentHeaders}).map((res:Response) => res.json()).subscribe(
        //         data => console.log("data", data),
        //         err => console.log("error", err)
        //     );
    }

}