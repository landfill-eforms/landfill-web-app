import {Component, OnInit, Injectable} from '@angular/core';
import {Http, Response, Headers, RequestOptions} from '@angular/http';
import {environment} from '../../environments/environment';

@Injectable()
export class SitesService {

    readonly baseUrl:string = environment.resourceUrl + 'resource/sites';

    constructor (private http:Http) {}

    ngOnInit() {}

    getVisible(callback:(data) => void) {
        this.http.get(this.baseUrl).map((res:Response) => res.json()).subscribe(
                data => callback(data),
                err => console.log(err)
            );
    }

}