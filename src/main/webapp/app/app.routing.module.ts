
import {RouterModule, Routes} from "@angular/router";
import {DocumentComponent} from "./document/document.component";
import {TemplateComponent} from "./template/template.component";
import {TemplateService} from "./template/template.service";
import {DocumentService} from "./document/document.service";

export const routes: Routes = [
    { path: '', redirectTo: 'templates', pathMatch: 'full' },
    { path: 'templates',  component: TemplateComponent },
    { path: 'documents', component: DocumentComponent }
];

export const appRoutingProviders: any[] = [
    TemplateService, DocumentService
];

export const routing = RouterModule.forRoot(routes);