import { AbstractHttpService } from './../abstract/abstract-http.service';
import { UserGroup } from './../../model/server/persistence/entity/user/user-group.class';
import { Injectable } from '@angular/core';
import { Response } from '@angular/http';
import { AuthHttp } from 'angular2-jwt';
import { environment } from '../../../environments/environment';

@Injectable()
export class UserGroupService extends AbstractHttpService<UserGroup> {

	constructor(authHttp:AuthHttp) {
		super('/user-group', authHttp);
	}

}