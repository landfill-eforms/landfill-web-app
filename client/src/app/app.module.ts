import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HttpModule} from '@angular/http';

import {AppRoutes, Routing, AppRouterProviders} from './app.routing';
import {AppComponent} from './app.component';

import {TestModule} from './components/test/test.modules';

import {InstantaneousDataService} from './services/instantaneous-data.service';
import {SitesService} from './services/sites.service';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpModule,
    //AppRoutes,
    Routing,
    TestModule,
  ],
  providers: [
    //AppRouterProviders,
    InstantaneousDataService,
    SitesService,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
