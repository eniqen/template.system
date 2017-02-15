package org.bitbucket.eniqen.api.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Mikhail Nemenko {@literal <nemenkoma@gmail.com>}
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DocumentDTO {
	private String id;
	private TemplateDTO templateDTO;
	private String name;
	private String description;
}
