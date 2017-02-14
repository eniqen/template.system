package org.bitbucket.eniqen.service.template.impl;

import lombok.experimental.var;
import org.bitbucket.eniqen.common.Guard;
import org.bitbucket.eniqen.common.exception.EntityNotFoundException;
import org.bitbucket.eniqen.domain.FieldType;
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
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import static java.util.Collections.emptySet;
import static java.util.stream.Collectors.toSet;
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
						   Optional<Set<TemplateField>> templateFields) {

		Guard.CHECK_STRING.check(name, NAME_REQUIRED);
		templateFields.ifPresent(this::validateFields);

		var savedTemplate = templateRepository.save(Template.builder()
															.name(name)
															.description(description)
															.build());

		templateFields.ifPresent(tf -> tf.forEach(templateField -> {
			templateField.setTemplate(savedTemplate);
		}));

		return templateRepository.save(savedTemplate);
	}

	@Override
	@Transactional(isolation = REPEATABLE_READ)
	public Template update(String id,
						   String name,
						   String description,
						   Optional<Set<TemplateField>> templateFields) {

		Guard.CHECK_STRING.check(id, ID_REQUIRED);

		var template = this.find(id)
						   .orElseThrow(() -> new EntityNotFoundException(NOT_EXIST));

		templateFields.ifPresent(this::validateFields);
		templateFields.ifPresent(tf -> tf.forEach(templateField -> {
			templateField.setTemplate(template);
		}));

		return templateRepository.save(Template.builder()
											   .name(name)
											   .description(description)
											   .templateFields(templateFields.orElse(emptySet()))
											   .build());
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

		Guard.CHECK_STRING.check(id, ID_REQUIRED);

		var template = this.find(id)
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

			var fieldTypesCount = templateFields.stream()
												.map(templateField -> templateField.getField().getType())
												.distinct()
												.count();

			if (fieldTypesCount < 3) {
				throw new IllegalArgumentException("Не верное колличество обязательных типов полей");
			}

			var ordinalSet = templateFields.stream()
										   .map(TemplateField::getOrdinal)
										   .collect(toSet());

			if (ordinalSet.size() < templateFields.size()) {
				throw new IllegalArgumentException("Неверный индекс полей в шаблоне");
			}
		}
	}
}
