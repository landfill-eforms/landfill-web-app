import {Component, OnInit, Injectable} from '@angular/core';
import {Http, Response, Headers, RequestOptions} from '@angular/http';
import {environment} from '../../environments/environment';

@Injectable()
export class InstantaneousDataService {

    readonly baseUrl:string = environment.resourceUrl + 'resource/instantaneous-data';

    constructor (private http:Http) {}

    ngOnInit() {}

    getBySiteName(callback:(data) => void, siteName:string) {
        this.http.get(this.baseUrl + "/" + siteName).map((res:Response) => res.json()).subscribe(
                data => callback(data),
                err => console.log(err)
            );
    }

    getAll(callback:(data) => void) {
        this.http.get(this.baseUrl).map((res:Response) => res.json()).subscribe(
                data => callback(data),
                err => console.log(err)
            );
    }

    save(callback:(data) => void, input:any[]) {
        this.http.post(this.baseUrl, input).map((res:Response) => res.json()).subscribe(
            data => callback(data),
            err => console.log(err),
        );
    }

    upload(callback:(data) => void, file:any) {
        this.http.post(this.baseUrl + "/upload", file).map((res:Response) => res.json()).subscribe(
            data => callback(data),
            err => console.log(err),
        );
    }

}