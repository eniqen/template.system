package org.bitbucket.eniqen.repository;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import org.bitbucket.eniqen.domain.Field;
import org.bitbucket.eniqen.domain.FieldType;
import org.bitbucket.eniqen.domain.repository.FieldRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Интеграционные тесты репозитория Полей
 *
 * @author Mikhail Nemenko {@literal <nemenkoma@gmail.com>}
 */
@DatabaseSetup(FieldRepositoryTest.DATASET)
@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = FieldRepositoryTest.DATASET)
public class FieldRepositoryTest extends AbstractRepositoryTest {

	public static final String DATASET = "classpath:datasets/field-table.xml";

	private static final int PAGE_SIZE = 3;
	private static final String FIELD_1_ID = "9aa16d3f-7997-4111-9293-4936b2e43de5";
	private static final FieldType FIELD_1_TYPE = FieldType.CHECKBOX;
	private static final String FIELD_1_NAME = "Поле-1";
	private static final String FIELD_1_DESCRIPTION = "Тестовое поле-1";

	private static final String FIELD_NOT_EXIST_ID = UUID.randomUUID().toString();
	private static final FieldType FIELD_TYPE = FieldType.CHECKBOX;
	private static final String FIELD_NAME = "FIELD_NAME_TEST";
	private static final String FIELD_DESCRIPTION = "FIELD_NAME_DESCRIPTION";

	@Autowired
	private FieldRepository fieldRepository;

	/**
	 * Поиск по существующему идентификатору в базе
	 */
	@Test
	public void findOneWhenExisting() {
		Optional<Field> field = fieldRepository.findOne(FIELD_1_ID);

		assertThat(field.isPresent(), equalTo(true));

		Field actual = field.get();

		assertThat(actual.getId(), equalTo(FIELD_1_ID));
		assertThat(actual.getType(), equalTo(FIELD_1_TYPE));
		assertThat(actual.getName(), equalTo(FIELD_1_NAME));
		assertThat(actual.getDescription(), equalTo(FIELD_1_DESCRIPTION));
	}

	/**
	 * Поиск по несуществующему идентификатору
	 */
	@Test
	public void findOneWhenNotExisting() {
		Optional<Field> field = fieldRepository.findOne(FIELD_NOT_EXIST_ID);
		assertThat(field.isPresent(), equalTo(false));
	}

	/**
	 * Проверка пейджинации
	 */
	@Test
	public void findAllWithPaging() {
		Page<Field> fields = fieldRepository.findAll(new PageRequest(0, PAGE_SIZE));

		assertThat(fields, is(not(nullValue())));
		assertThat(fields.getTotalElements(), equalTo(3L));
		assertThat(fields.getTotalPages(), equalTo(1));
		assertThat(fields.getContent(), hasSize(PAGE_SIZE));
	}

	/**
	 * Проверяем создание нового поля
	 */
	@Test
	public void saveNew() {
		final Field field = new Field(FIELD_TYPE, FIELD_NAME, FIELD_DESCRIPTION);
		final Field savedField = fieldRepository.save(field);
		final List<Field> fields = fieldRepository.findAll();

		assertThat(savedField.getId(), is(not(nullValue())));
		assertThat(savedField.getType(), equalTo(field.getType()));
		assertThat(savedField.getDescription(), equalTo(field.getDescription()));
		assertThat(savedField.getName(), equalTo(field.getName()));
		assertThat(fields, hasSize(4));
	}

	/**
	 * Проверяем удаление по идентификатору
	 */
	@Test
	public void deleteById() {
		final List<Field> all = fieldRepository.findAll();
		assertThat(all, hasSize(3));

		fieldRepository.delete(FIELD_1_ID);

		final List<Field> afterDeleteField = fieldRepository.findAll();
		assertThat(afterDeleteField, hasSize(2));
		assertThat(afterDeleteField, hasItem(hasProperty("id", not(equalTo(FIELD_1_ID)))));
	}
}

