package org.bitbucket.eniqen.api.dto;

import lombok.Data;

/**
 * @author Mikhail Nemenko {@literal <nemenkoma@gmail.com>}
 */
@Data
public class FieldDTO {
	private String id;
	private String name;
	private String description;
	private String value;
}
