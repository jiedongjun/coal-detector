import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'coal-conf',
        data: { pageTitle: 'coalDetectorApp.coalConf.home.title' },
        loadChildren: () => import('./coal-conf/coal-conf.module').then(m => m.CoalConfModule),
      },
      {
        path: 'report',
        data: { pageTitle: 'coalDetectorApp.report.home.title' },
        loadChildren: () => import('./report/report.module').then(m => m.ReportModule),
      },
      {
        path: 'expend',
        data: { pageTitle: 'coalDetectorApp.expend.home.title' },
        loadChildren: () => import('./expend/expend.module').then(m => m.ExpendModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class EntityRoutingModule {}
