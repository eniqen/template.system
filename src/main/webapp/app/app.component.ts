import {Component} from '@angular/core';
import {Router} from "@angular/router";

@Component({
    moduleId: module.id,
    selector: 'app',
    template: `

      <!---->
      <!--<div class="mdl-layout mdl-js-layout">-->
          <!--<header class="mdl-layout__header">-->
           <!--<div class="mdl-js-ripple-effect">-->
            <!--<a class="mdl-layout__tab" [routerLink]="['/templates']">Шаблоны</a>-->
            <!--<a class="mdl-layout__tab" [routerLink]="['/documents']">Документы</a>-->
           <!--</div>-->
          <!--</header>-->
      <!--</div>-->
 
    <nav md-tab-nav-bar>
      <a md-tab-link [routerLink]="['/templates']">Шаблоны</a>
      <a md-tab-link [routerLink]="['/documents']">Документы</a>
    </nav>

    <div style="color: green; margin-top: 1rem;">Вывод:</div>
    <div style="border: 2px solid green; padding: 1rem;">
      <router-outlet></router-outlet>
    </div>
  `
})
export class AppComponent {

    constructor(private router: Router) {
    }

    onClick() {
        this.router.navigate(['/documents']);
    }
}
