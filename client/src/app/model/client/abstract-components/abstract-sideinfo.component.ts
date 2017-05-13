import { StringUtils } from './../../../utils/string.utils';
import { DateTimeUtils } from './../../../utils/date-time.utils';

export abstract class AbstractSideinfoComponent<T> {

	DateTimeUtils = DateTimeUtils;
	StringUtils = StringUtils;

	title:string;

	constructor(title:string) {
		this.title = title;
	}

	abstract getData():T;

	abstract setData(data:T);

}