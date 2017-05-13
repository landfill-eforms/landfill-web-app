import { InstrumentType } from './../../../../model/server/persistence/entity/instrument/instrument-type.class';
import { InstrumentTypeService } from './../../../../services/instrument/instrument-type.service';
import { Component, OnInit } from '@angular/core';
import { MdDialog, MdDialogRef, MdSnackBar } from "@angular/material";

@Component({
	selector: 'app-instrument-type-dialog',
	templateUrl: './instrument-type-dialog.component.html'
})
export class InstrumentTypeDialogComponent implements OnInit {

	isNew:boolean;
	instrumentType:InstrumentType;

	constructor(
		public dialogRef:MdDialogRef<InstrumentTypeDialogComponent>,
		private snackBar:MdSnackBar,
		private instrumentTypeService:InstrumentTypeService
	) {}

	ngOnInit() {
		if (!this.instrumentType) {
			this.instrumentType = new InstrumentType();
		}
		else {
			// Clone
			let clone:InstrumentType = new InstrumentType();
			for (let key of Object.keys(this.instrumentType)) {
				clone[key] = this.instrumentType[key];
			}
			this.instrumentType = clone;
		}
	}

	confirm() {
		// TODO Perform data verification before saving.
		if (this.isNew) {
			this.instrumentTypeService.create(this.instrumentType,
				(data) => {
					console.log(data);
					this.dialogRef.close(data);
				},
				(err) => {
					this.snackBar.open(JSON.parse(err.text()).message, "OK", {duration: 5000});
				}
			);
		}
		else {
			this.instrumentTypeService.update(this.instrumentType,
				(data) => {
					console.log(data);
					this.dialogRef.close(data);
				},
				(err) => {
					this.snackBar.open(JSON.parse(err.text()).message, "OK", {duration: 5000});
				}
			);
		}
	}

	cancel() {
		this.dialogRef.close();
	}
	
}