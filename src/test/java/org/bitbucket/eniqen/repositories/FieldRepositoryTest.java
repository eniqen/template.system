package org.bitbucket.eniqen.repositories;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;
import org.bitbucket.eniqen.models.Field;
import org.bitbucket.eniqen.models.FieldType;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;

/**
 * @author Mikhail Nemenko {@literal <nemenkoma@gmail.com>}
 */
@DatabaseSetup(FieldRepositoryTest.DATASET)
@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = FieldRepositoryTest.DATASET)
public class FieldRepositoryTest extends AbstractRepositoryTest {

    public static final String DATASET = "classpath:datasets/field-table.xml";

    private static final String FIELD_ID = "9aa16d3f-7997-4111-9293-4936b2e43de5";
    private static final String FIELD_NOT_EXIST_ID = UUID.randomUUID().toString();
    private static final FieldType FIELD_TYPE = FieldType.CHECKBOX;
    private static final String FIELD_NAME = "FIELD_NAME_TEST";
    private static final String FIELD_DESCRIPTION = "FIELD_NAME_DESCRIPTION";

    @Autowired
    private FieldRepository fieldRepository;

    @Test
    @Ignore
    public void findOneWhenExisting() {
        Optional<Field> field = fieldRepository.findOne(FIELD_ID);
        assertThat(field.isPresent(), equalTo(true));
        assertThat(field.get().getId(), equalTo(FIELD_ID));
    }

    @Test
    public void findOneWhenNotExisting() {
        Optional<Field> field = fieldRepository.findOne(FIELD_NOT_EXIST_ID);
        assertThat(field.isPresent(), equalTo(false));
    }

    @Test
    public void saveNew() {
        Field field = new Field(FIELD_TYPE, FIELD_NAME, FIELD_DESCRIPTION);
        Field savedField = fieldRepository.save(field);

        List<Field> fields = fieldRepository.findAll();

        assertThat(field.getFieldType(), equalTo(savedField.getFieldType()));
        assertThat(field.getDescription(), equalTo(savedField.getDescription()));
        assertThat(field.getName(), equalTo(savedField.getName()));
        assertThat(fields, hasSize(4));
    }
}

