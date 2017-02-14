package org.bitbucket.eniqen.api;

import lombok.val;
import org.bitbucket.eniqen.api.dto.CollectionDTO;
import org.bitbucket.eniqen.api.dto.TemplateDTO;
import org.bitbucket.eniqen.domain.Template;
import org.bitbucket.eniqen.service.template.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.*;

/**
 * @author Mikhail Nemenko {@literal <nemenkoma@gmail.com>}
 */
@RestController
@RequestMapping(value = "/templates")
public class TemplateController {
	// FIXME: 14.02.2017 Мапперы
	private final TemplateService templateService;

	@Autowired
	public TemplateController(TemplateService templateService) {
		this.templateService = templateService;
	}

	@PostMapping(value = "/create")
	public HttpEntity<TemplateDTO> create(@RequestBody TemplateDTO templateDTO) {
		val template = templateService.create(templateDTO.getName(), templateDTO.getDescription(), null);
		return ok(new TemplateDTO());
	}

	@GetMapping(value = "/{id}",
				produces = APPLICATION_JSON_VALUE)
	public HttpEntity<TemplateDTO> get(@PathVariable("id") String id) {
		return templateService.find(id)
							  .map(template -> ok(TemplateDTO.builder()
															 .id(template.getId())
															 .name(template.getName())
															 .description(template.getDescription())
															 .build()))
							  .orElse(notFound().build());
	}

	@GetMapping(value = "/list",
				produces = APPLICATION_JSON_VALUE)
	public HttpEntity<CollectionDTO<TemplateDTO>> getAll(@RequestParam("pageSize") int pageSize,
														 @RequestParam("pageNum") int pageNum) {

		val pageTemplates = templateService.findAll(pageNum, pageSize);

		return ok(new CollectionDTO<>(Collections.singletonList(new TemplateDTO()),
									  pageTemplates.getTotalElements()));

	}

	@PutMapping(value = "/{id}/update",
				consumes = APPLICATION_JSON_VALUE,
				produces = APPLICATION_JSON_VALUE)
	public HttpEntity<TemplateDTO> update(@RequestBody TemplateDTO templateDTO,
										  @PathVariable("id") String id) {
		val template = templateService.update(id, templateDTO.getName(), templateDTO.getDescription(), null);
		return ok(new TemplateDTO());
	}

	@DeleteMapping(value = "/{id}/delete")
	public HttpEntity delete() {
		return ok().build();
	}
}
