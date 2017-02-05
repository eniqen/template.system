package org.bitbucket.eniqen.models;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Mikhail Nemenko {@literal <nemenkoma@gmail.com>}
 */
@Entity
public class Document extends BaseEntity {

    private String name;

    @ElementCollection
    @MapKeyColumn(name = "template.templateFields.field.id")
    @Cascade(value = CascadeType.ALL)
    private Map<Field, String> fieldValues = new HashMap<>();

    @ManyToOne(cascade = javax.persistence.CascadeType.ALL)
    @JoinColumn(name = "TEMPLATE_ID")
    private Template template;

    public Map<Field, String> getFieldValues() {
        return fieldValues;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFieldValues(Map<Field, String> fieldValues) {
        this.fieldValues = fieldValues;
    }

    public Template getTemplate() {
        return template;
    }

    public void setTemplate(Template template) {
        this.template = template;
    }
}

