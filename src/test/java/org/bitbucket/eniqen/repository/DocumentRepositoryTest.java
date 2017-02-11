package org.bitbucket.eniqen.repository;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import org.bitbucket.eniqen.domain.Document;
import org.bitbucket.eniqen.domain.repository.DocumentRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * Интеграционные тесты репозитория документов
 *
 * @author Mikhail Nemenko {@literal <nemenkoma@gmail.com>}
 */
@DatabaseSetup(value = {TemplateRepositoryTest.DATASET, DocumentRepositoryTest.DATASET})
@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = DocumentRepositoryTest.DATASET)
public class DocumentRepositoryTest extends AbstractRepositoryTest {
	public static final String DATASET = "classpath:datasets/document-table.xml";

	@Autowired
	private DocumentRepository documentRepository;

	@Test
	public void findAllWithPaging() {
		Page<Document> pagingResult = documentRepository.findAll(new PageRequest(0, 3));
	}
}
