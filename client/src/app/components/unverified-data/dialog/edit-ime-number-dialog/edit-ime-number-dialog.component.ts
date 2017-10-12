import { EnumUtils } from './../../../../utils/enum.utils';
import { MonitoringPointType } from './../../../../model/server/persistence/enums/location/monitoring-point-type.enum';
import { MonitoringPoint } from './../../../../model/server/persistence/enums/location/monitoring-point.enum';
import { ImeNumber } from './../../../../model/server/persistence/entity/surfaceemission/instantaneous/ime-number.class';
import { Instrument } from './../../../../model/server/persistence/entity/instrument/instrument.class';
import { MdSnackBar } from '@angular/material';
import { MdDialogRef } from '@angular/material';
import { OnInit, Component } from '@angular/core';

@Component({
	selector: 'app-edit-ime-number-dialog',
	templateUrl: './edit-ime-number-dialog.component.html'
})
export class EditImeNumberDialogComponent implements OnInit {

	data:ImeNumber;
	availableInstruments:Instrument[] = [];
	availableImeNumbers:ImeNumber[];
	imeNumbersLoaded:boolean;
	monitoringPointsWrapped:{monitoringPoint:MonitoringPoint, selected?:boolean}[] = [];

	fields: {
		instrumentId?:number,
		methaneLevel?:number,
		description?:string,
		monitoringPoints?:MonitoringPoint[] // This is only for sending the grids back to the unverified data set component.
	} = {};
	
	constructor(
		public dialogRef:MdDialogRef<EditImeNumberDialogComponent>) {
	}

	ngOnInit() {
		this.fields.instrumentId = this.data.imeData[0].instrument ? this.data.imeData[0].instrument.id : null;
		this.fields.methaneLevel = this.data.imeData[0].methaneLevel / 100;
		this.fields.description = this.data.imeData[0].description;
		if (this.data) {
			let selectedGrids: number [] = this.data.monitoringPoints.map(g => g.ordinal);
            let monitoringPoints = MonitoringPoint.values().filter(g => {
                return g.site.ordinal == this.data.site.ordinal &&
                    g.type.ordinal == MonitoringPointType.GRID.ordinal;
			});
			for (let monitoringPoint of monitoringPoints) {
                this.monitoringPointsWrapped.push({monitoringPoint: monitoringPoint, selected: selectedGrids.indexOf(monitoringPoint.ordinal) > -1});
            }
		}
	}

	confirm() {
<<<<<<< HEAD
		// TODO Implement this.
		// let result: MonitoringPoint[] = this.monitoringPointsWrapped.filter(g => g.selected).map(g => g.monitoringPoint);
		// this.dialogRef.close(result);
		// this.dialogRef.close(this.fields);

		// let result: MonitoringPoint[] = this.monitoringPointsWrapped.filter(g => g.selected).map(g => g.monitoringPoint);
=======
>>>>>>> 686a6fc67f981b6ac82f001b91df4be536b8c499
		this.fields.monitoringPoints = this.monitoringPointsWrapped.filter(g => g.selected).map(g => g.monitoringPoint);
		this.dialogRef.close(this.fields);
	}

	/** Closes the dialog without passing any data back. */
	cancel() {
		this.dialogRef.close();
	}

}