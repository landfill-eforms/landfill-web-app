import { ImeNumberService } from './../../../../services/instantaneous/ime-number.service';
import { EnumUtils } from './../../../../utils/enum.utils';
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

    data: ImeNumber;
    wrappedGrids: {monitoringPoint: MonitoringPoint, selected: boolean, locked?: boolean}[] = [];

	constructor(
		private _imeNumberService: ImeNumberService,
		private snackBar: MdSnackBar,
		public dialogRef: MdDialogRef<ImeGridsDialogComponent>) {

	}

	ngOnInit() {
        if (this.data) {
            let selectedGrids: number [] = this.data.monitoringPoints.map(g => g.ordinal);
            let monitoringPoints = MonitoringPoint.values().filter(g => {
                return g.site.ordinal == this.data.site.ordinal &&
                    g.type.ordinal == MonitoringPointType.GRID.ordinal;
            });
            for (let monitoringPoint of monitoringPoints) {
                this.wrappedGrids.push({monitoringPoint: monitoringPoint, selected: selectedGrids.indexOf(monitoringPoint.ordinal) > -1});
			}
			
			// Automatically add and lock the grids from the instantaneous readings that are linked to the IME.
			if (this.data.instantaneousData) {
				for (let grid of this.data.instantaneousData.map(i => EnumUtils.convertToEnum(MonitoringPoint, i.monitoringPoint).ordinal)) {
					for (let wrappedGrid of this.wrappedGrids) {
						if (wrappedGrid.monitoringPoint.ordinal == grid) {
							wrappedGrid.selected = true;
							wrappedGrid.locked = true;
							break;
						}
					}
				}
			}
        }
	}

	submit() {
		let result: MonitoringPoint[] = this.wrappedGrids.filter(g => g.selected).map(g => g.monitoringPoint);
		this.dialogRef.close(result);
	}

	cancel() {
		this.dialogRef.close();
	}

	listGrids(): string {
		return this.wrappedGrids.filter(g => g.selected).map(g => g.monitoringPoint.name).sort().join(", ");
	}

}