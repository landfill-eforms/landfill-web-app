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
        return (hours > 12 ? hours - 12 : hours) + ":" + (minutes < 10 ? "0" + minutes : minutes) + " " + (hours >= 12 ? "PM" : "AM");
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
        let timeStr:string = (hours > 12 ? hours - 12 : hours) + ":" + (minutes < 10 ? "0" + minutes : minutes) + " " + (hours >= 12 ? "PM" : "AM");
        return dateStr + ", "+ timeStr;
    }

}