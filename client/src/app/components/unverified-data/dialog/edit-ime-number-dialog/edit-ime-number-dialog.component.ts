import { MonitoringPointType } from './../../../../model/server/persistence/enums/location/monitoring-point-type.enum';
import { MonitoringPoint } from './../../../../model/server/persistence/enums/location/monitoring-point.enum';
import { ImeNumber } from './../../../../model/server/persistence/entity/surfaceemission/instantaneous/ime-number.class';
import { Instrument } from './../../../../model/server/persistence/entity/instrument/instrument.class';
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
		monitoringPoints?:MonitoringPoint[],
		imeNumber?:string,
		methaneLevel?:number
	} = {};
	
	constructor(
		public dialogRef:MdDialogRef<EditImeNumberDialogComponent>) {
	}

	ngOnInit() {
		this.fields.instrumentId = this.data.imeData[0].instrument ? this.data.imeData[0].instrument.id : null;
		this.fields.imeNumber = this.data.imeNumber;
		this.fields.methaneLevel = this.data.imeData[0].methaneLevel / 100;
		if (this.data) {
            this.fields.monitoringPoints = MonitoringPoint.values().filter(g => {
                return g.site.ordinal == this.data.site.ordinal &&
                    g.type.ordinal == MonitoringPointType.GRID.ordinal;
            });
		}
		
		for (let monitoringPoint of this.monitoringPointsWrapped) {
			for (let monitoringPointsWrapped of this.monitoringPointsWrapped) {
				if (monitoringPointsWrapped.monitoringPoint.ordinal == monitoringPoint.monitoringPoint.ordinal) {
					monitoringPointsWrapped.selected = true;
				}
			}
		}
	}

	confirm() {
		// TODO Implement this.
	}

	/** Closes the dialog without passing any data back. */
	cancel() {
		this.dialogRef.close();
	}

}