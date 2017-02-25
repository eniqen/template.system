
import {RouterModule, Routes} from "@angular/router";
import {DocumentComponent} from "./components/document.component";
import {TemplateComponent} from "./components/template.component";
import {TemplateService} from "./services/template.service";
import {DocumentService} from "./services/document.service";

export const routes: Routes = [
    // { path: '', redirectTo: 'templates', pathMatch: 'full' },
    { path: 'templates',  component: TemplateComponent },
    { path: 'documents', component: DocumentComponent }
];

export const appRoutingProviders: any[] = [
    TemplateService, DocumentService
];

export const routing = RouterModule.forRoot(routes);