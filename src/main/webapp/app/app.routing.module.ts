import {RouterModule, Routes} from "@angular/router";
import {DocumentComponent} from "./components/document/document.component";
import {TemplateComponent} from "./components/template/template.component";
import {TemplateService} from "./services/template.service";
import {DocumentService} from "./services/document.service";
import {TemplateFieldComponent} from "./components/template/template.field.component";

export const routes: Routes = [
    {path: '', redirectTo: 'templates', pathMatch: 'full'},
    {
        path: 'templates', component: TemplateComponent,
        children: [
            {path: 'test', component: TemplateFieldComponent},
        ]
    },
    {path: 'documents', component: DocumentComponent}
];

export const appRoutingProviders: any[] = [
    TemplateService, DocumentService
];

export const routing = RouterModule.forRoot(routes);