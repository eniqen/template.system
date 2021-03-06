package org.bitbucket.eniqen.api;

import org.bitbucket.eniqen.api.dto.CollectionDTO;
import org.bitbucket.eniqen.api.dto.FieldDTO;
import org.bitbucket.eniqen.api.dto.TemplateDTO;
import org.bitbucket.eniqen.api.mapper.FieldMapper;
import org.bitbucket.eniqen.api.mapper.TemplateMapper;
import org.bitbucket.eniqen.domain.Template;
import org.bitbucket.eniqen.domain.TemplateField;
import org.bitbucket.eniqen.service.field.FieldService;
import org.bitbucket.eniqen.service.template.TemplateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.Set;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

/**
 * @author Mikhail Nemenko {@literal <nemenkoma@gmail.com>}
 */
@RestController
@RequestMapping(TemplateController.URL)
public class TemplateController {

	static final String URL = "/templates";

	private final TemplateService templateService;
	private final FieldService fieldService;

	@Autowired
	public TemplateController(TemplateService templateService, FieldService fieldService) {
		this.templateService = templateService;
		this.fieldService = fieldService;
	}

	@PostMapping(value = "/create",
				 consumes = APPLICATION_JSON_VALUE,
				 produces = APPLICATION_JSON_VALUE)
	public HttpEntity<TemplateDTO> create(@RequestBody TemplateDTO templateDTO) {

        Set<TemplateField> templateFields = FieldMapper.INSTANCE.toTemplateFields(templateDTO.getFields());

        Template savedTemplate = this.templateService.create(templateDTO.getName(),
                                                             templateDTO.getDescription(),
                                                             templateFields);

        TemplateDTO returnTemplateDTO = TemplateMapper.INSTANCE.toDto(savedTemplate);
        returnTemplateDTO.getFields().sort(Comparator.comparingInt(FieldDTO::getOrder));
		return ok(returnTemplateDTO);
	}

	@GetMapping(value = "/{id}",
				produces = APPLICATION_JSON_VALUE)
	public HttpEntity<TemplateDTO> get(@PathVariable("id") String id) {
		return this.templateService.find(id)
								   .map(template -> ok(TemplateMapper.INSTANCE.toDto(template)))
								   .orElse(notFound().build());
	}

	@GetMapping(value = "/list",
				produces = APPLICATION_JSON_VALUE)
	public HttpEntity<CollectionDTO<TemplateDTO>> getAll(@RequestParam("pageSize") int pageSize,
														 @RequestParam("pageNum") int pageNum) {

		Page<Template> pageTemplates = this.templateService.findAll(pageNum, pageSize);

		return ok(new CollectionDTO<>(TemplateMapper.INSTANCE.toListDto(pageTemplates.getContent()),
									  pageTemplates.getTotalElements()));
	}

	@PutMapping(value = "/{id}/update",
				consumes = APPLICATION_JSON_VALUE,
				produces = APPLICATION_JSON_VALUE)
	public HttpEntity<TemplateDTO> update(@RequestBody TemplateDTO templateDTO,
										  @PathVariable("id") String id) {

		final Set<TemplateField> templateFields = FieldMapper.INSTANCE.toTemplateFields(templateDTO.getFields());

		final Template template = templateService.update(id,
														 templateDTO.getName(),
														 templateDTO.getDescription(),
														 templateFields);
		return ok(TemplateMapper.INSTANCE.toDto(template));
	}

	@DeleteMapping(value = "/{id}/delete")
	public HttpEntity delete(@PathVariable("id") String id) {
		this.templateService.delete(id);
		return ok().build();
	}
}
