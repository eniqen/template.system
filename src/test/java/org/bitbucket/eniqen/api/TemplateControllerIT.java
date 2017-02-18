package org.bitbucket.eniqen.api;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import org.bitbucket.eniqen.api.dto.FieldDTO;
import org.bitbucket.eniqen.api.dto.TemplateDTO;
import org.bitbucket.eniqen.domain.FieldType;
import org.bitbucket.eniqen.repository.FieldRepositoryTest;
import org.bitbucket.eniqen.repository.TemplateRepositoryTest;
import org.junit.Test;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;
import static org.bitbucket.eniqen.api.TemplateController.URL;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Mikhail Nemenko {@literal <nemenkoma@gmail.com>}
 */
@DatabaseSetup(value = {FieldRepositoryTest.DATASET,
						TemplateRepositoryTest.DATASET,
						TemplateRepositoryTest.TEMPLATE_FIELD_DATASET})
@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL,
				  value = {FieldRepositoryTest.DATASET,
						   TemplateRepositoryTest.DATASET,
						   TemplateRepositoryTest.TEMPLATE_FIELD_DATASET})
public class TemplateControllerIT extends AbstractIT {

	private static final String TEMPLATE_1_ID = "d77a4774-c25e-4844-bddd-151354d6d8bc";
	private static final String TEMPLATE_1_NAME = "TEMPLATE-1";
	private static final String TEMPLATE_1_DESCRIPTION = "DESCRIPTION-1";

	private static final String FIELD_1_ID = "9aa16d3f-7997-4111-9293-4936b2e43de5";
	private static final String FIELD_2_ID = "ad0af9b0-34b2-4616-96ec-38ab295e13db";
	private static final String FIELD_3_ID = "1fc35485-c78f-4c4c-9a0b-a4e37831611b";

	private static final FieldType FIELD_1_TYPE = FieldType.CHECKBOX;
	private static final String FIELD_1_NAME = "Поле-1";
	private static final String FIELD_1_DESCRIPTION = "Тестовое поле-1";
	private static final Integer FIELD_1_ORDER = 0;

	@Test
	public void testGetAllWithPaging() throws Exception {
		mockMvc.perform(get(format("%s/list", URL))
								.contentType(APPLICATION_JSON)
								.param("pageSize", String.valueOf(5))
								.param("pageNum", String.valueOf(0)))
			   .andExpect(status().isOk())
			   .andExpect(jsonPath("$.count", is(3)))
			   .andExpect(jsonPath("$.items[0].id", is(TEMPLATE_1_ID)))
			   .andExpect(jsonPath("$.items[0].name", is(TEMPLATE_1_NAME)))
			   .andExpect(jsonPath("$.items[0].description", is(TEMPLATE_1_DESCRIPTION)))
			   .andExpect(jsonPath("$.items[0].fields[0].id", is(FIELD_1_ID)))
			   .andExpect(jsonPath("$.items[0].fields[0].name", is(FIELD_1_NAME)))
			   .andExpect(jsonPath("$.items[0].fields[0].description", is(FIELD_1_DESCRIPTION)))
			   .andExpect(jsonPath("$.items[0].fields[0].order", is(FIELD_1_ORDER)))
			   .andExpect(jsonPath("$.items[0].fields[0].type", is(FIELD_1_TYPE.toString())))
			   .andExpect(jsonPath("$.items[0].fields[1].id", is(FIELD_2_ID)))
			   .andExpect(jsonPath("$.items[0].fields[2].id", is(FIELD_3_ID)));
	}

	@Test
	public void testGetOneById() throws Exception {
		mockMvc.perform(get(format("%s/{id}", URL), TEMPLATE_1_ID)
								.contentType(APPLICATION_JSON))
			   .andExpect(status().isOk())
			   .andExpect(jsonPath("$.id", is(TEMPLATE_1_ID)))
			   .andExpect(jsonPath("$.name", is(TEMPLATE_1_NAME)))
			   .andExpect(jsonPath("$.description", is(TEMPLATE_1_DESCRIPTION)))
			   .andExpect(jsonPath("$.fields", hasSize(3)))
			   .andExpect(jsonPath("$.fields[0].id", is(FIELD_1_ID)))
			   .andExpect(jsonPath("$.fields[0].name", is(FIELD_1_NAME)))
			   .andExpect(jsonPath("$.fields[0].description", is(FIELD_1_DESCRIPTION)))
			   .andExpect(jsonPath("$.fields[0].order", is(FIELD_1_ORDER)))
			   .andExpect(jsonPath("$.fields[0].type", is(FIELD_1_TYPE.toString())))
			   .andExpect(jsonPath("$.fields[1].id", is(FIELD_2_ID)))
			   .andExpect(jsonPath("$.fields[1].name", notNullValue()))
			   .andExpect(jsonPath("$.fields[1].description", notNullValue()))
			   .andExpect(jsonPath("$.fields[1].order", notNullValue()))
			   .andExpect(jsonPath("$.fields[1].type", notNullValue()))
			   .andExpect(jsonPath("$.fields[2].id", is(FIELD_3_ID)));
	}

	@Test
	public void testDeleteById() throws Exception {
		mockMvc.perform(delete(format("%s/{id}/delete", URL), TEMPLATE_1_ID)
				.contentType(APPLICATION_JSON))
				.andExpect(status().isOk());
	}

	@Test
	public void testCreate() throws Exception {
	    FieldDTO fieldDTO1 = new FieldDTO(null, FieldType.CHECKBOX, "field_name0", "field_description0", "field_value0", 0);
	    FieldDTO fieldDTO2 = new FieldDTO(null, FieldType.INPUT, "field_name1", "field_description1", "field_value1", 1);
	    FieldDTO fieldDTO3 = new FieldDTO(null, FieldType.TEXTAREA, "field_name2", "field_description2", "field_value2", 2);
		List<FieldDTO> fields = Stream.of(fieldDTO1, fieldDTO2, fieldDTO3)
				                      .sorted(Comparator.comparingInt(FieldDTO::getOrder))
									  .collect(toList());

        TemplateDTO templateDTO = new TemplateDTO(null, "NAME", "DESC", fields);

        final String templateDTOString = jsonMapper.writeValueAsString(templateDTO);

        mockMvc.perform(post(format("%s/create", URL))
				.contentType(APPLICATION_JSON)
				.content(templateDTOString))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id", notNullValue()))
				.andExpect(jsonPath("$.name", is(templateDTO.getName())))
				.andExpect(jsonPath("$.description", is(templateDTO.getDescription())))
				.andExpect(jsonPath("$.fields", hasSize(3)))
				.andExpect(jsonPath("$.fields[0].id", notNullValue()))
				.andExpect(jsonPath("$.fields[0].name", is(fieldDTO1.getName())))
				.andExpect(jsonPath("$.fields[0].description", is(fieldDTO1.getDescription())))
				.andExpect(jsonPath("$.fields[0].type", is(fieldDTO1.getType().toString())))
				.andExpect(jsonPath("$.fields[0].order", is(fieldDTO1.getOrder())))
				.andExpect(jsonPath("$.fields[1].id", notNullValue()))
                .andExpect(jsonPath("$.fields[1].name", is(fieldDTO2.getName())))
                .andExpect(jsonPath("$.fields[1].description", is(fieldDTO2.getDescription())))
                .andExpect(jsonPath("$.fields[1].type", is(fieldDTO2.getType().toString())))
                .andExpect(jsonPath("$.fields[1].order", is(fieldDTO2.getOrder())))
                .andExpect(jsonPath("$.fields[2].id", notNullValue()))
                .andExpect(jsonPath("$.fields[2].name", is(fieldDTO3.getName())))
                .andExpect(jsonPath("$.fields[2].description", is(fieldDTO3.getDescription())))
                .andExpect(jsonPath("$.fields[2].type", is(fieldDTO3.getType().toString())))
                .andExpect(jsonPath("$.fields[2].order", is(fieldDTO3.getOrder())));
	}

	@Test
	public void testUpdate() throws Exception {
//        final String templateDTO = jsonMapper.writeValueAsString(new TemplateDTO());
//        mockMvc.perform(put(format("%s/{id}/update", URL))
//				.contentType(APPLICATION_JSON)
//                .param("1")
//                .content(templateDTO))
//				.andExpect(status().isOk());
	}
}
