import {Component} from '@angular/core';
import {Router} from "@angular/router";

@Component({
    moduleId: module.id,
    selector: 'app',
    template: `
    <nav>
      <a [routerLink]="['/templates']">Templates</a>
      <a [routerLink]="['/documents']">Documents</a>
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
