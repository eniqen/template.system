package org.bitbucket.eniqen;

import com.google.common.collect.ImmutableMap;
import org.bitbucket.eniqen.domain.Field;
import org.bitbucket.eniqen.domain.FieldType;
import org.bitbucket.eniqen.domain.Template;
import org.bitbucket.eniqen.domain.TemplateField;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Map;
import java.util.UUID;

import static java.util.stream.Collectors.groupingBy;

@SpringBootApplication
@EntityScan("org.bitbucket.eniqen.domain")
@EnableJpaRepositories("org.bitbucket.eniqen.domain.repository")
public class TemplateApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(TemplateApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Template template1 = new Template("123", "123");
		template1.setId("111");
		Template template2 = new Template("123", "123");
		template2.setId("222");

		TemplateField t1 = new TemplateField(0, null, template1);
		TemplateField t2 = new TemplateField(1, null, template1);
		TemplateField t3 = new TemplateField(2, null, template2);

		Map<TemplateField, String> templateFields = ImmutableMap.of(t1, "1", t2, "2", t3, "3");

		long count = templateFields.keySet().stream()
								   .map(TemplateField::getTemplate)
								   .map(Template::getId)
								   .distinct()
								   .count();
	}
}
