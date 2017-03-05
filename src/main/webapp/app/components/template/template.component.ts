import {TemplateService} from "../../services/template.service";
import {Component} from "@angular/core";
import {OnInit} from "@angular/core";
import {Router} from "@angular/router";
import {Template} from "../../interfaces/Template";

@Component({
    moduleId: module.id,
    selector: 'templates',
    templateUrl: 'template.component.html',
    styleUrls: ['template.component.css']
})
export class TemplateComponent implements OnInit {
    templates: Array<Template>;
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

    // onSelect(template: Template) {
    //     this.router.navigate(['/templates', template.id]);
    // }
}