import {NgModule} from "@angular/core";
import {BrowserModule} from "@angular/platform-browser";
import {AppComponent} from "./app.component";
import {TemplateComponent} from "./template/template.component";
import {DocumentComponent} from "./document/document.component";
import {appRoutingProviders, routing} from "./app.routing.module";
import {HttpModule} from "@angular/http";
import {FormsModule} from "@angular/forms";

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
        DocumentComponent
    ],
    providers: [appRoutingProviders],
    bootstrap: [AppComponent]
})
export class AppModule {
}
