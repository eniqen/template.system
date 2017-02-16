package org.bitbucket.eniqen.api.mapper;

import org.bitbucket.eniqen.api.dto.FieldDTO;
import org.bitbucket.eniqen.domain.Field;
import org.bitbucket.eniqen.domain.TemplateField;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Set;

/**
 * @author Mikhail Nemenko {@literal <nemenkoma@gmail.com>}
 */
@Mapper
public interface FieldMapper {

    FieldMapper INSTANCE = Mappers.getMapper(FieldMapper.class);

    Field toEntity(FieldDTO fieldDTO);

    FieldDTO toDto(Field field);

    @Mappings({@Mapping(target = "field.name", source = "name"),
               @Mapping(target = "field.description", source = "description"),
               @Mapping(target = "field.type", source = "type"),
               @Mapping(target = "ordinal", source = "order")})
    TemplateField toTemplateField(FieldDTO fieldDTO);

    @InheritInverseConfiguration
    FieldDTO templateFieldToFieldDto(TemplateField templateField);

    @IterableMapping(qualifiedByName = "toTemplateField")
    Set<TemplateField> toTemplateFields(List<FieldDTO> fields);

    @InheritInverseConfiguration
    List<FieldDTO> toFieldsDto(Set<TemplateField> fields);
}
