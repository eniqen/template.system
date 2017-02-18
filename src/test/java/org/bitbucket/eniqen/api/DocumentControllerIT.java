package org.bitbucket.eniqen.api;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import org.bitbucket.eniqen.api.dto.DocumentDTO;
import org.bitbucket.eniqen.api.dto.FieldDTO;
import org.bitbucket.eniqen.api.dto.TemplateDTO;
import org.bitbucket.eniqen.domain.FieldType;
import org.bitbucket.eniqen.repository.DocumentRepositoryTest;
import org.bitbucket.eniqen.repository.FieldRepositoryTest;
import org.bitbucket.eniqen.repository.TemplateRepositoryTest;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;
import static org.bitbucket.eniqen.api.DocumentController.URL;
import static org.hamcrest.Matchers.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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


	private static final String DOCUMENT_1_ID = "9bf48488-f18b-4ad1-9bf1-008417c2e413";
	private static final String TEMPLATE_1_ID = "d77a4774-c25e-4844-bddd-151354d6d8bc";
	private static final String TEMPLATE_1_NAME = "TEMPLATE-1";
	private static final String TEMPLATE_1_DESCRIPTION = "DESCRIPTION-1";

	private static final String FIELD_1_ID = "9aa16d3f-7997-4111-9293-4936b2e43de5";
	private static final FieldType FIELD_1_TYPE = FieldType.CHECKBOX;
	private static final String FIELD_1_NAME = "Поле-1";
	private static final String FIELD_1_DESCRIPTION = "Тестовое поле-1";
	private static final Integer FIELD_1_ORDER = 0;

	private static final String FIELD_2_ID = "ad0af9b0-34b2-4616-96ec-38ab295e13db";
	private static final FieldType FIELD_2_TYPE = FieldType.INPUT;
	private static final String FIELD_2_NAME = "Поле-2";
	private static final String FIELD_2_DESCRIPTION = "Тестовое поле-2";
	private static final Integer FIELD_2_ORDER = 1;

	private static final String FIELD_3_ID = "1fc35485-c78f-4c4c-9a0b-a4e37831611b";
	private static final FieldType FIELD_3_TYPE = FieldType.TEXTAREA;
	private static final String FIELD_3_NAME = "Поле-3";
	private static final String FIELD_3_DESCRIPTION = "Тестовое поле-3";
	private static final Integer FIELD_3_ORDER = 2;

	private static final String DOCUMENT_1_NAME = "DOCUMENT-1";
	private static final String DOCUMENT_1_DESCRIPTION = "DOCUMENT_DESCRIPTION-1";
	private static final int DOCUMENT_1_VERSION = 0;

	private static final String FIELD_1_VALUE = "Сидор";
	private static final String FIELD_2_VALUE = "Зидан";
	private static final String FIELD_3_VALUE = "Вася";

	@Test
	public void testGetAllWithPaging() throws Exception {
		mockMvc.perform(get(format("%s/list", URL))
								.contentType(APPLICATION_JSON)
								.param("pageSize", String.valueOf(5))
								.param("pageNum", String.valueOf(0)))
			   .andExpect(status().isOk())
			   .andExpect(jsonPath("$.count", is(3)));
	}

	@Test
	public void testGetOneById() throws Exception {
		mockMvc.perform(get(format("%s/{id}", URL), DOCUMENT_1_ID)
								.contentType(APPLICATION_JSON))
			   .andExpect(status().isOk());
	}

	@Test
	public void testDeleteById() throws Exception {
		mockMvc.perform(delete(format("%s/{id}/delete", URL), DOCUMENT_1_ID)
								.contentType(APPLICATION_JSON))
			   .andExpect(status().isOk());
	}

	@Test
	public void testCreate() throws Exception {
		FieldDTO fieldDTO1 = new FieldDTO(FIELD_1_ID, FIELD_1_TYPE, FIELD_1_NAME, FIELD_1_DESCRIPTION, FIELD_1_VALUE, FIELD_1_ORDER);
		FieldDTO fieldDTO2 = new FieldDTO(FIELD_2_ID, FIELD_2_TYPE, FIELD_2_NAME, FIELD_2_DESCRIPTION, FIELD_2_VALUE, FIELD_2_ORDER);
		FieldDTO fieldDTO3 = new FieldDTO(FIELD_3_ID, FIELD_3_TYPE, FIELD_3_NAME, FIELD_3_DESCRIPTION, FIELD_3_VALUE, FIELD_3_ORDER);

		List<FieldDTO> fields = Stream.of(fieldDTO1, fieldDTO2, fieldDTO3)
									  .sorted(Comparator.comparingInt(FieldDTO::getOrder))
									  .collect(toList());

		TemplateDTO templateDTO = new TemplateDTO(TEMPLATE_1_ID, TEMPLATE_1_NAME, TEMPLATE_1_DESCRIPTION, fields);

		DocumentDTO documentDTO = new DocumentDTO(null, templateDTO, DOCUMENT_1_NAME, DOCUMENT_1_DESCRIPTION);
		final String stringDocument = jsonMapper.writeValueAsString(documentDTO);

		mockMvc.perform(post(format("%s/create", URL))
								.contentType(APPLICATION_JSON)
								.content(stringDocument))
			   .andExpect(status().isOk());
	}

	@Test
	public void testUpdate() throws Exception {

	}
}
