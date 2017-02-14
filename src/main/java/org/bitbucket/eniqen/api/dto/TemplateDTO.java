package org.bitbucket.eniqen.api.dto;

import lombok.*;

import java.util.List;

/**
 * @author Mikhail Nemenko {@literal <nemenkoma@gmail.com>}
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TemplateDTO {
	private String id;
	private String name;
	private String description;
	@Singular private List<FieldDTO> fields;
}
