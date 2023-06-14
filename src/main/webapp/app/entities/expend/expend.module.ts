import { NgModule } from '@angular/core';

import { SharedModule } from 'app/shared/shared.module';
import { ExpendComponent } from './list/expend.component';
import { ExpendDetailComponent } from './detail/expend-detail.component';
import { ExpendUpdateComponent } from './update/expend-update.component';
import { ExpendDeleteDialogComponent } from './delete/expend-delete-dialog.component';
import { ExpendRoutingModule } from './route/expend-routing.module';

@NgModule({
  imports: [SharedModule, ExpendRoutingModule],
  declarations: [ExpendComponent, ExpendDetailComponent, ExpendUpdateComponent, ExpendDeleteDialogComponent],
  entryComponents: [ExpendDeleteDialogComponent],
})
export class ExpendModule {}
