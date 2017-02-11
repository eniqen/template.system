package org.bitbucket.eniqen.service.template.impl;

import org.bitbucket.eniqen.domain.repository.FieldRepository;
import org.bitbucket.eniqen.domain.repository.TemplateRepository;
import org.bitbucket.eniqen.service.template.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
