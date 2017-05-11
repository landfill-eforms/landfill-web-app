import { UnsavedCanDeactivate } from './../../../services/candeactivate/unsaved.candeactivate';
import { YesNoDialogComponent } from './../../directives/dialogs/yes-no-dialog/yes-no-dialog.component';
import { MdDialog, MdDialogRef, MdDialogConfig } from '@angular/material';
import { ApplicationSettingsComponent } from './application-settings.component';
import { Router, CanDeactivate, RouterStateSnapshot, ActivatedRouteSnapshot } from '@angular/router';
import { Injectable } from '@angular/core';

@Injectable()
export class ApplicationSettingsCanDeactivate extends UnsavedCanDeactivate<ApplicationSettingsComponent> {

	constructor(dialog:MdDialog, router:Router) {
		super(dialog, router);
	}

	canDeactivate(component:ApplicationSettingsComponent, currentRoute: ActivatedRouteSnapshot, currentState:RouterStateSnapshot, nextState:RouterStateSnapshot) {
		if (!component.unsavedChagnes || this.confirmed) {
			this.confirmed = false;
			return true;
		}
		this.openConfirmationDialog(nextState);
		return false;
	}

}