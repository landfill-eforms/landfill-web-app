import { Injectable } from '@angular/core';
import { Response } from '@angular/http';
import { AuthHttp } from 'angular2-jwt';
import { environment } from '../../../environments/environment';

@Injectable()
export class UserActivityService {
	
	readonly baseUrl:string = environment.resourceUrl + '/user-activity';

	constructor(private authHttp: AuthHttp) {

	}

	getByUserId(id:number, success:(data) => void, error?:(err) => void) {
		this.authHttp.get(this.baseUrl + "/list/user/id/" + id).map((res:Response) => res.json()).subscribe(
				data => success(data),
				err => error ? error(err) : console.log(err)
			);
	}

}
