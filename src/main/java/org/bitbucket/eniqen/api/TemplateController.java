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

import static java.util.Collections.*;
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

	@PostMapping(value = "/create",
                 consumes = APPLICATION_JSON_VALUE,
                 produces = APPLICATION_JSON_VALUE)
	public HttpEntity<TemplateDTO> create(@RequestBody TemplateDTO templateDTO) {

		val template = this.templateService.create(templateDTO.getName(),
                                                   templateDTO.getDescription(),
                                                   null);
		return ok(TemplateDTO.builder()
                             .id(template.getId())
                             .name(template.getName())
                             .description(template.getDescription())
                             .build());
	}

	@GetMapping(value = "/{id}",
				produces = APPLICATION_JSON_VALUE)
	public HttpEntity<TemplateDTO> get(@PathVariable("id") String id) {
		return this.templateService.find(id)
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

		val pageTemplates = this.templateService.findAll(pageNum, pageSize);

		return ok(CollectionDTO.<TemplateDTO>builder()
											.items(singletonList(new TemplateDTO()))
											.count(pageTemplates.getTotalElements())
											.build());

	}

	@PutMapping(value = "/{id}/update",
				consumes = APPLICATION_JSON_VALUE,
				produces = APPLICATION_JSON_VALUE)
	public HttpEntity<TemplateDTO> update(@RequestBody TemplateDTO templateDTO,
										  @PathVariable("id") String id) {
		val template = templateService.update(id, templateDTO.getName(), templateDTO.getDescription(), null);
		return ok(TemplateDTO.builder()
                             .id(template.getId())
                             .name(template.getName())
                             .description(template.getDescription())
                             .build());
	}

	@DeleteMapping(value = "/{id}/delete")
	public HttpEntity delete(@PathVariable("id") String id) {
		this.templateService.delete(id);
		return ok().build();
	}
}
