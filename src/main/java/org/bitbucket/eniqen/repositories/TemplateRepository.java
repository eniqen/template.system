package org.bitbucket.eniqen.repositories;

import org.springframework.boot.autoconfigure.amqp.RabbitProperties;

/**
 * @author Mikhail Nemenko {@literal <nemenkoma@gmail.com>}
 */
public interface TemplateRepository extends BaseRepository<RabbitProperties.Template, String> {
}
