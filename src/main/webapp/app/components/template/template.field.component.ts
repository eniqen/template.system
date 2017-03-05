import {Component} from "@angular/core";
import {Field} from "../../interfaces/Field";

@Component({
    moduleId: module.id,
    selector: 'template-fields',
    templateUrl: 'template.field.component.html',
    styleUrls: ['template.field.component.css']
})
export class TemplateFieldComponent {
    fields: Array<Field> = [];
    field: Field = new Field();
    types = [
        {code: '0', name: 'TEXTAREA'},
        {code: '1', name: 'INPUT'},
        {code: '2', name: 'CHECKBOX'}
    ];

    constructor() {

    }


    addField() {
        this.field.order = this.fields.length;
        this.fields.push(this.field);
        this.field = new Field();
    }
}