import {Injectable} from "@angular/core";
import {Http} from "@angular/http";
import {Template} from "../entity/Template";
import {GenericCollection} from "../entity/GenericCollection";

@Injectable
export class TemplateService {

    private headers = {'Content-Type': 'application/json'};
    private templateUrl = '/templates';

    constructor(private http: Http) {
    }

    getTemplate(id: string): Promise<Template> {
        const url = `${this.templateUrl}/${id}`;
        return this.htpp.get(url)
            .toPromise()
            .then(response => response.json().data as Template)
            .catch(this.handleError);
    }

    getTemplates(pageSize: number, pageNum: number): Promise<GenericCollection<Template>> {
        const url = `${this.templateUrl}/list`;
        return this.http.get(url, {'pageSize': pageSize, 'pageNum': pageNum})
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
        return this.http(url, Json.stringify(template), {headers: this.headers})
            .toPromise()
            .then(response => response.json().data as Template)
            .catch(this.handleError);
    }

    update(template: Template): Promise<Template> {
        const url = `${this.templateUrl}/${template.id}/update`;
        return this.http(url, Json.stringify(template), {headers: this.headers})
            .toPromise()
            .then(() => template)
            .catch(this.handleError);
    }

    private handleError(error: any): Promise<any> {
        console.error('An error occurred', error);
        return Promise.reject(error.message || error);
    }
}