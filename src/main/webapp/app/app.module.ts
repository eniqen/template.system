import {NgModule} from "@angular/core";
import {BrowserModule} from "@angular/platform-browser";
import {AppComponent} from "./app.component";
import {TemplateComponent} from "./components/template/template.component";
import {DocumentComponent} from "./components/document/document.component";
import {appRoutingProviders, routing} from "./app.routing.module";
import {HttpModule} from "@angular/http";
import {FormsModule} from "@angular/forms";
import {TemplateFieldComponent} from "./components/template/template.field.component";
import { MaterialModule } from '@angular/material';
import {DocumentInfoComponent} from "./components/document/document.info.component";


@NgModule({
    imports: [
        BrowserModule,
        routing,
        HttpModule,
        FormsModule,
        MaterialModule
    ],
    declarations: [
        AppComponent,
        TemplateComponent,
        DocumentComponent,
        TemplateFieldComponent,
        DocumentInfoComponent
    ],
    providers: [appRoutingProviders],
    bootstrap: [AppComponent]
})
export class AppModule {
}
