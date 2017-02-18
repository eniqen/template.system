package org.bitbucket.eniqen.api.mapper;

import org.bitbucket.eniqen.api.dto.TemplateDTO;
import org.bitbucket.eniqen.domain.Template;
import org.bitbucket.eniqen.domain.TemplateField;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * @author Mikhail Nemenko {@literal <nemenkoma@gmail.com>}
 */
@Mapper(uses = FieldMapper.class)
public interface TemplateMapper {

	TemplateMapper INSTANCE = Mappers.getMapper(TemplateMapper.class);

	TemplateDTO toDto(Template template);

	Template toEntity(TemplateDTO templateDTO);

	@Mappings({@Mapping(target = "template.id", source = "id"),
			   @Mapping(target = "template.name", source = "name"),
			   @Mapping(target = "template.description", source = "description")})
	TemplateField toTemplateField(TemplateDTO templateDTO);

	@IterableMapping(qualifiedByName = "toDto")
	List<TemplateDTO> toListDto(List<Template> templates);
}
