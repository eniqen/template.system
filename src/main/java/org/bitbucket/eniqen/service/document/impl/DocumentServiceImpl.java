package org.bitbucket.eniqen.service.document.impl;

import org.bitbucket.eniqen.domain.repository.DocumentRepository;
import org.bitbucket.eniqen.domain.repository.TemplateRepository;
import org.bitbucket.eniqen.service.document.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

	@Autowired
	public DocumentServiceImpl(DocumentRepository documentRepository,
							   TemplateRepository templateRepository) {
		this.documentRepository = documentRepository;
		this.templateRepository = templateRepository;
	}
}
