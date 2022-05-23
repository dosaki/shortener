export class ShortUrl {
    identifier: string
    originalURL: string
    createdAt: string
    visits: number

    constructor(urlInfo: any) {
        this.identifier = urlInfo ? urlInfo.identifier : null;
        this.originalURL = urlInfo ? urlInfo.originalURL : null;
        this.createdAt = urlInfo ? urlInfo.createdAt : null;
        this.visits = urlInfo ? urlInfo.visits : null;
    }

    asShortenedUrl(url: string): string {
        return `${url}/${this.identifier}`;
    }
}
