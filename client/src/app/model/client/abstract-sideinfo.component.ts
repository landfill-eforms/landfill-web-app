export abstract class AbstractSideinfoComponent {

	title:string;

	constructor(title:string) {
		this.title = title;
	}

	abstract getData():any;

	abstract setData(data:any);

}