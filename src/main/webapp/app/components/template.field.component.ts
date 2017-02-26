import {Component} from "@angular/core";
import {Field} from "../interfaces/Field";

@Component({
    moduleId: module.id,
    selector: 'template-fields',
    templateUrl: 'template.field.component.html',
    styleUrls: ['template.field.component.css']
})
export class TemplateFieldComponent {
    fields: Array<Field>;
    states = [{code: '0', name: 'TEXTAREA'},
        {code: '1', name: 'INPUT'},
        {code: '2', name: 'CHECKBOX'}];

    constructor() {
        // this.keys = Object.keys(this.fieldTypes).filter(Number)
    }
}