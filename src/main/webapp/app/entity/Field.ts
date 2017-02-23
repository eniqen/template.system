export interface Field {
    id: string
    name: string
    description: string
    type: FieldType
    order: number
    value: string
}

enum  FieldType {TEXTBOX, INPUT, CHECKBOX}
