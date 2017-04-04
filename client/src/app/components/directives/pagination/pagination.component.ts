import { Component, Input, Output, OnChanges, EventEmitter } from '@angular/core';

@Component({
    selector: 'app-pagination',
    templateUrl: './pagination.component.html',
    styleUrls: ['./pagination.component.scss']
})
export class PaginationComponent implements OnChanges {

    @Input() paginfo:Paginfo = new Paginfo();
    // @Input() totalRows:number = 0;
    @Input() extraPadding:number = 0;
    @Input() showPageSelector:boolean = false;
    @Input() showFirstLastPage:boolean = false;

    @Output() changed = new EventEmitter();

    readonly displayedRange:{min:number, max:number} = {
        min: 1,
        max: 1
    };

    readonly displayedRowsSelection:number[] = [10, 25, 50, 100];

    availablePagesSelection:number[] = [1];

	ngOnChanges() {
        console.log("Pagination change detected.");
        this.validate();
        this.update();
	}

    validate() {
        
    }

    update() {

        // Make sure selected page is valid.
        if (this.paginfo.currentPage < 1) {
            this.paginfo.currentPage = 1;
        }
        let pageCount = ~~(this.paginfo.totalRows / this.paginfo.displayedRows) + 1;
        if (this.paginfo.currentPage > pageCount) {
            this.paginfo.currentPage = pageCount;
        }

        // Update the displayed range.
        let pageMax = this.paginfo.currentPage * this.paginfo.displayedRows;
        this.displayedRange.max = pageMax > this.paginfo.totalRows ? this.paginfo.totalRows : pageMax;
        this.displayedRange.min = (this.paginfo.currentPage - 1) * this.paginfo.displayedRows + 1;
        this.availablePagesSelection = this.generateAvaliablePagesArray();

        // Emit event through the changed emitter.
        this.changed.emit();
    }

    generateAvaliablePagesArray():number[] {
        let pageCount = ~~(this.paginfo.totalRows / this.paginfo.displayedRows) + 1;
        return Array(pageCount).fill(0).map((x,i) => i + 1);
    }

    changeDisplayedRows() {
        this.validate();
        this.update();
    }

    changeCurrentPage() {
        this.validate();
        this.update();
    }

    firstPage() {
        this.paginfo.currentPage = 1;
        this.update();
    }

    prevPage() {
        if (this.paginfo.currentPage > 1) {
            this.paginfo.currentPage--;
            this.update();
        }
    }

    nextPage() {
        let pageCount = ~~(this.paginfo.totalRows / this.paginfo.displayedRows) + 1;
        if (this.paginfo.currentPage < pageCount) {
            this.paginfo.currentPage++;
            this.update();
        }
    }

    lastPage() {
        this.paginfo.currentPage = ~~(this.paginfo.totalRows / this.paginfo.displayedRows) + 1;
        this.update();
    }

}

export class Paginfo {
    displayedRows:number = 10;
    currentPage:number = 1;
    totalRows:number = 0;
}