import { Site } from './../../../../model/server/persistence/enums/location/site.enum';
import { MonitoringPointType } from './../../../../model/server/persistence/enums/location/monitoring-point-type.enum';
import { MonitoringPoint } from './../../../../model/server/persistence/enums/location/monitoring-point.enum';
import { UnverifiedWarmspotData } from './../../../../model/server/persistence/entity/unverified/unverified-warmspot-data.class';
import { MonitoringPointType } from './../../../../model/server/persistence/enums/location/monitoring-point-type.enum';
import { MonitoringPoint } from './../../../../model/server/persistence/enums/location/monitoring-point.enum';
import { MdDialogRef, MdSnackBar } from '@angular/material';
import { Instrument } from './../../../../model/server/persistence/entity/instrument/instrument.class';
import { Component, OnInit } from '@angular/core';


@Component({
	selector: 'app-edit-unverified-warmspot-dialog',
	templateUrl: './edit-unverified-warmspot-dialog.component.html'
})
export class EditUnverifiedWarmspotDialogComponent implements OnInit {

	site:Site;
	data:UnverifiedWarmspotData;
	availableInstruments:Instrument[] = [];
	monitoringPointsWrapped:{monitoringPoint:MonitoringPoint, selected?:boolean} [] = [];

	fields:{
		instrumentId?:number, 
		methaneLevel?:number, 
		size?:string, 
		description?:string,
		monitoringPoints?:MonitoringPoint[]
	} = {}

	constructor(
		public dialogRef:MdDialogRef<EditUnverifiedWarmspotDialogComponent>,
		private snackBar:MdSnackBar
	) {}

	ngOnInit() {
		this.fields.instrumentId = this.data.instrument ? this.data.instrument.id : null;
		this.fields.methaneLevel = this.data.methaneLevel / 100;
		this.fields.size = this.data.size;
		this.fields.description = this.data.description;

		this.availableInstruments = this.availableInstruments.filter(i => i.instrumentType.instantaneous);
		if (this.data) {
			let selectedGrids: number [] = this.data.monitoringPoints.map(g => g.ordinal);
            let monitoringPoints = MonitoringPoint.values().filter(g => {
                return g.site.ordinal == this.site.ordinal &&
                    g.type.ordinal == MonitoringPointType.GRID.ordinal;
			});
			for (let monitoringPoint of monitoringPoints) {
                this.monitoringPointsWrapped.push({monitoringPoint: monitoringPoint, selected: selectedGrids.indexOf(monitoringPoint.ordinal) > -1});
            }
		}
	}

	confirm() {
		this.fields.monitoringPoints = this.monitoringPointsWrapped.filter(g => g.selected).map(g => g.monitoringPoint);
		this.dialogRef.close(this.fields);
	}

	cancel() {
		this.dialogRef.close();
	}

	canSubmit(): boolean{
		return !!this.monitoringPointsWrapped.filter(g => g.selected).length && this.fields.instrumentId != null && this.fields.instrumentId != null && this.fields.methaneLevel != null && !!this.fields.size;
	}
}