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
    constructor(){
        // this.keys = Object.keys(this.fieldTypes).filter(Number)
    }
}