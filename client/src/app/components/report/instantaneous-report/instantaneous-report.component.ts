import { StringUtils } from './../../../utils/string.utils';
import { InstantaneousData } from './../../../model/server/persistence/entity/serviceemission/instantaneous/instantaneous-data.class';
import { Site } from './../../../model/server/persistence/enums/location/site.enum';
import { DateTimeUtils } from './../../../utils/date-time.utils';
import { InstantaneousDataService } from './../../../services/instantaneous/instantaneous-data.service';
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

    constructor (private instantaneousDataService:InstantaneousDataService) {
        
    }

    ngOnInit() {

        // Get list of active sites
        this.sites.list = Site.values().filter(s => s.active);
    }

    getData() {
		console.log(this.dateRange)
		this.data = [];
		this.isDataLoaded = false;
        this.instantaneousDataService.getBySiteAndDate(this.sites.selected.name.toUpperCase(), this.dateRange.start, this.dateRange.end,
            (data) => {
                console.log(data);
                for (let i = 0; i < data.length; i++) {
                    this.data.push(this.instantaneousDataService.processDataPoint(data[i]));
                }
                this.isDataLoaded = true;
            }
        );
    }

	onStartDateChange(event) {
		this.dateRange.start = event.target.valueAsNumber || -1;
	}

	onEndDateChange(event) {
		this.dateRange.end = event.target.valueAsNumber || -1;
	}

}