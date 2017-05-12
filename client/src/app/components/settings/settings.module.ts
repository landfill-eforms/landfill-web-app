import { SuperAdminPasswordDialogComponent } from './dialog/super-admin-password-dialog/super-admin-password-dialog.component';
import { ApplicationSettingsComponent } from './application-settings/application-settings.component';
import { DirectivesModule } from './../directives/directives.module';
import { CommonModule } from './../common/common.module';
import { NgModule } from '@angular/core';

@NgModule({
    imports: [
        CommonModule,
        DirectivesModule
    ],
    providers: [],
    declarations: [
        ApplicationSettingsComponent,
        SuperAdminPasswordDialogComponent
    ]
})

export class SettingsModule {
    
}