package org.bitbucket.eniqen.api;

import org.bitbucket.eniqen.service.document.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Mikhail Nemenko {@literal <nemenkoma@gmail.com>}
 */
@RestController
@RequestMapping(value = "/documents")
public class DocumentController {

	private final DocumentService documentService;

	@Autowired
	public DocumentController(DocumentService documentService) {
		this.documentService = documentService;
	}

	@GetMapping(value = "/{id}")
	public void getById() {

	}

	@GetMapping(value = "/list")
	public void getAll() {

	}

	@PutMapping(value = "/{id}/update")
	public void update() {

	}

	@DeleteMapping(value = "/{id}/delete")
	public void delete() {

	}
}
