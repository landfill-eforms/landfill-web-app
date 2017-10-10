import { MonitoringPointType } from './../../../../model/server/persistence/enums/location/monitoring-point-type.enum';
import { MonitoringPoint } from './../../../../model/server/persistence/enums/location/monitoring-point.enum';
import { ImeNumber } from './../../../../model/server/persistence/entity/surfaceemission/instantaneous/ime-number.class';
import { MdSnackBar } from '@angular/material';
import { MdDialogRef } from '@angular/material';
import { OnInit, Component } from '@angular/core';

@Component({
	selector: 'app-ime-grids-dialog',
	templateUrl: './ime-grids-dialog.component.html'
})
export class ImeGridsDialogComponent implements OnInit {

    monitoringPoints: MonitoringPoint[]= [];

    data: ImeNumber;

	constructor(
		private snackBar: MdSnackBar,
		public dialogRef: MdDialogRef<ImeGridsDialogComponent>) {

	}

	ngOnInit() {
        if (this.data) {
            this.monitoringPoints = MonitoringPoint.values().filter(g => {
                return g.site.ordinal == this.data.site.ordinal &&
                    g.type.ordinal == MonitoringPointType.GRID.ordinal;
            });
        }
	}

	submit() {
		// TODO Implement this.
	}

	cancel() {
		this.dialogRef.close();
	}

}