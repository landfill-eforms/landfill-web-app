import { Injectable } from '@angular/core';
import { AppConstant } from './../../app.constant';
import { Title } from '@angular/platform-browser';

@Injectable()
export class TitleService extends Title {

    setTitle(newTitle:string):void {
        super.setTitle(newTitle + " - " + AppConstant.BASE_TITLE);
    }

}