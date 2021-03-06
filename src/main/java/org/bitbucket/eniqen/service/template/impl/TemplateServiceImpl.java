package org.bitbucket.eniqen.service.template.impl;

import org.bitbucket.eniqen.common.exception.EntityNotFoundException;
import org.bitbucket.eniqen.domain.Field;
import org.bitbucket.eniqen.domain.Template;
import org.bitbucket.eniqen.domain.TemplateField;
import org.bitbucket.eniqen.domain.repository.FieldRepository;
import org.bitbucket.eniqen.domain.repository.TemplateRepository;
import org.bitbucket.eniqen.service.template.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.toSet;
import static org.bitbucket.eniqen.common.Guard.CHECK_STRING;
import static org.bitbucket.eniqen.common.error.TemplateError.*;
import static org.springframework.transaction.annotation.Isolation.REPEATABLE_READ;

/**
 * Реализация сервиса для работы с шаблонами
 *
 * @author Mikhail Nemenko {@literal <nemenkoma@gmail.com>}
 */
@Service
@Transactional(readOnly = true)
public class TemplateServiceImpl implements TemplateService {

	private final TemplateRepository templateRepository;
	private final FieldRepository fieldRepository;

	@Autowired
	public TemplateServiceImpl(TemplateRepository templateRepository,
							   FieldRepository fieldRepository) {
		this.templateRepository = templateRepository;
		this.fieldRepository = fieldRepository;
	}

	@Override
	@Transactional(isolation = REPEATABLE_READ)
	public Template create(String name,
						   String description,
						   Set<TemplateField> templateFields) {

		CHECK_STRING.check(name, NAME_REQUIRED);
		validateFields(templateFields);

		Template savedTemplate = templateRepository.save(new Template(name, description));

        templateFields.forEach(templateField -> {
            final Field savedField = fieldRepository.save(templateField.getField());

            templateField.setTemplate(savedTemplate);
            templateField.setField(savedField);
        });
        savedTemplate.setFields(templateFields);

		return templateRepository.save(savedTemplate);
	}

	@Override
	@Transactional(isolation = REPEATABLE_READ)
	public Template update(String id,
						   String name,
						   String description,
						   Set<TemplateField> templateFields) {

		CHECK_STRING.check(id, ID_REQUIRED);

		Template template = this.find(id)
								.orElseThrow(() -> new EntityNotFoundException(NOT_EXIST));

		validateFields(templateFields);

		templateFields.forEach(templateField -> templateField.setTemplate(template));

		return templateRepository.save(new Template(name, description, templateFields));
	}

	@Override
	public Page<Template> findAll(int pageNum, int pageSize) {
		return templateRepository.findAll(new PageRequest(pageNum, pageSize));
	}

	@Override
	public Collection<Template> findAll() {
		return templateRepository.findAll();
	}

	@Override
	public Optional<Template> find(String id) {
		return templateRepository.findOne(id);
	}

	@Override
	@Transactional(isolation = REPEATABLE_READ)
	public void delete(String id) {

		CHECK_STRING.check(id, ID_REQUIRED);

		Template template = this.find(id)
								.orElseThrow(() -> new EntityNotFoundException(NOT_EXIST));

		templateRepository.delete(template.getId());
	}

	/**
	 * Вспомогательный метод валидации полей и порядка
	 *
	 * @param templateFields Список для связи шаблон - поле
	 */
	private void validateFields(Set<TemplateField> templateFields) {
		if (templateFields != null && !templateFields.isEmpty()) {

			long fieldTypesCount = templateFields.stream()
												 .map(templateField -> templateField.getField().getType())
												 .distinct()
												 .count();

			if (fieldTypesCount < 3) {
				throw new IllegalArgumentException("Не верное колличество обязательных типов полей");
			}

			Set<Integer> ordinalSet = templateFields.stream()
													.map(TemplateField::getOrdinal)
													.collect(toSet());

			if (ordinalSet.size() < templateFields.size()) {
				throw new IllegalArgumentException("Неверный индекс полей в шаблоне");
			}
		}
	}
}
