package org.bitbucket.eniqen.models;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;


/**
 * @author Mikhail Nemenko {@literal <nemenkoma@gmail.com>}
 */
@Entity
public class Field extends BaseNamedDictionaryEntry {

	@Enumerated(EnumType.STRING)
	private FieldType fieldType;

	public Field(FieldType type, String name, String description) {
		super(name, description);
		this.fieldType = type;
	}

	public Field() {
	}

	public FieldType getFieldType() {
		return fieldType;
	}

	public void setFieldType(FieldType fieldType) {
		this.fieldType = fieldType;
	}
}

