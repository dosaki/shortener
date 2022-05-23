import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { tap } from 'rxjs/operators';

@Injectable()
export class ConfigService {

    public config = {
        "backendUrl": "http://localhost:8080"
    };

    constructor(private httpClient: HttpClient) {
        this.httpClient.get<any>('config.json')
            .subscribe(data  => {
                console.log(data)
                this.config.backendUrl = data.backendUrl
            });
    }
}