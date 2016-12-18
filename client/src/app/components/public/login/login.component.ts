import {Component, OnInit} from '@angular/core';
import {Http, Response, Headers} from '@angular/http';
import {Router} from '@angular/router';
import 'rxjs/Rx';

@Component({
    selector: 'login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

    constructor (
        private router:Router,
        private http:Http
    ) {}

    ngOnInit() {

    }

    login(event, username, password) {
    event.preventDefault();
        let body = JSON.stringify({username, password});
        let contentHeaders = new Headers();
        contentHeaders.append('Accept', 'application/json');
        contentHeaders.append('Content-Type', 'multipart/form-data');
        // this.http.post('http://localhost:8080/login/test', body, {headers: contentHeaders})
        // .subscribe(
        //     response => {
        //     localStorage.setItem('id_token', response.json().id_token);
        //     this.router.navigate(['home']);
        //     },
        //     error => {
        //     alert(error.text());
        //     console.log(error.text());
        // });
        this.http.post('http://localhost:8080/login/test', body, {headers: contentHeaders}).map((res:Response) => res.json()).subscribe(
                data => console.log("data", data),
                err => console.log("error", err)
            );
    }

}