package org.bitbucket.eniqen.domain;

import lombok.Builder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;


/**
 * @author Mikhail Nemenko {@literal <nemenkoma@gmail.com>}
 */
@Entity
public class Field extends BaseNamedDictionaryEntry {

	@Enumerated(EnumType.STRING)
	private FieldType type;

	@Builder
	public Field(FieldType type, String name, String description) {
		super(name, description);
		this.type = type;
	}

	public Field() {
	}

	public FieldType getType() {
		return type;
	}

	public void setType(FieldType type) {
		this.type = type;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		Field field = (Field) o;

		return type == field.type;

	}

	@Override
	public int hashCode() {
		return type != null ? type.hashCode() : 0;
	}
}

