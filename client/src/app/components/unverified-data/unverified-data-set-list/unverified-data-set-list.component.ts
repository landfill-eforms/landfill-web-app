import { Sort, SortUtils } from './../../../utils/sort.utils';
import { MonitoringPoint } from './../../../model/server/persistence/enums/monitoring-point.enum';
import { EnumUtils } from './../../../utils/enum.utils';
import { Site } from './../../../model/server/persistence/enums/site.enum';
import { DateTimeUtils } from './../../../utils/date-time.utils';
import { StringUtils } from './../../../utils/string.utils';
import { UnverifiedDataService } from './../../../services/unverified/unverified-data-set.service';
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

	sort:Sort = {
		current: "id",
		reversed: false
	}

	sortProperties:any = {
		site: [
			"site.constantName",
			"uploadedDate",
			"filename"
		],
		uploadedBy: [
			"uploadedBy",
			"uploadedDate",
			"filename"
		],
		uploadedDate: [
			"uploadedDate",
			"filename"
		],
		modifiedBy: [
			"modifiedBy",
			"modifiedDate",
			"filename"
		],
		modifiedDate: [
			"modifiedDate",
			"filename"
		]
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

	sortBy(sortBy:string) {
		SortUtils.sortAndUpdate(this.sort, sortBy, this.dataSets, this.sortProperties[sortBy]);
	}

}