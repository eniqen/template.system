package org.bitbucket.eniqen.mapper;

import org.bitbucket.eniqen.api.dto.FieldDTO;
import org.bitbucket.eniqen.api.mapper.FieldMapper;
import org.bitbucket.eniqen.domain.Field;
import org.bitbucket.eniqen.domain.FieldType;
import org.bitbucket.eniqen.domain.TemplateField;
import org.junit.Test;

import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.Matchers.nullValue;

/**
 * @author Mikhail Nemenko {@literal <nemenkoma@gmail.com>}
 */
public class FieldMapperTest {

	private static final FieldType FIELD_1_TYPE = FieldType.CHECKBOX;
	private static final String FIELD_1_NAME = "FIELD_NAME";
	private static final String FIELD_1_DESCRIPTION = "FIELD_DESCRIPTION";
	private static final String FIELD_1_ID = UUID.randomUUID().toString();
	private static final String FIELD_1_VALUE = "FIELD_VALUE";
	private static final int FIELD_1_ORDER = 5;

	@Test
	public void testFieldToFieldDto() {
		Field field = new Field(FIELD_1_TYPE, FIELD_1_NAME, FIELD_1_DESCRIPTION);
		field.setId(FIELD_1_ID);
		field.setVersion(5);

		final FieldDTO fieldDTO = FieldMapper.INSTANCE.toDto(field);

		assertThat(fieldDTO.getId(), equalTo(field.getId()));
		assertThat(fieldDTO.getName(), equalTo(field.getName()));
		assertThat(fieldDTO.getDescription(), equalTo(field.getDescription()));
		assertThat(fieldDTO.getOrder(), nullValue());
		assertThat(fieldDTO.getType(), equalTo(field.getType()));
		assertThat(fieldDTO.getValue(), nullValue());
	}

	@Test
	public void testFieldDtoToField() {
		FieldDTO fieldDTO = new FieldDTO(FIELD_1_ID,
										 FIELD_1_TYPE,
										 FIELD_1_NAME,
										 FIELD_1_DESCRIPTION,
										 FIELD_1_VALUE,
										 FIELD_1_ORDER);

		final Field field = FieldMapper.INSTANCE.toEntity(fieldDTO);

		assertThat(field.getId(), equalTo(field.getId()));
		assertThat(field.getName(), equalTo(field.getName()));
		assertThat(field.getDescription(), equalTo(field.getDescription()));
		assertThat(field.getType(), equalTo(field.getType()));
		assertThat(field.getVersion(), nullValue());
	}

	@Test
	public void test() {
		FieldDTO fieldDTO = new FieldDTO(FIELD_1_ID,
										 FIELD_1_TYPE,
										 FIELD_1_NAME,
										 FIELD_1_DESCRIPTION,
										 FIELD_1_VALUE,
										 FIELD_1_ORDER);

		final TemplateField templateField = FieldMapper.INSTANCE.toTemplateField(fieldDTO);

		assertThat(templateField.getId(), nullValue());
		assertThat(templateField.getTemplate(), nullValue());
		assertThat(templateField.getField(), notNullValue());
		assertThat(templateField.getField().getId(), equalTo(fieldDTO.getId()));
		assertThat(templateField.getField().getName(), equalTo(fieldDTO.getName()));
		assertThat(templateField.getField().getDescription(), equalTo(fieldDTO.getDescription()));
		assertThat(templateField.getField().getType(), equalTo(fieldDTO.getType()));
		assertThat(templateField.getOrdinal(), equalTo(fieldDTO.getOrder()));
	}
}
