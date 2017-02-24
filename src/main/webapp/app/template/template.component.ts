import {TemplateService} from "./template.service";
import {Component} from "@angular/core/src/metadata/directives";
import {OnInit} from "@angular/core";
import {Router} from "@angular/router";

@Component({

})
class TemplateComponent implements OnInit{

    constructor(private templateService: TemplateService, private router: Router)

    abstract ngOnInit(): void {
    }
}