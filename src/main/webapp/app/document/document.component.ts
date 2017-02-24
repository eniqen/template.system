import {DocumentService} from "./document.service";
import {Router} from "@angular/router";
import {Component} from "@angular/core";

@Component({})
export class DocumentComponent {
    constructor(private documentService: DocumentService,
                private router: Router) {}
}