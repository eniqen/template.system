import {DocumentService} from "../../services/document.service";
import {Router} from "@angular/router";
import {Component, OnInit} from "@angular/core";

@Component({
    moduleId: module.id,
    selector: 'documents',
    templateUrl: 'document.component.html',
    styleUrls: ['document.component.css']
})
export class DocumentComponent implements OnInit {
    documents: Array<Documents>;
    selectedDocument: Document;
    newDocument: Document;

    constructor(private documentService: DocumentService,
                private router: Router) {
    }

    getDocuments(pageSize: number, pageNum: number): void {
        this.documentService.getDocuments(pageNum, pageSize)
            .then(docs => this.documents = docs.items);
    }

    ngOnInit(): void {
        this.getDocuments(5, 0);
    }

    onSelect(document: Document): void {
        this.selectedDocument = document;
    }
}