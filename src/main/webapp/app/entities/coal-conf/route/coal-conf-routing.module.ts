import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { CoalConfComponent } from '../list/coal-conf.component';
import { CoalConfDetailComponent } from '../detail/coal-conf-detail.component';
import { CoalConfUpdateComponent } from '../update/coal-conf-update.component';
import { CoalConfRoutingResolveService } from './coal-conf-routing-resolve.service';

const coalConfRoute: Routes = [
  {
    path: '',
    component: CoalConfComponent,
    data: {
      defaultSort: 'id,asc',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CoalConfDetailComponent,
    resolve: {
      coalConf: CoalConfRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CoalConfUpdateComponent,
    resolve: {
      coalConf: CoalConfRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CoalConfUpdateComponent,
    resolve: {
      coalConf: CoalConfRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(coalConfRoute)],
  exports: [RouterModule],
})
export class CoalConfRoutingModule {}
