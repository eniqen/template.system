package org.bitbucket.eniqen.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Контроллер для работы с полями
 *
 * @author Mikhail Nemenko {@literal <nemenkoma@gmail.com>}
 */
@RestController
@RequestMapping(value = "/fields")
public class FieldController {

	@GetMapping(value = "/hello")
	public String hello() {
		return "Hello!";
	}
}
