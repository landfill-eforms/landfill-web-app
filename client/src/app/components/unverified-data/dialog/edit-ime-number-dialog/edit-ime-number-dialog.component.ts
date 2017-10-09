import { MdDialogRef } from '@angular/material';
import { OnInit, Component } from '@angular/core';

@Component({
	selector: 'app-edit-ime-number-dialog',
	templateUrl: './edit-ime-number-dialog.component.html'
})
export class EditImeNumberDialogComponent implements OnInit {

	constructor(
		public dialogRef:MdDialogRef<EditImeNumberDialogComponent>) {

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