import {NgModule} from '@angular/core';
import {CommonModule} from '../common/common.module'
import {NavigationBaseComponent} from './navigation-base/navigation-base.component';

@NgModule({
    imports: [
        CommonModule
    ],
    providers: [],
    declarations: [
        NavigationBaseComponent,
    ]
})

export class NavigationModule {
    
}