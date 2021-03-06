package org.bitbucket.eniqen.api;

import org.bitbucket.eniqen.api.dto.CollectionDTO;
import org.bitbucket.eniqen.api.dto.DocumentDTO;
import org.bitbucket.eniqen.api.dto.FieldDTO;
import org.bitbucket.eniqen.api.dto.TemplateDTO;
import org.bitbucket.eniqen.api.mapper.DocumentMapper;
import org.bitbucket.eniqen.api.mapper.FieldMapper;
import org.bitbucket.eniqen.api.mapper.TemplateMapper;
import org.bitbucket.eniqen.domain.Document;
import org.bitbucket.eniqen.domain.Field;
import org.bitbucket.eniqen.domain.Template;
import org.bitbucket.eniqen.domain.TemplateField;
import org.bitbucket.eniqen.service.document.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

/**
 * @author Mikhail Nemenko {@literal <nemenkoma@gmail.com>}
 */
@RestController
@RequestMapping(DocumentController.URL)
public class DocumentController {

	static final String URL = "/documents";
	private final DocumentService documentService;

	@Autowired
	public DocumentController(DocumentService documentService) {
		this.documentService = documentService;
	}

	/**
	 * Апи для создания документа
	 *
	 * @param documentDTO трансфер документа
	 * @return созданный объект документа
	 */
	@PostMapping(value = "/create",
				 produces = APPLICATION_JSON_VALUE,
				 consumes = APPLICATION_JSON_VALUE)
	public HttpEntity<DocumentDTO> create(@RequestBody DocumentDTO documentDTO) {

		final TemplateDTO templateDTO = documentDTO.getTemplate();
		final Map<TemplateField, String> templateFields = templateDTO.getFields()
																	 .stream()
																	 .collect(toMap(FieldMapper.INSTANCE::toTemplateField,
																					FieldDTO::getValue));
		templateFields.keySet().forEach(templateField -> {
			final Template template = TemplateMapper.INSTANCE.toEntity(templateDTO);
			templateField.setTemplate(template);
		});

		final Document document = this.documentService.create(documentDTO.getName(),
															  documentDTO.getDescription(),
															  templateFields);


		final List<FieldDTO> fields = document.getFieldValues()
											  .entrySet()
											  .stream()
											  .map(entry -> {
												  final TemplateField key = entry.getKey();
												  final Field field = key.getField();
												  return new FieldDTO(field.getId(),
																	  field.getType(),
																	  field.getName(),
																	  field.getDescription(),
																	  entry.getValue(),
																	  key.getOrdinal());
											  }).collect(toList());

		final Optional<TemplateDTO> savedTemplateDTO = document.getFieldValues().keySet()
															   .stream()
															   .findFirst()
															   .map(templateField -> new TemplateDTO(templateField.getTemplate().getId(),
																									 templateField.getTemplate().getName(),
																									 templateField.getTemplate().getDescription(),
																									 fields));

		DocumentDTO savedDocumentDTO = new DocumentDTO(document.getId(),
													   savedTemplateDTO.orElse(null),
													   document.getName(),
													   document.getDescription());
		return ok(savedDocumentDTO);
	}

	/**
	 * Апи получения документа по идентификатору
	 *
	 * @param id идентификатор документа
	 * @return найденый
	 */
	@GetMapping(value = "/{id}")
	public HttpEntity<DocumentDTO> getById(@PathVariable("id") String id) {
		return documentService.find(id)
							  .map(document -> ok(DocumentMapper.INSTANCE.toDto(document)))
							  .orElse(notFound().build());
	}

	/**
	 * Апи получения постраничного списка документов
	 *
	 * @param pageSize размер постраничной выдачи
	 * @param pageNum  номер страницы
	 * @return список документов
	 */
	@GetMapping(value = "/list")
	public HttpEntity<CollectionDTO<DocumentDTO>> getAll(@RequestParam("pageSize") int pageSize,
														 @RequestParam("pageNum") int pageNum) {

		Page<Document> documents = documentService.findAll(pageSize, pageNum);

		return ok(new CollectionDTO<>(DocumentMapper.INSTANCE.toDtoList(documents.getContent()),
									  documents.getTotalElements()));
	}

	/**
	 * Апи обновления документа
	 *
	 * @param id          идентификатор документа
	 * @param documentDTO трансфер документа
	 * @return обновленный результат
	 */
	@PutMapping(value = "/{id}/update",
				consumes = APPLICATION_JSON_VALUE,
				produces = APPLICATION_JSON_VALUE)
	public HttpEntity<DocumentDTO> update(@PathVariable("id") String id,
										  @RequestBody DocumentDTO documentDTO) {
		Document updateDocument = this.documentService.update(id,
															  documentDTO.getName(),
															  documentDTO.getDescription(),
															  null);
		return ok(DocumentMapper.INSTANCE.toDto(updateDocument));
	}

	/**
	 * Апи удаления документа по его идентификатору
	 *
	 * @param id идентификатор доукмента
	 * @return статус ОК
	 */
	@DeleteMapping(value = "/{id}/delete")
	public HttpEntity delete(@PathVariable("id") String id) {
		this.documentService.delete(id);
		return ok().build();
	}
}
