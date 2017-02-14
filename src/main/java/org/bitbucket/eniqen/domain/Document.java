package org.bitbucket.eniqen.domain;

import lombok.Builder;
import lombok.Singular;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Mikhail Nemenko {@literal <nemenkoma@gmail.com>}
 */
@Entity
public class Document extends BaseNamedDictionaryEntry {


    public Document() {
        super();
    }

    public Document(String name, String description) {
        super(name, description);
    }

    @Builder
    public Document(String name, String description, Map<TemplateField, String> templateFields) {
        super(name, description);
        this.templateFields = templateFields;
    }

    @Singular
    @ElementCollection(fetch = FetchType.EAGER)
    @MapKeyColumn(name = "id")
    @Cascade(value = CascadeType.ALL)
    private Map<TemplateField, String> templateFields = new HashMap<>();
//
//    @ManyToOne(cascade = javax.persistence.CascadeType.ALL, optional = false)
//    @JoinColumn(name = "TEMPLATE_ID")
//    private Template template;

    public Map<TemplateField, String> getFieldValues() {
        return templateFields;
    }

    public void setFieldValues(Map<TemplateField, String> templateFields) {
        this.templateFields = templateFields;
    }

//    public Template getTemplate() {
//        return template;
//    }
//
//    public void setTemplate(Template template) {
//        this.template = template;
//    }
}

