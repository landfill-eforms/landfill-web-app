import { Paginfo, PaginationComponent } from './../../../components/directives/pagination/pagination.component';
import { InputStatus } from './../../../utils/input.utils';
import { Sort, SortUtils } from './../../../utils/sort.utils';

/** A component extending from this abstract class contains a data table that can be sorted, filtered, and paginated. */
export abstract class AbstractDataTableComponent<T> {

	abstract pagination:PaginationComponent;

	isDataLoaded:boolean;
	data:T[];

	sort:Sort = {
		current: "id",
		reversed: false
	}

	abstract sortProperties:any;

	// Filter variables.
	showFilters:boolean = false;
	filteredRowsCount:number = 0;
	filteredData:T[] = [];
	abstract filters:any;
	textFilterStatus:InputStatus = {
		valid: true,
		errorMessage: ""
	};

	paginfo:Paginfo = new Paginfo();
	paginatedData:T[] = [];

	constructor() {
		
	}

	sortBy(sortBy:string) {
		SortUtils.sortAndUpdate(this.sort, sortBy, this.data, this.sortProperties[sortBy]);
		this.applyFilters();
	}

	toggleFilters() {
		if (this.showFilters) {
			this.showFilters = false;
			this.resetFilters();
		}
		else {
			this.showFilters = true;
		}
	}

	abstract applyFilters();

	/** Implementing method should call applyFilters() at the end of the method. */
	abstract resetFilters();

	applyPagination() {
		this.paginatedData = this.filteredData.filter((o, i) => {
			return i >= (this.paginfo.currentPage - 1) * this.paginfo.displayedRows && i < this.paginfo.currentPage * this.paginfo.displayedRows;
		});
	}

}