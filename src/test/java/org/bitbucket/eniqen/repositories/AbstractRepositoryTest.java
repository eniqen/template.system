package org.bitbucket.eniqen.repositories;

import com.github.springtestdbunit.DbUnitTestExecutionListener;
import org.bitbucket.eniqen.Application;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

/**
 * Базовый класс для тестирования репозиториев
 *
 * @author Mikhail Nemenko {@literal <nemenkoma@gmail.com>}
 */
@DirtiesContext
@TestExecutionListeners(DbUnitTestExecutionListener.class)
@SpringApplicationConfiguration(Application.class)
public abstract class AbstractRepositoryTest extends AbstractJUnit4SpringContextTests {
}
