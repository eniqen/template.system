package org.bitbucket.eniqen.repository;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import org.bitbucket.eniqen.domain.Document;
import org.bitbucket.eniqen.domain.Template;
import org.bitbucket.eniqen.domain.TemplateField;
import org.bitbucket.eniqen.domain.repository.DocumentRepository;
import org.bitbucket.eniqen.domain.repository.TemplateRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.util.Pair;

import java.util.*;
import java.util.stream.Collectors;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Интеграционные тесты репозитория документов
 *
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
public class DocumentRepositoryTest extends AbstractRepositoryTest {

	public static final String DATASET = "classpath:datasets/document-table.xml";
	public static final String DOCUMENT_TEMPLATE_FIELD_DATASET = "classpath:datasets/document-template-field-table.xml";

	private static final String DOCUMENT_NOT_EXIST_ID = UUID.randomUUID().toString();
	private static final String DOCUMENT_1_ID = "9bf48488-f18b-4ad1-9bf1-008417c2e413";
	private static final String DOCUMENT_1_NAME = "DOCUMENT-1";
	private static final String DOCUMENT_1_DESCRIPTION = "DOCUMENT_DESCRIPTION-1";
	private static final int DOCUMENT_1_VERSION = 0;

	private static final String TEMPLATE_1_ID = "d77a4774-c25e-4844-bddd-151354d6d8bc";

	private static final String FIELD_1_ID = "9aa16d3f-7997-4111-9293-4936b2e43de5";
	private static final String FIELD_2_ID = "ad0af9b0-34b2-4616-96ec-38ab295e13db";
	private static final String FIELD_3_ID = "1fc35485-c78f-4c4c-9a0b-a4e37831611b";

	private static final String FIELD_1_VALUE = "Сидор";
	private static final String FIELD_2_VALUE = "Зидан";
	private static final String FIELD_3_VALUE = "Вася";

	private static final String DOCUMENT_FIELD_1_VALUE = "DOCUMENT_FIELD_1_VALUE";
	private static final String DOCUMENT_FIELD_2_VALUE = "DOCUMENT_FIELD_2_VALUE";
	private static final String DOCUMENT_FIELD_3_VALUE = "DOCUMENT_FIELD_3_VALUE";

	private static final Set<String> DOCUMENT_FIELD_VALUES = ImmutableSet.of(DOCUMENT_FIELD_1_VALUE,
																			 DOCUMENT_FIELD_2_VALUE,
																			 DOCUMENT_FIELD_3_VALUE);

	private static final Set<Pair<String, String>> FIELDS = ImmutableSet.of(Pair.of(FIELD_1_ID, FIELD_1_VALUE),
																			Pair.of(FIELD_2_ID, FIELD_2_VALUE),
																			Pair.of(FIELD_3_ID, FIELD_3_VALUE));

	@Autowired
	private DocumentRepository documentRepository;

	@Autowired
	private TemplateRepository templateRepository;

	/**
	 * Поиск всех документов с постраничным выводом
	 */
	@Test
	public void findAllWithPaging() {
		Page<Document> pagingResult = documentRepository.findAll(new PageRequest(0, 3));
		assertThat(pagingResult, is(notNullValue()));
		assertThat(pagingResult.getTotalElements(), equalTo(3L));
		assertThat(pagingResult.getTotalPages(), equalTo(1));
	}

	/**
	 * Поиск всех документов
	 */
	@Test
	public void findAll() {
		List<Document> documents = documentRepository.findAll();
		assertThat(documents, is(notNullValue()));
		assertThat(documents.size(), equalTo(3));
	}

	/**
	 * Проверяем что отсутствует документ с данным идентификатором
	 */
	@Test
	public void findWhenIdIsNotExist() {
		final Optional<Document> documentOptional = documentRepository.findOne(DOCUMENT_NOT_EXIST_ID);
		assertThat(documentOptional.isPresent(), equalTo(false));
	}

	/**
	 * Проверяем что в базе есть документ с нужным нам айдишником и в таблице Шаблоны-Поля есть связанные сущности
	 */
	@Test
	public void findWhenIdIsExist() {

		final Optional<Document> documentOptional = documentRepository.findOne(DOCUMENT_1_ID);
		assertThat(documentOptional.isPresent(), equalTo(true));

		final Document document = documentOptional.get();
		assertThat(document.getId(), equalTo(DOCUMENT_1_ID));
		assertThat(document.getName(), equalTo(DOCUMENT_1_NAME));
		assertThat(document.getDescription(), equalTo(DOCUMENT_1_DESCRIPTION));
		assertThat(document.getVersion(), equalTo(DOCUMENT_1_VERSION));

		final Map<TemplateField, String> fieldValues = document.getFieldValues();
		assertThat(fieldValues, is(notNullValue()));
		assertThat(fieldValues.size(), equalTo(3));

		fieldValues.entrySet().forEach(entry -> {
			assertThat(entry.getKey().getTemplate().getId(), equalTo(TEMPLATE_1_ID));
			assertThat(FIELDS.contains(Pair.of(entry.getKey().getField().getId(), entry.getValue())), equalTo(true));
		});
	}

	/**
	 * Проверяем создание нового документа
	 */
	@Test
	public void saveNew() {
		final Document document = new Document(DOCUMENT_1_NAME, DOCUMENT_1_DESCRIPTION);
		Document savedDocument = documentRepository.save(document);
		final List<Document> documents = documentRepository.findAll();

		assertThat(savedDocument.getId(), is(notNullValue()));
		assertThat(savedDocument.getDescription(), equalTo(document.getDescription()));
		assertThat(savedDocument.getName(), equalTo(document.getName()));
		assertThat(documents, hasSize(4));
		assertThat(savedDocument.getFieldValues().entrySet(), empty());

		final Optional<Template> template = templateRepository.findOne(TEMPLATE_1_ID);
		final List<TemplateField> fields = template.get().getFields()
												   .stream()
												   .collect(Collectors.toList());

		Map<TemplateField, String> templateFieldsValue = ImmutableMap.of(fields.get(0), DOCUMENT_FIELD_1_VALUE,
																		 fields.get(1), DOCUMENT_FIELD_2_VALUE,
																		 fields.get(2), DOCUMENT_FIELD_3_VALUE);
		savedDocument.setFieldValues(templateFieldsValue);

		savedDocument = documentRepository.save(savedDocument);
		assertThat(savedDocument.getFieldValues(), notNullValue());
		assertThat(savedDocument.getFieldValues().entrySet(), hasSize(3));

		savedDocument.getFieldValues()
					 .entrySet()
					 .forEach(entry -> {
						 assertThat(entry.getKey().getTemplate().getId(), equalTo(TEMPLATE_1_ID));
						 assertThat(DOCUMENT_FIELD_VALUES.contains(entry.getValue()), is(true));
					 });
	}

	/**
	 * Проверяем удаление документа
	 */
	@Test
	public void deleteById() {
		final List<Document> documents = documentRepository.findAll();
		assertThat(documents, hasSize(3));

		documentRepository.delete(DOCUMENT_1_ID);

		final List<Document> documentsAfterDelete = documentRepository.findAll();
		assertThat(documentsAfterDelete, hasSize(2));
		assertThat(documentsAfterDelete, hasItem(hasProperty("id", not(equalTo(DOCUMENT_1_ID)))));
	}
}
