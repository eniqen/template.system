package org.bitbucket.eniqen.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by sbt-nemenko-ma on 31.01.2017.
 */
@Configuration
@EnableJpaRepositories(basePackages = "../java/org/bitbucket/eniqen/repositories/*")
public class DatabaseConfig {
}
