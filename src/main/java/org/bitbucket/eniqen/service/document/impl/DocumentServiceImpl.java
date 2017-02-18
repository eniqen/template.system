package org.bitbucket.eniqen.service.document.impl;

import org.bitbucket.eniqen.common.error.DocumentError;
import org.bitbucket.eniqen.common.exception.EntityArgumentException;
import org.bitbucket.eniqen.common.exception.EntityNotFoundException;
import org.bitbucket.eniqen.domain.Document;
import org.bitbucket.eniqen.domain.Template;
import org.bitbucket.eniqen.domain.TemplateField;
import org.bitbucket.eniqen.domain.repository.DocumentRepository;
import org.bitbucket.eniqen.domain.repository.TemplateFieldRepository;
import org.bitbucket.eniqen.domain.repository.TemplateRepository;
import org.bitbucket.eniqen.service.document.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;
import static org.bitbucket.eniqen.common.Guard.CHECK_STRING;
import static org.bitbucket.eniqen.common.error.DocumentError.*;
import static org.springframework.transaction.annotation.Isolation.REPEATABLE_READ;

/**
 * Реализация сервиса для работы с Документами
 *
 * @author Mikhail Nemenko {@literal <nemenkoma@gmail.com>}
 */
@Service
@Transactional(readOnly = true)
public class DocumentServiceImpl implements DocumentService {

	private final DocumentRepository documentRepository;
	private final TemplateRepository templateRepository;
	private final TemplateFieldRepository templateFieldRepository;

	@Autowired
	public DocumentServiceImpl(DocumentRepository documentRepository,
							   TemplateRepository templateRepository,
							   TemplateFieldRepository templateFieldRepository) {
		this.documentRepository = documentRepository;
		this.templateRepository = templateRepository;
		this.templateFieldRepository = templateFieldRepository;
	}

	@Override
	public Optional<Document> find(String id) {
		return documentRepository.findOne(id);
	}

	@Override
	public Page<Document> findAll(int pageSize, int pageNo) {
		return documentRepository.findAll(new PageRequest(pageNo, pageSize));
	}

	@Override
	public Collection<Document> findAll() {
		return documentRepository.findAll();
	}

	@Override
	@Transactional(isolation = REPEATABLE_READ)
	public Document update(String id,
						   String name,
						   String description,
						   Map<TemplateField, String> templateFields) {

		CHECK_STRING.check(id, ID_REQUIRED);
		Document document = documentRepository.findOne(id)
											  .orElseThrow(() -> new EntityNotFoundException(NOT_EXIST));

		CHECK_STRING.check(name, NAME_REQUIRED);
		document.setName(name);
		document.setDescription(name);

		validateTemplateLinks(templateFields.keySet().stream());
		validateFieldIds(templateFields.keySet().stream());

		document.setFieldValues(templateFields);

		return documentRepository.save(document);
	}

	@Override
	@Transactional(isolation = REPEATABLE_READ)
	public Document create(String name,
						   String description,
						   Map<TemplateField, String> templateFields) {

		CHECK_STRING.check(name, NAME_REQUIRED);

		final Map<TemplateField, String> fields = templateFields.entrySet()
																.stream()
																.collect(toMap(o -> templateFieldRepository.findByTemplateIdAndFieldId(o.getKey().getTemplate().getId(),
																																	   o.getKey().getField().getId())
																										   .orElseThrow(() -> new EntityNotFoundException(DocumentError.NOT_EXIST)),
																			   Map.Entry::getValue));

		validateTemplateLinks(fields.keySet().stream());
		validateFieldIds(fields.keySet().stream());

		return documentRepository.save(new Document(name, description, fields));
	}

	@Override
	@Transactional(isolation = REPEATABLE_READ)
	public void delete(String id) {

		CHECK_STRING.check(id, ID_REQUIRED);

		Document document = documentRepository.findOne(id)
											  .orElseThrow(() -> new EntityNotFoundException(NOT_EXIST));

		documentRepository.delete(document.getId());
	}

	/**
	 * Вспомогательный метод для валидации связки Шаблон-Документ-Поле
	 *
	 * @param templateFieldStream поток шаблона с полем
	 */
	private void validateTemplateLinks(Stream<TemplateField> templateFieldStream) {

		long templateIdCount = templateFieldStream.map(TemplateField::getTemplate)
												  .map(Template::getId)
												  .distinct()
												  .count();

		if (templateIdCount != 1) throw new EntityArgumentException(INVALID_LINK_TEMPLATE);
	}

	/**
	 * Всопомогательный метод проверяющий что все Поля содержут идентификаторы
	 *
	 * @param templateFieldStream поток шаблона с полем
	 */
	private void validateFieldIds(Stream<TemplateField> templateFieldStream) {

		boolean isFieldIdValid = templateFieldStream.map(TemplateField::getField)
													.allMatch(field -> field.getId() != null && !field.getId().trim().isEmpty());

		if (!isFieldIdValid) throw new EntityArgumentException(TEMPLATE_NOT_EXIST_FIELDS);
	}
}
