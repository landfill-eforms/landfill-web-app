import { MdDialogRef } from '@angular/material';
import { OnInit, Component } from '@angular/core';

@Component({
	selector: 'app-edit-ise-number-dialog',
	templateUrl: './edit-ise-number-dialog.component.html'
})
export class EditIseNumberDialogComponent implements OnInit {

	constructor(
		public dialogRef:MdDialogRef<EditIseNumberDialogComponent>) {

	}

	ngOnInit() {

	}

	confirm() {
		// TODO Implement this.
	}

	/** Closes the dialog without passing any data back. */
	cancel() {
		this.dialogRef.close();
	}

}