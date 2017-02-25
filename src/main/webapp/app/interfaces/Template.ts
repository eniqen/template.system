import {Field} from "./Field";

export interface Template {
    id: string;
    name: string;
    description: string;
    fields: Array<Field>;
}