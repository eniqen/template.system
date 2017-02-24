import {DocumentService} from "./document.service";
import {Router} from "@angular/router";
import {Component} from "@angular/core";

@Component({
    selector: 'documents',
    template: `<h1> Documents </h1>`
})
export class DocumentComponent {
    constructor(private documentService: DocumentService,
                private router: Router) {}
}