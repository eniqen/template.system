import {Injectable} from "@angular/core";
import {Http, Headers} from "@angular/http";

import "rxjs/operator/toPromise";

import {Document} from "../entity/Document";
import {Collection} from "../entity/CollectionDto";

@Injectable
export class DocumentService {

    private headers = new Headers({'Content-Type': 'application/json'});
    private documentUrl = '/documents';

    constructor(private http: Http) {
    }

    getDocument(id: string): Promise<Document> {
        const url = `${this.documentUrl}/${id}`;
        return this.http.get(url)
            .toPromise()
            .then(()=> response.json().data as Document)
            .catch(this.handleError);
    }

    getDocuments(pageNum: number, pageSize: number): Promise<Collection<Document>> {
        const url = `${this.documentUrl}/list`;
        return this.http.get(url)
            .toPromise()
            .then(response => response.json().data as Collection<Document>)
            .catch(this.errorHandler);
    }

    create(document: Document): Promise<Document> {
        const url = `${this.documentUrl}/create`;
        return this.http.post(url, Json.stringify(document), {headers: this.headers})
            .toPromise()
            .then(response => response.json().data as Document)
            .catch(this.handleError);
    }

    update(document: Document): Promise<Document> {
        const url = `${this.documentUrl}/${id}/update`;
        this.http.put(url, Json.stringify(document), {headers: this.headers})
            .toPromise()
            .then(()=> document)
            .catch(this.handleError);
    }

    delete(id: string): Promise<void> {
        const url = `${this.documentUrl}/${id}/delete`;
        return this.http.delete(url)
            .toPromise()
            .then(()=> null)
            .catch(this.handleError);
    }

    private handleError(error: any): Promise<any> {
        console.error('An error occurred', error);
        return Promise.reject(error.message || error);
    }
}