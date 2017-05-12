import { Month } from './../model/client/enums/month.enum';
export class DateTimeUtils {

    static getDate(datetime:number):string {
        if (datetime == null) {
            return "";
        }
        let date:Date = new Date(datetime);
        return (date.getMonth() + 1) + "/" + date.getDate() + "/" + date.getFullYear();
    }

    static getTime(datetime:number):string {
        if (datetime == null) {
            return "";
        }
        let date:Date = new Date(datetime);
        let minutes:number = date.getMinutes();
        let hours:number = date.getHours();
        return (hours == 0 ? 12 : (hours > 12 ? hours - 12 : hours)) + ":" + (minutes < 10 ? "0" + minutes : minutes) + " " + (hours >= 12 ? "PM" : "AM");
    }

    static getDateTime(datetime:number):string {
        if (datetime == null) {
            return "";
        }
        let date:Date = new Date(datetime);
        return DateTimeUtils.getDate(datetime) + " " + DateTimeUtils.getTime(datetime);
    }

    static getDetailedDateTime(datetime:number):string {
        if (datetime == null) {
            return "";
        }
        let date:Date = new Date(datetime);
        let now:Date = new Date();
        let minutes:number = date.getMinutes();
        let hours:number = date.getHours();
        let isToday:boolean = date.getFullYear() == now.getFullYear() && date.getMonth() == now.getMonth() && date.getDate() == now.getDate();
        let dateStr:string = isToday ? "Today" : Month.values()[date.getMonth()].shortName + " " + date.getDate() + ", " + date.getFullYear();
        let timeStr:string = (hours == 0 ? 12 : (hours > 12 ? hours - 12 : hours)) + ":" + (minutes < 10 ? "0" + minutes : minutes) + " " + (hours >= 12 ? "PM" : "AM");
        return dateStr + ", "+ timeStr;
    }

    static parseTime(time:string):number {

        // Find colon
        let colonIdx:number = time.indexOf(":");
        if (colonIdx < 0) {
            return NaN;
        }

        // Parse hour
        let hour:number = Number(time.substring(0, colonIdx));
        if (isNaN(hour)) {
            return NaN;
        }

        // Parse minutes
        let minute:number = Number(time.substr(colonIdx + 1, 2));
        if (isNaN(minute)) {
            return NaN;
        }

        // TODO Make this case insensitive.
        if (time.endsWith("PM")) {
            if (hour < 12) {
                hour += 12;
            }
        }
        else if (time.endsWith("AM")) {
            if (hour >= 12) {
                hour -= 12;
            }
        }

        return new Date(0, 0, 0, hour, minute, 0, 0).getTime();
    }

    /** Filters out the date informatino from a long data value, and returns only the time information. */
    static filterTimeOnly(date:number):number {
        if (date == null) {
            return NaN;
        }
		let d = new Date(date);
		return new Date(0, 0, 0, d.getHours(), d.getMinutes(), d.getSeconds(), d.getMilliseconds()).getTime();
	}

    static mergeDateTime(date:number, time:number):number {
        if (date == null || time == null) {
            return NaN;
        }
        let d:Date = new Date(date);
        let t:Date = new Date(time);
        return new Date(d.getFullYear(), d.getMonth(), d.getDate(), t.getHours(), t.getMinutes(), t.getSeconds(), t.getMilliseconds()).getTime()
    }

}