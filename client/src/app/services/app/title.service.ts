import { Injectable } from '@angular/core';
import { AppConstant } from './../../app.constant';
import { Title } from '@angular/platform-browser';

@Injectable()
export class TitleService extends Title {

    private baseTitle:string = "";

    setTitle(newTitle:string):void {
        this.baseTitle = newTitle;
        super.setTitle(this.baseTitle + (AppConstant.TITLE_SUFFIX ? " - " + AppConstant.TITLE_SUFFIX : ""));
    }

    getBaseTitle():string {
        return this.baseTitle;
    }

}