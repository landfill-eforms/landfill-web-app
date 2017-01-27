import { Component, OnInit, Injectable } from '@angular/core';
import { Response, Headers, RequestOptions } from '@angular/http';
import { AuthHttp } from 'angular2-jwt';
import { environment } from '../../environments/environment';

@Injectable()
export class FileUploadService {

	readonly baseUrl:string = environment.resourceUrl + '/file';

	constructor(private authHttp: AuthHttp) {}

	testUpload(success:(data) => void, error:(err) => void, formData:FormData) {
	this.authHttp.post(this.baseUrl + "/upload", formData).map((res:Response) => res.json()).subscribe(
			data => success(data),
			err => error(err),
		);
	}

}