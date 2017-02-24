import {NgModule}      from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {FormsModule}   from '@angular/forms';
import {HttpModule}    from '@angular/http';

import {AppComponent}  from './app.component';
import {TemplateComponent}  from './template/template.component';
import {DocumentComponent}  from './document/document.component';
import {AppRoutingModule} from "./app.routing.module";
import {TemplateService} from "./template/template.service";
import {DocumentService} from "./document/document.service";

@NgModule({
    imports: [
        BrowserModule,
        FormsModule,
        HttpModule,
        AppRoutingModule
    ],
    declarations: [
        AppComponent,
        TemplateComponent,
        DocumentComponent
    ],
    providers: [ TemplateService, DocumentService ],
    bootstrap: [AppComponent]
})
export class AppModule {
}
