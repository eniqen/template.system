package org.bitbucket.eniqen.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Mikhail Nemenko {@literal <nemenkoma@gmail.com>}
 */
@Entity
public class Template extends BaseEntity {

	@Column(name = "description")
	private String description;

	@Column(name = "name")
	private String name;

	@OneToMany(mappedBy = "template", cascade = CascadeType.ALL, orphanRemoval = true)
	@OrderBy(value = "ordinal")
	private Set<TemplateField> templateFields = new HashSet<>();

	public Template() {
	}

	public Template(String description, String name) {
		this.description = description;
		this.name = name;
	}

	public Template(String description, String name, Set<TemplateField> templateFields) {
		this(description, name);
		this.templateFields = templateFields;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<TemplateField> getFields() {
		return templateFields;
	}

	public void setFields(Set<TemplateField> fields) {
		this.templateFields = fields;
	}
}
