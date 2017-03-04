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
                <md-grid-list style="padding-top: 1%" cols="6" rowHeight="550px">
                    <md-grid-tile [colspan]="1" [style.background]="'lightblue'">
                        <templates></templates>
                    </md-grid-tile>
                    <md-grid-tile [colspan]="5" [style.background]="'lightgreen'">
                        <template-fields></template-fields>
                    </md-grid-tile>
                </md-grid-list>
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