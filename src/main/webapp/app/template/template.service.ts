import {Injectable} from "@angular/core";
import {Http, Headers, URLSearchParams} from "@angular/http";
import {Template} from "../entity/Template";
import {GenericCollection} from "../entity/GenericCollection";

import 'rxjs/add/operator/toPromise';

@Injectable()
export class TemplateService {

    private headers = new Headers({'Content-Type': 'application/json'});
    private templateUrl = '/templates';

    constructor(private http: Http) {
    }

    getTemplate(id: string): Promise<Template> {
        const url = `${this.templateUrl}/${id}`;
        return this.http.get(url)
            .toPromise()
            .then(response => response.json().data as Template)
            .catch(this.handleError);
    }

    getTemplates(pageSize: number, pageNum: number): Promise<GenericCollection<Template>> {
        let params: URLSearchParams = new URLSearchParams();
        params.set('pageSize', pageSize.toString());
        params.set('pageNum', pageNum.toString());

        const url = `${this.templateUrl}/list`;
        return this.http.get(url, {search: params})
            .toPromise()
            .then(response => response.json().data as GenericCollection<Template>)
            .catch(this.handleError);
    }

    delete(id: string): Promise<void> {
        const url = `${this.templateUrl}/${id}/delete`;
        return this.http.delete(url, {headers: this.headers})
            .toPromise()
            .then(()=> null)
            .catch(this.handleError);
    }

    create(template: Template): Promise<Template> {
        const url = `${this.templateUrl}/create`;
        return this.http.post(url, JSON.stringify(template), {headers: this.headers})
            .toPromise()
            .then(response => response.json().data as Template)
            .catch(this.handleError);
    }

    update(template: Template): Promise<Template> {
        const url = `${this.templateUrl}/${template.id}/update`;
        return this.http.put(url, JSON.stringify(template), {headers: this.headers})
            .toPromise()
            .then(() => template)
            .catch(this.handleError);
    }

    private handleError(error: any): Promise<any> {
        console.error('An error occurred', error);
        return Promise.reject(error.message || error);
    }
}