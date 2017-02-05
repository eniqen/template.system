package org.bitbucket.eniqen.models;

import javax.persistence.*;
import java.util.Set;


/**
 * @author Mikhail Nemenko {@literal <nemenkoma@gmail.com>}
 */
@Entity
public class Field extends BaseEntity {

    @Enumerated(EnumType.STRING )
    private FieldType fieldType;

    private String name;

    private String description;

    public Field(FieldType type, String description, String name) {
        this.fieldType = type;
        this.description = description;
        this.name = name;
    }

    public Field() {
    }

    public FieldType getFieldType() {
        return fieldType;
    }

    public void setFieldType(FieldType fieldType) {
        this.fieldType = fieldType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

