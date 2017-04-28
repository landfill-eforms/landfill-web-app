import { TestBed } from '@angular/core/testing';
export abstract class AbstractSideinfoComponent<T> {

	title:string;

	constructor(title:string) {
		this.title = title;
	}

	abstract getData():T;

	abstract setData(data:T);

}