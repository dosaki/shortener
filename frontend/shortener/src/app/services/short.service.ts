import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {map, Observable} from "rxjs";
import {ShortUrl} from "../domain/short-url";
import {Token} from "../domain/token";
import {ConfigService} from "./config.service";

@Injectable()
export class ShortService {

    token: string = "";

    constructor(private http: HttpClient, private configService : ConfigService) {
    }

    sendOriginalUrl(originalUrl: string): Observable<string> {
        return this.http.post<ShortUrl>(this.configService.config.backendUrl, {originalUrl})
            .pipe(map(response => `${this.configService.config.backendUrl}/${response.identifier}`));
    }

    authenticate(username: string, password: string): Observable<boolean> {
        const headers = {
            "Authorization": `Basic ${username}:${password}`
        }
        return this.http.get<Token>(`${this.configService.config.backendUrl}/admin/token`, {headers})
            .pipe(map(response => {
                if (!response || !response.token) {
                    return false;
                }
                this.token = response.token;
                return true;
            }));
    }

    listUrls(): Observable<ShortUrl[]> {
        const headers = {
            "Authorization": `Bearer ${this.token}`
        }
        return this.http.get<ShortUrl[]>(`${this.configService.config.backendUrl}/admin/list`, {headers});
    }
}
