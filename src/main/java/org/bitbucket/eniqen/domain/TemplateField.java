package org.bitbucket.eniqen.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Mikhail Nemenko {@literal <nemenkoma@gmail.com>}
 */
@Entity
@Table(name = "templates_fields")
public class TemplateField implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

//	@Id
	@ManyToOne(optional = false)
	@JoinColumn(name = "field_id")
	private Field field;

//	@Id
	@ManyToOne(optional = false)
	@JoinColumn(name = "template_id")
	private Template template;

	private int ordinal;

	public TemplateField(int ordinal, Field field, Template template) {
		this.ordinal = ordinal;
		this.field = field;
		this.template = template;
	}

	public TemplateField() {
	}

	public int getOrdinal() {
		return ordinal;
	}

	public void setOrdinal(int ordinal) {
		this.ordinal = ordinal;
	}

	public Field getField() {
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}

	public Template getTemplate() {
		return template;
	}

	public void setTemplate(Template template) {
		this.template = template;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
