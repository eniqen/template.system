package org.bitbucket.eniqen.api.dto;

import lombok.Data;

/**
 * @author Mikhail Nemenko {@literal <nemenkoma@gmail.com>}
 */
@Data
public class DocumentDTO {
	private String id;
	private TemplateDTO templateDTO;
	private String name;
	private String description;
}
