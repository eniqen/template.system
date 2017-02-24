import {Field} from "./Field";

export class Template {
    id?: string;
    name: string;
    description?: string;
    fields: Array<Field>;
}