import { YesNoDialogComponent } from './../../components/directives/dialogs/yes-no-dialog/yes-no-dialog.component';
import { MdDialog, MdDialogRef, MdDialogConfig } from '@angular/material';
import { Router, CanDeactivate, RouterStateSnapshot, ActivatedRouteSnapshot } from '@angular/router';
import { Injectable } from '@angular/core';

@Injectable()
export abstract class UnsavedCanDeactivate<T> implements CanDeactivate<T> {

	private dialog:MdDialog;
	private router:Router;

	protected confirmed:boolean;

	protected readonly dialogTitle:string = "Unsaved Changes";
	protected readonly dialogPrompt:string = "There are unsaved changes on this page. If you navigate away, all unsaved changes will be lost. Would you like to continue?";
	protected readonly dialogConfirmLabel:string = "CONTINUE";
	protected readonly dialogCancelLabel:string = "CANCEL";

	constructor(dialog:MdDialog, router:Router) {
		this.dialog = dialog;
		this.router = router;
	}

	abstract canDeactivate(component:T, currentRoute: ActivatedRouteSnapshot, currentState:RouterStateSnapshot, nextState:RouterStateSnapshot);

	protected openConfirmationDialog(nextState:RouterStateSnapshot) {
		let dialogConfig:MdDialogConfig = new MdDialogConfig();
		dialogConfig.width = '640px';
		let dialogRef:MdDialogRef<YesNoDialogComponent> = this.dialog.open(YesNoDialogComponent, dialogConfig);
		dialogRef.componentInstance.title = this.dialogTitle;
		dialogRef.componentInstance.prompt = [this.dialogPrompt];
		dialogRef.componentInstance.confirmLabel = this.dialogConfirmLabel;
		dialogRef.componentInstance.cancelLabel = this.dialogCancelLabel;
		dialogRef.afterClosed().subscribe(result => {
			if (result) {
				this.confirmed = true;
				this.router.navigate([nextState.url]);
			}
		});
	}

}