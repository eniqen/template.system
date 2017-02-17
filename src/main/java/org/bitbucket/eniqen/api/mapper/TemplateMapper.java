package org.bitbucket.eniqen.api.mapper;

import org.bitbucket.eniqen.api.dto.TemplateDTO;
import org.bitbucket.eniqen.domain.Template;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
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

	@IterableMapping(qualifiedByName = "toDto")
	List<TemplateDTO> toListDto(List<Template> templates);
}
