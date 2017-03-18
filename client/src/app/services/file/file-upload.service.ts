import { Injectable } from '@angular/core';
import { Response } from '@angular/http';
import { AuthHttp } from 'angular2-jwt';
import { environment } from '../../../environments/environment';

@Injectable()
export class FileUploadService {

	readonly baseUrl:string = environment.resourceUrl + '/upload';

	constructor(private authHttp: AuthHttp) {}

	testUpload(formData:FormData, success:(data) => void, error?:(err) => void) {
		this.authHttp.post(this.baseUrl + "/mobile", formData).map((res:Response) => res.json()).subscribe(
				data => success(data),
				err => error ? error(err) : console.log(err)
			);
	}

}