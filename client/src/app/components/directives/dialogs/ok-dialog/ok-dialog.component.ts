import { Component } from '@angular/core';
import { MdDialogRef } from '@angular/material';

@Component({
	selector: 'app-ok-dialog',
	templateUrl: './ok-dialog.component.html'
})
export class OkDialogComponent {

	title:string;
	prompt:string[];
	confirmLabel:string;

	constructor(public dialogRef:MdDialogRef<OkDialogComponent>) {
		
	}

	confirm() {
		this.dialogRef.close(true);
	}

}