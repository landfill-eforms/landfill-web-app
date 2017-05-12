import { Input, Component } from '@angular/core';

@Component({
	selector: 'app-page-loading-indicator',
	templateUrl: './page-loading-indicator.component.html',
	styleUrls: ['./page-loading-indicator.component.scss']
})
export class PageLoadingIndicatorComponent {

    @Input() message:string = "Loading..."

}