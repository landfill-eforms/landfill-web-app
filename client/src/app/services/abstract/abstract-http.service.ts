import { Response } from '@angular/http';
import { AuthHttp } from 'angular2-jwt';
import { environment } from '../../../environments/environment';

export class AbstractHttpService<T> {

	protected readonly authHttp:AuthHttp;

	readonly baseUrl:string;

	constructor(resourcePath:string, authHttp:AuthHttp) {
		this.baseUrl = environment.resourceUrl + resourcePath;
		this.authHttp = authHttp;
	}

	getAll(success:(data) => void, error?:(err) => void) {
		this.authHttp.get(this.baseUrl + "/list/all").map((res:Response) => res.json()).subscribe(
				data => success(data),
				err => error ? error(err) : console.log(err)
			);
	}

	getById(id, success:(data) => void, error?:(err) => void) {
		this.authHttp.get(this.baseUrl + "/unique/id/" + id).map((res:Response) => res.json()).subscribe(
				data => success(data),
				err => error ? error(err) : console.log(err)
			);
	}

	create(entity:T, success:(data) => void, error?:(err) => void) {
		this.authHttp.post(this.baseUrl + '/create', entity).map((res:Response) => res.json()).subscribe(
				data => success(data),
				err => error ? error(err) : console.log(err)
			);
	}

	update(entity:T, success:(data) => void, error?:(err) => void) {
		this.authHttp.post(this.baseUrl + '/update', entity).map((res:Response) => res.json()).subscribe(
				data => success(data),
				err => error ? error(err) : console.log(err)
			);
	}

	// TODO Find out why delete request method is giving cors error.
	delete(entity:T, success:(data) => void, error?:(err) => void) {
		this.authHttp.post(this.baseUrl + '/delete', entity).map((res:Response) => res.json()).subscribe(
				data => success(data),
				err => error ? error(err) : console.log(err)
			);
	}

}