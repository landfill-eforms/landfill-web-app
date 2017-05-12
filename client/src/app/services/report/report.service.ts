import { IndividualReportQuery } from './../../model/server/persistence/entity/report/individual-report-query.class';
import { AuthHttp } from 'angular2-jwt';
import { Response } from '@angular/http';
import { environment } from './../../../environments/environment';
import { Injectable } from '@angular/core';

@Injectable()
export class ReportService {

	readonly baseUrl:string = environment.resourceUrl + '/report';

	constructor(private authHttp: AuthHttp) {}

	previewReport(reportQuery:IndividualReportQuery, success:(data) => void, error?:(err) => void) {
		this.authHttp.post(this.baseUrl + '/preview', reportQuery).map((res:Response) => res.json()).subscribe(
				data => success(data),
				err => error ? error(err) : console.log(err)
			);
	}

}