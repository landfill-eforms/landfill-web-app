import { MdSnackBar } from '@angular/material';
import { MdDialogRef } from '@angular/material';
import { OnInit, Component } from '@angular/core';

@Component({
	selector: 'app-ime-grids-dialog',
	templateUrl: './ime-grids-dialog.component.html'
})
export class ImeGridsDialogComponent implements OnInit {

	constructor(
		private snackBar: MdSnackBar,
		public dialogRef: MdDialogRef<ImeGridsDialogComponent>) {

	}

	ngOnInit() {

	}

	submit() {
		// TODO Implement this.
	}

	cancel() {
		this.dialogRef.close();
	}

}