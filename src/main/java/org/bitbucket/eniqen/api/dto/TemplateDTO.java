package org.bitbucket.eniqen.api.dto;

import java.util.List;

/**
 * @author Mikhail Nemenko {@literal <nemenkoma@gmail.com>}
 */
public class TemplateDTO {
	private String id;
	private String name;
	private String description;
	private List<FieldDTO> fields;

	public TemplateDTO() {
	}

	public TemplateDTO(String id, String name, String description, List<FieldDTO> fields) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.fields = fields;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<FieldDTO> getFields() {
		return fields;
	}

	public void setFields(List<FieldDTO> fields) {
		this.fields = fields;
	}
}
