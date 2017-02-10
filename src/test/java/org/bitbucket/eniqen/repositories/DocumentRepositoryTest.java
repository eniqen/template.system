package org.bitbucket.eniqen.repositories;

import com.github.springtestdbunit.annotation.DatabaseOperation;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import com.github.springtestdbunit.annotation.DatabaseTearDown;

/**
 * @author Mikhail Nemenko {@literal <nemenkoma@gmail.com>}
 */
@DatabaseSetup(DocumentRepositoryTest.DATASET)
@DatabaseTearDown(type = DatabaseOperation.DELETE_ALL, value = DocumentRepositoryTest.DATASET)
public class DocumentRepositoryTest extends AbstractRepositoryTest {
    public static final String DATASET = "";
}
