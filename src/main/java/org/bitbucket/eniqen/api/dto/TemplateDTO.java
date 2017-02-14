package org.bitbucket.eniqen.api.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Singular;

import java.util.List;

/**
 * @author Mikhail Nemenko {@literal <nemenkoma@gmail.com>}
 */
@Data
@Builder
@NoArgsConstructor
public class TemplateDTO {
	private String id;
	private String name;
	private String description;
	@Singular private List<FieldDTO> fields;
}
