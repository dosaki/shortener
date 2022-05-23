import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {AdminComponent} from './components/admin/admin.component';
import {NewUrlComponent} from './components/newurl/newurl.component';
import {HttpClientModule} from "@angular/common/http";
import {FormsModule} from "@angular/forms";
import {ShortService} from "./services/short.service";
import {ConfigService} from "./services/config.service";

@NgModule({
    declarations: [
        AppComponent,
        AdminComponent,
        NewUrlComponent
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        HttpClientModule,
        FormsModule
    ],
    providers: [
        ShortService,
        ConfigService
    ],
    bootstrap: [AppComponent]
})
export class AppModule {
}
