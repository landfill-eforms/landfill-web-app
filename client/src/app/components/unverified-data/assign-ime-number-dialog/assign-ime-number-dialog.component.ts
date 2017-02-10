import { IMENumber } from './../../../model/server/persistence/entity/instantaneous/ime-number.class';
import { IMENumberService } from './../../../services/ime-number.service';
import { UnverifiedInstantaneousData } from './../../../model/server/persistence/entity/unverified/unverified-instantaneous-data.class';
import { MdDialogRef } from '@angular/material';
import { Component, OnInit } from '@angular/core';


@Component({
	selector: 'app-assign-ime-number-dialog',
	templateUrl: './assign-ime-number-dialog.component.html',
	styleUrls: ['./assign-ime-number-dialog.component.scss']
})
export class AssignIMENumberDialogComponent {

	data:UnverifiedInstantaneousData;
	existingIMENumbers:IMENumber[];
	createdIMENumbers:IMENumber[]; // IME numbers created during this session.

	constructor(
		public dialogRef:MdDialogRef<AssignIMENumberDialogComponent>,
		private imeNumberService:IMENumberService
	) {}

}