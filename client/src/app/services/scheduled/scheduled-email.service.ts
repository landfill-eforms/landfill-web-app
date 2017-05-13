import { ScheduledNotification } from './../../model/server/persistence/entity/scheduled/scheduled-notification.class';
import { ScheduledEmail } from './../../model/server/persistence/entity/scheduled/scheduled-email.class';
import { ScheduledReport } from './../../model/server/persistence/entity/scheduled/scheduled-report.class';
import { AuthHttp } from 'angular2-jwt';
import { Response } from '@angular/http';
import { environment } from './../../../environments/environment';
import { Injectable } from '@angular/core';

@Injectable()
export class ScheduledEmailService {

	readonly baseUrl:string = environment.resourceUrl + '/schedule/email';

	constructor(private authHttp: AuthHttp) {}

	getAll(success:(data) => void, error?:(err) => void) {
		this.authHttp.get(this.baseUrl + '/list/all').map((res:Response) => res.json()).subscribe(
				data => success(data),
				err => error ? error(err) : console.log(err)
			);
	}

	getAllScheduledReports(success:(data) => void, error?:(err) => void) {
		this.authHttp.get(this.baseUrl + '/list/all/report').map((res:Response) => res.json()).subscribe(
				data => success(data),
				err => error ? error(err) : console.log(err)
			);
	}

	getAllScheduledNotifications(success:(data) => void, error?:(err) => void) {
		this.authHttp.get(this.baseUrl + '/list/all/notification').map((res:Response) => res.json()).subscribe(
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

	createScheduledReport(scheduledReport:ScheduledReport, success:(data) => void, error?:(err) => void) {
		this.authHttp.post(this.baseUrl + '/create/report', scheduledReport).map((res:Response) => res.json()).subscribe(
				data => success(data),
				err => error ? error(err) : console.log(err)
			);
	}

	createScheduledNotification(scheduledNotification:ScheduledNotification, success:(data) => void, error?:(err) => void) {
		this.authHttp.post(this.baseUrl + '/create/notification', scheduledNotification).map((res:Response) => res.json()).subscribe(
				data => success(data),
				err => error ? error(err) : console.log(err)
			);
	}

	updateScheduledReport(scheduledReport:ScheduledReport, success:(data) => void, error?:(err) => void) {
		this.authHttp.post(this.baseUrl + '/update/report', scheduledReport).map((res:Response) => res.json()).subscribe(
				data => success(data),
				err => error ? error(err) : console.log(err)
			);
	}

	updateScheduledNotification(scheduledNotification:ScheduledNotification, success:(data) => void, error?:(err) => void) {
		this.authHttp.post(this.baseUrl + '/update/notification', scheduledNotification).map((res:Response) => res.json()).subscribe(
				data => success(data),
				err => error ? error(err) : console.log(err)
			);
	}

	// TODO Find out why delete request method is giving cors error.
	deleteScheduledReport(scheduledReport:ScheduledReport, success:(data) => void, error?:(err) => void) {
		this.authHttp.post(this.baseUrl + '/delete/report', scheduledReport).map((res:Response) => res.json()).subscribe(
				data => success(data),
				err => error ? error(err) : console.log(err)
			);
	}

	// TODO Find out why delete request method is giving cors error.
	deleteScheduledNotification(scheduledNotification:ScheduledNotification, success:(data) => void, error?:(err) => void) {
		this.authHttp.post(this.baseUrl + '/delete/notification', scheduledNotification).map((res:Response) => res.json()).subscribe(
				data => success(data),
				err => error ? error(err) : console.log(err)
			);
	}

}