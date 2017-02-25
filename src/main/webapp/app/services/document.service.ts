import {Injectable} from "@angular/core";
import {Http, Headers, URLSearchParams} from "@angular/http";

import "rxjs/operator/toPromise";

import {Document} from "../interfaces/Document";
import {GenericCollection} from "../interfaces/GenericCollection";

@Injectable()
export class DocumentService {

    private headers = new Headers({'Content-Type': 'application/json'});
    private documentUrl = '/documents';

    constructor(private http: Http) {
    }

    getDocument(id: string): Promise<Document> {
        const url = `${this.documentUrl}/${id}`;
        return this.http.get(url)
            .toPromise()
            .then(response => response.json().data as Document)
            .catch(this.handleError);
    }

    getDocuments(pageNum: number, pageSize: number): Promise<GenericCollection<Document>> {
        let params: URLSearchParams = new URLSearchParams();
        params.set('pageSize', pageSize.toString());
        params.set('pageNum', pageNum.toString());

        const url = `${this.documentUrl}/list`;
        return this.http.get(url, {search: params})
            .toPromise()
            .then(response => response.json().data as GenericCollection<Document>)
            .catch(this.handleError);
    }

    create(document: Document): Promise<Document> {
        const url = `${this.documentUrl}/create`;
        return this.http.post(url, JSON.stringify(document), {headers: this.headers})
            .toPromise()
            .then(response => response.json().data as Document)
            .catch(this.handleError);
    }

    update(document: Document): Promise<Document> {
        const url = `${this.documentUrl}/${document.id}/update`;
        return this.http.put(url, JSON.stringify(document), {headers: this.headers})
            .toPromise()
            .then(() => document)
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