import { ArrayUtils } from './../../../utils/array.utils';
import { MonitoringPoint } from './../../../model/server/model/monitoring-point.enum';
import { EnumUtils } from './../../../utils/enum.utils';
import { Site } from './../../../model/server/model/site.enum';
import { DateTimeUtils } from './../../../utils/date-time.utils';
import { StringUtils } from './../../../utils/string.utils';
import { UnverifiedDataService } from './../../../services/unverified-data-set.service';
import { UnverifiedDataSet } from './../../../model/server/persistence/entity/unverified/unverified-data-set.class';
import { OnInit, Component } from '@angular/core';


@Component({
	selector: 'app-unverified-data-set-list',
	templateUrl: './unverified-data-set-list.component.html',
	styleUrls: ['./unverified-data-set-list.component.scss']
})
export class UnverifiedDataSetsComponent implements OnInit {

	StringUtils = StringUtils;
	DateTimeUtils = DateTimeUtils;
	

	dataSets:UnverifiedDataSet[] = [];
	isDataLoaded:boolean = false;
	sort:any = {
		current: "id",
		reversed: false
	}

	constructor(
		private unverifiedDataService:UnverifiedDataService
	) {}

	ngOnInit() {
		this.unverifiedDataService.getAll((data) => {
			for (let i = 0; i < data.length; i++) {
				let dataSet = data[i];
				dataSet["site"] = EnumUtils.convertToEnum(Site, dataSet["site"]);
				for (let j = 0; j < dataSet.unverifiedInstantaneousData.length; j++) {
					dataSet.unverifiedInstantaneousData[j]["monitoringPoint"] = EnumUtils.convertToEnum(MonitoringPoint, dataSet.unverifiedInstantaneousData[j]["monitoringPoint"]);
				}
				this.dataSets.push(this.unverifiedDataService.checkForErrors(dataSet));
			}
			console.log(this.dataSets);
			this.isDataLoaded = true;
		});
	}

	sortBySite() {
		if (this.sort.current === "site") {
			this.sort.reversed = !this.sort.reversed;
		}
		else {
			this.sort.current = "site";
			this.sort.reversed = false;
		}
		let properties:string[] = [
			"site.constantName",
			"uploadedDate",
			"filename"
		]
		ArrayUtils.sort(this.dataSets, properties, this.sort.reversed, false);
	}

	sortByUploadedDate() {
		if (this.sort.current === "uploadedDate") {
			this.sort.reversed = !this.sort.reversed;
		}
		else {
			this.sort.current = "uploadedDate";
			this.sort.reversed = false;
		}
		let properties:string[] = [
			"uploadedDate",
			"site.constantName",
			"filename"
		]
		ArrayUtils.sort(this.dataSets, properties, this.sort.reversed, false);
	}

	// TODO Move this to a util class.
	private stringSortFunction(a:string, b:string, reversed:boolean):number {
		if (a == b) return 0;
		return (a > b ? 1 : -1) * (reversed ? -1 : 1);
	}

}