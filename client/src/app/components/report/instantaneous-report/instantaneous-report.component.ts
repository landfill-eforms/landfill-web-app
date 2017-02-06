import { Site } from './../../../model/server/model/site.enum';
import { DateTimeUtils } from './../../../utils/date-time-utils';
import { SitesService } from './../../../services/sites.service';
import { InstantaneousDataService } from './../../../services/instantaneous-data.service';
import { InstantaneousData } from './../../../model/server/persistence/entity/instantaneous-data.class';
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
	sort:any = {
		current: "",
		reversed: false
	}

    // Util functions
    getDate = DateTimeUtils.getDate;
    getTime = DateTimeUtils.getTime;

    constructor (
        private instantaneousDataService:InstantaneousDataService,
        private sitesService:SitesService
        ) {}

    ngOnInit() {

        // Get list of active sites
        this.sites.list = Site.values.filter(s => s.active);
    }

    getStatus(reading:number):string {
        reading /= 100;
        if (reading > 500) {
            return "hotspot";
        }
        else if (reading > 300) {
            return "warmspot"
        }
        return "";
    }

    getData() {
		this.data = [];
		this.isDataLoaded = false;
        this.instantaneousDataService.getBySiteName((data) => {
            console.log(data);
            for (let i = 0; i < data.length; i++) {
				this.data.push(this.instantaneousDataService.processDataPoint(data[i]));
			}
			this.isDataLoaded = true;
        }, this.sites.selected.name.toUpperCase()); // TODO Add name to enums.
    }

	sortByDate() {
		if (this.sort.current === "date") {
			this.sort.reversed = !this.sort.reversed;
		}
		else {
			this.sort.current = "date";
			this.sort.reversed = false;
		}
		this.data.sort((a, b) => (a.startTime - b.startTime) * (this.sort.reversed ? -1 : 1));
	}

	sortByUser() {
		if (this.sort.current === "user") {
			this.sort.reversed = !this.sort.reversed;
		}
		else {
			this.sort.current = "user";
			this.sort.reversed = false;
		}
		this.data.sort((a, b) => {
			let compareName:number = this.stringSortFunction((a.user.person.lastname + a.user.person.firstname).toLowerCase(), (b.user.person.lastname + b.user.person.firstname).toLowerCase(), this.sort.reversed);
			if (compareName != 0) {
				return compareName;
			}
			return (a.startTime - b.startTime) * (this.sort.reversed ? -1 : 1);
		});
	}

	sortByGrid() {
		if (this.sort.current === "grid") {
			this.sort.reversed = !this.sort.reversed;
		}
		else {
			this.sort.current = "grid";
			this.sort.reversed = false;
		}
		this.data.sort((a, b) => {
			let compareGrid = this.stringSortFunction(a.monitoringPoint.name, b.monitoringPoint.name, this.sort.reversed);
			if (compareGrid != 0) {
				return compareGrid;
			}
			return (a.startTime - b.startTime) * (this.sort.reversed ? -1 : 1);
		});
	}

	sortByMethaneLevel() {
		if (this.sort.current === "methaneLevel") {
			this.sort.reversed = !this.sort.reversed;
		}
		else {
			this.sort.current = "methaneLevel";
			this.sort.reversed = false;
		}
		this.data.sort((a, b) => (a.methaneLevel - b.methaneLevel) * (this.sort.reversed ? -1 : 1));
	}

	// TODO Move this to a util class.
	private stringSortFunction(a:string, b:string, reversed:boolean):number {
		if (a > b) return reversed ? -1 : 1;
		if (a == b) return 0;
		if (a < b) return reversed ? 1 : -1;
	}

}