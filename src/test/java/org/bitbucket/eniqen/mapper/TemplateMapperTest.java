package org.bitbucket.eniqen.mapper;

import org.bitbucket.eniqen.api.dto.FieldDTO;
import org.bitbucket.eniqen.api.dto.TemplateDTO;
import org.bitbucket.eniqen.api.mapper.TemplateMapper;
import org.bitbucket.eniqen.domain.Field;
import org.bitbucket.eniqen.domain.FieldType;
import org.bitbucket.eniqen.domain.Template;
import org.bitbucket.eniqen.domain.TemplateField;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Set;
import java.util.UUID;

import static com.google.common.collect.ImmutableSet.of;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * @author Mikhail Nemenko {@literal <nemenkoma@gmail.com>}
 */
public class TemplateMapperTest {

	private static final String TEMPLATE_1_NAME = "TEMPLATE_NAME";
	private static final String TEMPLATE_1_ID = UUID.randomUUID().toString();
	private static final String TEMPLATE_1_DESCRIPTION = "TEMPLATE_DESCRIPTION";

	private static final FieldType FIELD_1_TYPE = FieldType.CHECKBOX;
	private static final String FIELD_1_NAME = "FIELD_NAME_1";
	private static final String FIELD_1_DESCRIPTION = "FIELD_DESCRIPTION_1";
	private static final String FIELD_1_ID = UUID.randomUUID().toString();
	private static final Integer FIELD_1_ORDER = 0;

	private static final FieldType FIELD_2_TYPE = FieldType.INPUT;
	private static final String FIELD_2_NAME = "FIELD_NAME_2";
	private static final String FIELD_2_DESCRIPTION = "FIELD_DESCRIPTION_2";
	private static final String FIELD_2_ID = UUID.randomUUID().toString();
	private static final Integer FIELD_2_ORDER = 1;

	private static final FieldType FIELD_3_TYPE = FieldType.TEXTAREA;
	private static final String FIELD_3_NAME = "FIELD_NAME_3";
	private static final String FIELD_3_DESCRIPTION = "FIELD_DESCRIPTION_3";
	private static final String FIELD_3_ID = UUID.randomUUID().toString();
	private static final Integer FIELD_3_ORDER = 2;
	private static Field field1;
	private static Field field2;
	private static Field field3;
	private static Template template;
	private static Set<TemplateField> TEMPLATE_FIELDS;

	@Before
	public void init() {
		field1 = new Field(FIELD_1_TYPE, FIELD_1_NAME, FIELD_1_DESCRIPTION);
		field1.setId(FIELD_1_ID);

		field2 = new Field(FIELD_2_TYPE, FIELD_2_NAME, FIELD_2_DESCRIPTION);
		field2.setId(FIELD_2_ID);

		field3 = new Field(FIELD_3_TYPE, FIELD_3_NAME, FIELD_3_DESCRIPTION);
		field3.setId(FIELD_3_ID);

		template = new Template(TEMPLATE_1_NAME, TEMPLATE_1_DESCRIPTION);
		template.setId(TEMPLATE_1_ID);

		TEMPLATE_FIELDS = of(new TemplateField(FIELD_1_ORDER, field1, template),
							 new TemplateField(FIELD_2_ORDER, field2, template),
							 new TemplateField(FIELD_3_ORDER, field3, template));

		template.setFields(TEMPLATE_FIELDS);
	}

	@Test
	public void testTemplateToTemplateDto() {
		final TemplateDTO templateDTO = TemplateMapper.INSTANCE.toDto(template);

		assertThat(templateDTO.getId(), equalTo(template.getId()));
		assertThat(templateDTO.getDescription(), equalTo(template.getDescription()));
		assertThat(templateDTO.getName(), equalTo(template.getName()));
		assertThat(templateDTO.getFields(), notNullValue());
		assertThat(templateDTO.getFields(), hasSize(3));

		final List<FieldDTO> fields = templateDTO.getFields();

		fields.forEach(fieldDto -> {
			assertThat(fieldDto.getId(), notNullValue());
			assertThat(fieldDto.getType(), notNullValue());
			assertThat(fieldDto.getName(), notNullValue());
			assertThat(fieldDto.getDescription(), notNullValue());
			assertThat(fieldDto.getValue(), nullValue());
			assertThat(fieldDto.getOrder(), notNullValue());
		});

		final Template template = TemplateMapper.INSTANCE.toEntity(templateDTO);
		System.out.println(template);
	}

}
