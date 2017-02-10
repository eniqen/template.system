package org.bitbucket.eniqen.repositories;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import org.bitbucket.eniqen.models.Template;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

/**
 * Интеграционные тесты репозитоий шаблонов
 *
 * @author Mikhail Nemenko {@literal <nemenkoma@gmail.com>}
 */
@DatabaseSetup(TemplateRepositoryTest.DATASET)
@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = TemplateRepositoryTest.DATASET)
public class TemplateRepositoryTest extends AbstractRepositoryTest {
	public static final String DATASET = "classpath:datasets/template-table.xml";

	@Autowired
	private TemplateRepository templateRepository;

	@Test
	public void findAllWithPaging(){
		Page<Template> pagingResult = templateRepository.findAll(new PageRequest(0, 3));
	}
}
