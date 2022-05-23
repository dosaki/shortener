import {Component, OnInit} from '@angular/core';
import {ShortService} from "../../services/short.service";

@Component({
    selector: 'app-newurl',
    templateUrl: './newurl.component.html',
    styleUrls: ['./newurl.component.css']
})
export class NewUrlComponent implements OnInit {

    url: string = "";
    shortUrl : string = "";

    constructor(private shortService: ShortService) {
    }

    ngOnInit(): void {
    }

    sendUrl(): void {
        this.shortService.sendOriginalUrl(this.url)
            .subscribe((shortUrl) => this.shortUrl = shortUrl);
    }

}
