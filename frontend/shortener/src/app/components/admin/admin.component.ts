import {Component, OnInit} from '@angular/core';
import {ShortService} from "../../services/short.service";
import {ShortUrl} from "../../domain/short-url";
import {ConfigService} from "../../services/config.service";

@Component({
    selector: 'app-admin',
    templateUrl: './admin.component.html',
    styleUrls: ['./admin.component.css']
})
export class AdminComponent implements OnInit {

    username: string = "";
    password: string = "";
    authenticated: boolean = false;
    urlInfoList: ShortUrl[] = [];
    serverUrl: string = "";

    constructor(private shortService: ShortService, private configService : ConfigService) {
    }

    ngOnInit(): void {
        this.serverUrl = this.configService.config.backendUrl;
    }

    authenticate(): void {
        this.shortService.authenticate(this.username, this.password)
            .subscribe(wasSuccessful => {
                this.authenticated = wasSuccessful;
                this.refresh();
            });
        this.username = "";
        this.password = "";
    }

    refresh(): void {
        if (this.authenticated) {
            this.shortService.listUrls()
                .subscribe(urls => {
                    this.urlInfoList = urls.map(urlInfo => new ShortUrl(urlInfo)).sort((a,b) => new Date(a.createdAt).getTime() - new Date(b.createdAt).getTime());
                })
        }
    }
}
