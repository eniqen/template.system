package org.bitbucket.eniqen.api;

import lombok.val;
import org.bitbucket.eniqen.api.dto.CollectionDTO;
import org.bitbucket.eniqen.api.dto.DocumentDTO;
import org.bitbucket.eniqen.service.document.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

import static java.util.Collections.singletonList;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

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

    /**
     * Апи для создания документа
     *
     * @param documentDTO трансфер документа
     * @return созданный объект документа
     */
    @PostMapping(value = "/create",
                 produces = APPLICATION_JSON_VALUE,
                 consumes = APPLICATION_JSON_VALUE)
	public HttpEntity<DocumentDTO> create(@RequestBody DocumentDTO documentDTO){

        val document = this.documentService.create(documentDTO.getName(),
                                                   documentDTO.getDescription(),
                                                   null);
        return ok(DocumentDTO.builder()
                             .id(document.getId())
                             .name(document.getName())
                             .description(document.getDescription())
                             .build());
    }

    /**
     * Апи получения документа по идентификатору
     *
     * @param id идентификатор документа
     * @return найденый
     */
    @GetMapping(value = "/{id}")
	public HttpEntity<DocumentDTO> getById(@PathVariable("id") String id) {
		return documentService.find(id)
                              .map(document -> ok(DocumentDTO.builder()
                                                             .id(document.getId())
                                                             .name(document.getName())
                                                             .description(document.getDescription())
                                                             .build()))
                              .orElse(notFound().build());
	}

    /**
     * Апи получения постраничного списка документов
     *
     * @param pageSize размер постраничной выдачи
     * @param pageNum  номер страницы
     * @return список документов
     */
    @GetMapping(value = "/list")
	public HttpEntity<CollectionDTO<DocumentDTO>> getAll(@RequestParam("pageSize") int pageSize,
                                                         @RequestParam("pageNum") int pageNum) {

        val documents = documentService.findAll(pageSize, pageNum);

        return ok(new CollectionDTO<>(singletonList(new DocumentDTO()), documents.getTotalElements()));
	}

    /**
     * Апи обновления документа
     *
     * @param id          идентификатор документа
     * @param documentDTO трансфер документа
     * @return обновленный результат
     */
    @PutMapping(value = "/{id}/update",
                consumes = APPLICATION_JSON_VALUE,
                produces = APPLICATION_JSON_VALUE)
	public HttpEntity<DocumentDTO> update(@PathVariable("id") String id,
                                          @RequestBody DocumentDTO documentDTO) {
        val updateDocument = this.documentService.update(id,
                                                         documentDTO.getName(),
                                                         documentDTO.getDescription(),
                                                         null);
        return ok(DocumentDTO.builder()
                .id(updateDocument.getId())
                .name(updateDocument.getName())
                .description(updateDocument.getDescription())
                .build());
    }

    /**
     * Апи удаления документа по его идентификатору
     *
     * @param id идентификатор доукмента
     * @return статус ОК
     */
    @DeleteMapping(value = "/{id}/delete")
	public HttpEntity delete(@PathVariable("id") String id) {
        this.documentService.delete(id);
        return ok().build();
	}
}
