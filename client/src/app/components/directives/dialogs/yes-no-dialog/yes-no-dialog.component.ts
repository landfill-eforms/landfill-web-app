import { Component } from '@angular/core';
import { MdDialogRef } from '@angular/material';

@Component({
	selector: 'app-yes-no-dialog',
	templateUrl: './yes-no-dialog.component.html'
})
export class YesNoDialogComponent {

	title:string;
	prompt:string[];
	confirmLabel:string;
	cancelLabel:string;

	constructor(public dialogRef:MdDialogRef<YesNoDialogComponent>) {
		
	}

	confirm() {
		this.dialogRef.close(true);
	}

	cancel() {
		this.dialogRef.close(false);
	}

}