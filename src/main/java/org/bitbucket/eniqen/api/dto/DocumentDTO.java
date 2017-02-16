package org.bitbucket.eniqen.api.dto;

/**
 * @author Mikhail Nemenko {@literal <nemenkoma@gmail.com>}
 */
public class DocumentDTO {
	private String id;
	private TemplateDTO templateDTO;
	private String name;
	private String description;

	public DocumentDTO() {
	}

	public DocumentDTO(String id, TemplateDTO templateDTO, String name, String description) {
		this.id = id;
		this.templateDTO = templateDTO;
		this.name = name;
		this.description = description;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public TemplateDTO getTemplateDTO() {
		return templateDTO;
	}

	public void setTemplateDTO(TemplateDTO templateDTO) {
		this.templateDTO = templateDTO;
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
}
