import {TemplateService} from "./template.service";
import {Component} from "@angular/core";
import {OnInit} from "@angular/core";
import {Router} from "@angular/router";
import {Template} from "../entity/Template";

@Component({
    selector: 'templates',
    templateUrl: './app/template/template.component.html',
    styleUrls: ['./app/template/template.component.css']
})
export class TemplateComponent implements OnInit {
    templates: Array<Template>;
    // templates =  ['Windstorm', 'Bombasto', 'Magneta', 'Tornado'];
    selectedTemplate: Template;

    constructor(private templateService: TemplateService,
                private router: Router) {}

    getTemplates(pageSize: number, pageNum: number): void {
        this.templateService.getTemplates(pageSize, pageNum)
            .then(templates => this.templates = templates.items);

    }

    ngOnInit(): void {
        this.getTemplates(5, 0);
    }

    onSelect(template: Template) {
        this.selectedTemplate = template;
    }
}