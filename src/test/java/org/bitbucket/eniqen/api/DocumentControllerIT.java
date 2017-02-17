package org.bitbucket.eniqen.api;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import org.bitbucket.eniqen.repository.DocumentRepositoryTest;
import org.bitbucket.eniqen.repository.FieldRepositoryTest;
import org.bitbucket.eniqen.repository.TemplateRepositoryTest;
import org.junit.Test;

/**
 * @author Mikhail Nemenko {@literal <nemenkoma@gmail.com>}
 */
@DatabaseSetup(value = {FieldRepositoryTest.DATASET,
						TemplateRepositoryTest.DATASET,
						TemplateRepositoryTest.TEMPLATE_FIELD_DATASET,
						DocumentRepositoryTest.DATASET,
						DocumentRepositoryTest.DOCUMENT_TEMPLATE_FIELD_DATASET})
@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL,
				  value = {FieldRepositoryTest.DATASET,
						   TemplateRepositoryTest.DATASET,
						   TemplateRepositoryTest.TEMPLATE_FIELD_DATASET,
						   DocumentRepositoryTest.DATASET,
						   DocumentRepositoryTest.DOCUMENT_TEMPLATE_FIELD_DATASET})
public class DocumentControllerIT extends AbstractIT {

	@Test
	public void init() {

	}
}
