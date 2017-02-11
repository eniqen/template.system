package org.bitbucket.eniqen.domain;

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
}

