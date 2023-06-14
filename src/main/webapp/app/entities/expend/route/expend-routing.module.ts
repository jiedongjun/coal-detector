import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { ExpendComponent } from '../list/expend.component';
import { ExpendDetailComponent } from '../detail/expend-detail.component';
import { ExpendUpdateComponent } from '../update/expend-update.component';
import { ExpendRoutingResolveService } from './expend-routing-resolve.service';

const expendRoute: Routes = [
  {
    path: '',
    component: ExpendComponent,
    data: {
      defaultSort: 'id,asc',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: ExpendDetailComponent,
    resolve: {
      expend: ExpendRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: ExpendUpdateComponent,
    resolve: {
      expend: ExpendRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: ExpendUpdateComponent,
    resolve: {
      expend: ExpendRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(expendRoute)],
  exports: [RouterModule],
})
export class ExpendRoutingModule {}
