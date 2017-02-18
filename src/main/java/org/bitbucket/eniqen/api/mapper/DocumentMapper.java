package org.bitbucket.eniqen.api.mapper;

import org.bitbucket.eniqen.api.dto.DocumentDTO;
import org.bitbucket.eniqen.api.dto.FieldDTO;
import org.bitbucket.eniqen.domain.Document;
import org.bitbucket.eniqen.domain.TemplateField;
import org.mapstruct.IterableMapping;
import org.mapstruct.MapMapping;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.Map;

/**
 * @author Mikhail Nemenko {@literal <nemenkoma@gmail.com>}
 */
@Mapper(uses = {TemplateMapper.class, FieldMapper.class})
public interface DocumentMapper {

	DocumentMapper INSTANCE = Mappers.getMapper(DocumentMapper.class);

	DocumentDTO toDto(Document document);

	Document toEntity(DocumentDTO documentDTO);

	List<DocumentDTO> toDtoList(List<Document> documents);
}
