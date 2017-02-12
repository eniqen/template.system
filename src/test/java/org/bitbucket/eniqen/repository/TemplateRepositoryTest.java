package org.bitbucket.eniqen.repository;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.google.common.collect.ImmutableSet;
import org.bitbucket.eniqen.domain.Field;
import org.bitbucket.eniqen.domain.Template;
import org.bitbucket.eniqen.domain.TemplateField;
import org.bitbucket.eniqen.domain.repository.FieldRepository;
import org.bitbucket.eniqen.domain.repository.TemplateRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Интеграционные тесты репозитоий шаблонов
 *
 * @author Mikhail Nemenko {@literal <nemenkoma@gmail.com>}
 */
@DatabaseSetup(value = {FieldRepositoryTest.DATASET,
						TemplateRepositoryTest.DATASET,
						TemplateRepositoryTest.TEMPLATE_FIELD_DATASET})
@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL,
				  value = {FieldRepositoryTest.DATASET,
						   TemplateRepositoryTest.DATASET,
						   TemplateRepositoryTest.TEMPLATE_FIELD_DATASET})
public class TemplateRepositoryTest extends AbstractRepositoryTest {

	public static final String DATASET = "classpath:datasets/template-table.xml";
	public static final String TEMPLATE_FIELD_DATASET = "classpath:datasets/template-field-table.xml";

	private static final String TEMPLATE_1_ID = "d77a4774-c25e-4844-bddd-151354d6d8bc";
	private static final String TEMPLATE_1_NAME = "TEMPLATE-1";
	private static final String TEMPLATE_1_DESCRIPTION = "DESCRIPTION-1";
	private static final Integer TEMPLATE_1_VERSION = 0;

	private static final String FIELD_1_ID = "9aa16d3f-7997-4111-9293-4936b2e43de5";
	private static final String FIELD_2_ID = "ad0af9b0-34b2-4616-96ec-38ab295e13db";
	private static final String FIELD_3_ID = "1fc35485-c78f-4c4c-9a0b-a4e37831611b";
	private static final Set<String> FIELDS = ImmutableSet.of(FIELD_1_ID, FIELD_2_ID, FIELD_3_ID);

	private static final String TEMPLATE_NOT_EXIST_ID = UUID.randomUUID().toString();

	@Autowired
	private TemplateRepository templateRepository;

	@Autowired
	private FieldRepository fieldRepository;

	/**
	 * Проверяем получение пейдженированного запроса с базы по шаблонам
	 */
	@Test
	public void findAllWithPaging() {
		final Page<Template> pagingResult = templateRepository.findAll(new PageRequest(0, 3));
		assertThat(pagingResult, is(notNullValue()));
		assertThat(pagingResult.getTotalPages(), equalTo(1));
		assertThat(pagingResult.getContent(), hasSize(3));
	}

	/**
	 * Поиск всех
	 */
	@Test
	public void findAll() {
		final List<Template> result = templateRepository.findAll();
		assertThat(result, is(notNullValue()));
		assertThat(result, hasSize(3));
	}

	/**
	 * Проверяем что в базе есть шаблон с нужным нам айдишником и в таблице Шаблоны-Поля есть связанные поля
	 */
	@Test
	public void findWhenIdIsExist() {

		final Optional<Template> templateOptional = templateRepository.findOne(TEMPLATE_1_ID);
		assertThat(templateOptional.isPresent(), equalTo(true));

		final Template template = templateOptional.get();
		assertThat(template.getId(), equalTo(TEMPLATE_1_ID));
		assertThat(template.getName(), equalTo(TEMPLATE_1_NAME));
		assertThat(template.getDescription(), equalTo(TEMPLATE_1_DESCRIPTION));
		assertThat(template.getVersion(), equalTo(TEMPLATE_1_VERSION));

		final Set<TemplateField> fields = template.getFields();
		assertThat(fields, is(not(equalTo(null))));
		assertThat(fields, hasSize(3));

		fields.forEach(templateField -> {
			assertThat(templateField.getTemplate().getId(), equalTo(TEMPLATE_1_ID));
			assertThat(FIELDS.contains(templateField.getField().getId()), equalTo(true));
		});
	}

	/**
	 * Проверяем что отсутствует шаблон с данным идентификатором
	 */
	@Test
	public void findWhenIdIsNotExist() {

		final Optional<Template> templateOptional = templateRepository.findOne(TEMPLATE_NOT_EXIST_ID);

		assertThat(templateOptional.isPresent(), equalTo(false));
	}

	/**
	 * Сохраняем новый шаблон, и привязываем к нему поля в доп таблицу Шаблоны-Поля
	 */
	@Test
	public void saveNew() {
		final Template template = new Template(TEMPLATE_1_NAME, TEMPLATE_1_DESCRIPTION);
		Template savedTemplate = templateRepository.save(template);
		final List<Template> templates = templateRepository.findAll();

		assertThat(savedTemplate.getId(), is(not(nullValue())));
		assertThat(savedTemplate.getDescription(), equalTo(template.getDescription()));
		assertThat(savedTemplate.getName(), equalTo(template.getName()));
		assertThat(savedTemplate.getFields(), empty());
		assertThat(templates, hasSize(4));

		Optional<Field> field1 = fieldRepository.findOne(FIELD_1_ID);
		Optional<Field> field2 = fieldRepository.findOne(FIELD_2_ID);
		Optional<Field> field3 = fieldRepository.findOne(FIELD_3_ID);

		Set<TemplateField> templateFields = ImmutableSet.of(new TemplateField(1, field1.get(), savedTemplate),
															new TemplateField(2, field2.get(), savedTemplate),
															new TemplateField(3, field3.get(), savedTemplate));
		savedTemplate.setFields(templateFields);

		savedTemplate = templateRepository.save(savedTemplate);
		assertThat(savedTemplate.getFields(), hasSize(3));
	}

	/**
	 *
	 */
	@Test
	public void deleteById() {
		final List<Template> templates = templateRepository.findAll();
		assertThat(templates, hasSize(3));

		templateRepository.delete(TEMPLATE_1_ID);

		final List<Template> afterDeleteTemplates = templateRepository.findAll();
		assertThat(afterDeleteTemplates, hasSize(2));
		assertThat(afterDeleteTemplates, hasItem(hasProperty("id", not(equalTo(TEMPLATE_1_ID)))));
	}
}
