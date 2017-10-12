import { MonitoringPointType } from './../../../../model/server/persistence/enums/location/monitoring-point-type.enum';
import { MonitoringPoint } from './../../../../model/server/persistence/enums/location/monitoring-point.enum';
import { IseNumber } from './../../../../model/server/persistence/entity/surfaceemission/integrated/ise-number.class';
import { MdSnackBar } from '@angular/material';
import { MdDialogRef } from '@angular/material';
import { OnInit, Component } from '@angular/core';

@Component({
	selector: 'app-edit-ise-number-dialog',
	templateUrl: './edit-ise-number-dialog.component.html'
})
export class EditIseNumberDialogComponent implements OnInit {

	data:IseNumber;
	availableIseNumbers:IseNumber[];
	iseNumbersLoaded:boolean;
	monitoringPointsWrapped:{monitoringPoint:MonitoringPoint, selected?:boolean}[] = [];

	fields: {
		monitoringPoint?:MonitoringPoint,
		methaneLevel?:number,
		description?:string
	} = {};

	constructor(
		public dialogRef:MdDialogRef<EditIseNumberDialogComponent>) {
	}

	ngOnInit() {
		this.fields.methaneLevel = this.data.iseData[0].methaneLevel / 100;
		this.fields.monitoringPoint = this.data.monitoringPoint;
		this.fields.description = this.data.iseData[0].description;
	}
	
	confirm() {
		this.dialogRef.close(this.fields);
	}

	/** Closes the dialog without passing any data back. */
	cancel() {
		this.dialogRef.close();
	}

}