import { NavigationToolbarComponent } from './navigation-toolbar/navigation-toolbar.component';
import { NavigationDrawerComponent } from './navigation-drawer/navigation-drawer.component';
import { NgModule } from '@angular/core';
import { CommonModule } from '../common/common.module'
import { NavigationBaseComponent } from './navigation-base/navigation-base.component';

@NgModule({
    imports: [
        CommonModule
    ],
    providers: [],
    declarations: [
        NavigationBaseComponent,
        NavigationDrawerComponent,
        NavigationToolbarComponent
    ]
})

export class NavigationModule {
    
}