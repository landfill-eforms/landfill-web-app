import {Component} from '@angular/core';
import {Http, Response, Headers, RequestOptions} from '@angular/http';
//import {Observable} from 'rxjs/Rx';
import 'rxjs/Rx';

@Component({
  selector: 'sleep-test',
  templateUrl: './sleep-test.component.html',
  styleUrls: ['./sleep-test.component.scss']
})
export class SleepTestComponent {
    title = 'app works!';
    url = 'http://localhost:8080/test/sleep';
    dataValue:number;
    dataGrid:string;
    searchGrid:string;
    sleepData:any[] = [];

    constructor (
        private http:Http
        ) {}

    ngOnInit() {

    }

    getTest(grid:string) {
        this.getSleep((data) => {
            console.log(data);
            this.sleepData = data;
        }, grid);
    }

    insertTest(grid:string, value:number) {
        let sleep = {
            key: 0,
            grid: grid,
            value: value,
        }
        this.postSleep((data) => {
            console.log(data);
        }, [sleep]);
    }

    getSleep(callback:(data) => void, grid:string) {
        //return this.http.get(this.url).map((res:Response) => res.json()).catch((error:any) => Observable.throw(error.json().error || 'Server error'));
        this.http.get(this.url + "/" + grid).map((res:Response) => res.json()).subscribe(
                data => callback(data),
                err => console.log(err),
                () => console.log("request completed")
            );
    }

    postSleep(callback:(data) => void, asdf:any[]) {
    //return this.http.get(this.url).map((res:Response) => res.json()).catch((error:any) => Observable.throw(error.json().error || 'Server error'));
        this.http.post(this.url, asdf).map((res:Response) => res.json()).subscribe(
            data => callback(data),
            err => console.log(err),
            () => console.log("request completed")
        );
    }

    getDate(datetime:number):string {
        let date:Date = new Date(datetime);
        return (date.getMonth() + 1) + "/" + date.getDate() + "/" + date.getFullYear();
    }

    getTime(datetime:number):string {
        let date:Date = new Date(datetime);
        return date.getHours() + ":" + date.getMinutes();
    }
}