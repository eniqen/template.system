package org.bitbucket.eniqen.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Mikhail Nemenko {@literal <nemenkoma@gmail.com>}
 */
@Entity
public class Template extends BaseNamedDictionaryEntry {

	@OneToMany(mappedBy = "template", cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderBy(value = "ordinal")
	private Set<TemplateField> templateFields = new HashSet<>();

	public Template() {
	}

	public Template(String name, String description) {
		super(name, description);
	}

	public Template(String name, String description, Set<TemplateField> templateFields) {
		this(description, name);
		this.templateFields = templateFields;
	}

	public Set<TemplateField> getFields() {
		return templateFields;
	}

	public void setFields(Set<TemplateField> fields) {
		this.templateFields = fields;
	}
}
