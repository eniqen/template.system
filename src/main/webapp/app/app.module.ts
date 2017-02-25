import {NgModule} from "@angular/core";
import {BrowserModule} from "@angular/platform-browser";
import {AppComponent} from "./app.component";
import {TemplateComponent} from "./components/template.component";
import {DocumentComponent} from "./components/document.component";
import {appRoutingProviders, routing} from "./app.routing.module";
import {HttpModule} from "@angular/http";
import {FormsModule} from "@angular/forms";
import {TemplateFieldComponent} from "./components/template.field.component";

@NgModule({
    imports: [
        BrowserModule,
        routing,
        HttpModule,
        FormsModule
    ],
    declarations: [
        AppComponent,
        TemplateComponent,
        DocumentComponent,
        TemplateFieldComponent
    ],
    providers: [appRoutingProviders],
    bootstrap: [AppComponent]
})
export class AppModule {
}
