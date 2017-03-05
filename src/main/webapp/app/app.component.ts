import {Component} from '@angular/core';
import {Router} from "@angular/router";

@Component({
    moduleId: module.id,
    selector: 'app',
    template: `
                <md-toolbar color="primary">
                    <nav md-tab-nav-bar>
                        <a md-tab-link [routerLink]="['/templates']">Шаблоны</a>
                        <a md-tab-link [routerLink]="['/documents']">Документы</a>
                    </nav>
                </md-toolbar>
                <router-outlet></router-outlet>
  `
})
export class AppComponent {

    constructor(private router: Router) {
    }

    onClick() {
        this.router.navigate(['/documents']);
    }
}