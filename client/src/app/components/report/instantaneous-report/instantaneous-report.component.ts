import { StringUtils } from './../../../utils/string.utils';
import { InstantaneousData } from './../../../model/server/persistence/entity/instantaneous/instantaneous-data.class';
import { Site } from './../../../model/server/model/site.enum';
import { DateTimeUtils } from './../../../utils/date-time.utils';
import { SitesService } from './../../../services/sites.service';
import { InstantaneousDataService } from './../../../services/instantaneous-data.service';
import { OnInit, Component } from '@angular/core';

@Component({
    selector: 'app-instantaneous-report',
    templateUrl: './instantaneous-report.component.html',
    styleUrls: ['./instantaneous-report.component.scss']
})
export class InstantaneousReportComponent implements OnInit {

	isDataLoaded:boolean = true;
	data:InstantaneousData[] = [];
	sites:any = {
        list: [],
        selected: {}
    }
	dateRange:any = {
		start: -1,
		end: -1
	}
	sort:any = {
		current: "",
		reversed: false
	}

    constructor (
        private instantaneousDataService:InstantaneousDataService,
        private sitesService:SitesService
        ) {}

    ngOnInit() {

        // Get list of active sites
        this.sites.list = Site.values().filter(s => s.active);
    }

    getData() {
		console.log(this.dateRange)
		this.data = [];
		this.isDataLoaded = false;
        this.instantaneousDataService.getBySiteAndDate((data) => {
            console.log(data);
            for (let i = 0; i < data.length; i++) {
				this.data.push(this.instantaneousDataService.processDataPoint(data[i]));
			}
			this.isDataLoaded = true;
        }, this.sites.selected.name.toUpperCase(), this.dateRange.start, this.dateRange.end);
    }

	onStartDateChange(event) {
		this.dateRange.start = event.target.valueAsNumber || -1;
	}

	onEndDateChange(event) {
		this.dateRange.end = event.target.valueAsNumber || -1;
	}

	// TODO Move this to a util class.
	private stringSortFunction(a:string, b:string, reversed:boolean):number {
		if (a > b) return reversed ? -1 : 1;
		if (a == b) return 0;
		if (a < b) return reversed ? 1 : -1;
	}

}