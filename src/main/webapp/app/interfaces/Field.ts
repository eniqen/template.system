import {FieldType} from "./FieldType";
export class Field {
    constructor(public id: string = '',
                public name: string = '',
                public description: string = '',
                public type: string = '',
                public order: number = 0,
                public value: string = '') {
    }
}
