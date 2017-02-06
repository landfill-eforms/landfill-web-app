import { Injectable } from '@angular/core';
import { Response } from '@angular/http';
import { AuthHttp } from 'angular2-jwt';
import { environment } from '../../environments/environment';

@Injectable()
export class FileUploadService {

	readonly baseUrl:string = environment.resourceUrl + '/file';

	constructor(private authHttp: AuthHttp) {}

	testUpload(callback:(data) => void, formData:FormData) {
	this.authHttp.post(this.baseUrl + "/upload", formData).map((res:Response) => res.json()).subscribe(
			data => callback(data),
			err => console.log(err),
		);
	}

}