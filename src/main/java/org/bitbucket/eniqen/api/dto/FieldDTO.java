package org.bitbucket.eniqen.api.dto;

import org.bitbucket.eniqen.domain.FieldType;

/**
 * @author Mikhail Nemenko {@literal <nemenkoma@gmail.com>}
 */
public class FieldDTO {
	private String id;
	private FieldType type;
	private String name;
	private String description;
	private String value;
	private Integer order;

	public FieldDTO() {
	}

	public FieldDTO(String id, FieldType type, String name, String description, String value, Integer order) {
		this.id = id;
		this.type = type;
		this.name = name;
		this.description = description;
		this.value = value;
		this.order = order;
	}

	public String getId() {
		return id;
	}

	public FieldType getType() {
		return type;
	}

	public String getName() {
		return name;
	}

	public String getDescription() {
		return description;
	}

	public String getValue() {
		return value;
	}

	public Integer getOrder() {
		return order;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setType(FieldType type) {
		this.type = type;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public void setOrder(Integer order) {
		this.order = order;
	}
}
