import { Component } from '@angular/core';

@Component({
  moduleId: module.id,
  selector: 'my-app',
  template: `<h1>Hello {{name}}</h1>
    <nav>
      <a routerLink="/documents" routerLinkActive="active">Documents</a>
      <a routerLink="/templates" routerLinkActive="active">Templates</a>
    </nav>
    <router-outlet></router-outlet>`

})
export class AppComponent  { name = 'Angular'; }
