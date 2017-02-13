package org.bitbucket.eniqen.domain;

import lombok.Builder;
import lombok.Singular;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Mikhail Nemenko {@literal <nemenkoma@gmail.com>}
 */
@Entity
public class Template extends BaseNamedDictionaryEntry {

	@Singular
	@OneToMany(mappedBy = "template",
			cascade = CascadeType.ALL,
			orphanRemoval = true)
	@OrderBy(value = "ordinal")
	@Fetch(FetchMode.JOIN)
	private Set<TemplateField> templateFields = new HashSet<>();

	public Template() {
	}

	public Template(String name, String description) {
		super(name, description);
	}

	@Builder
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

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Template template = (Template) o;

		return templateFields != null ? templateFields.equals(template.templateFields) : template.templateFields == null;

	}

	@Override
	public int hashCode() {
		return templateFields != null ? templateFields.hashCode() : 0;
	}
}
