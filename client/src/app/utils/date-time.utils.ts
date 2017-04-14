export class DateTimeUtils {

    static getDate(datetime:number):string {
        if (datetime == null) {
            return "";
        }
        let date:Date = new Date(datetime);
        return (date.getMonth() + 1) + "/" + date.getDate() + "/" + date.getFullYear();
    }

    static getTime(datetime:number):string {
        let date:Date = new Date(datetime);
        let minutes:number = date.getMinutes();
        return date.getHours() + ":" + (minutes < 10 ? "0" + minutes : minutes);
    }

}