import { NgModule } from '@angular/core';

import { SharedModule } from 'app/shared/shared.module';
import { CoalConfComponent } from './list/coal-conf.component';
import { CoalConfDetailComponent } from './detail/coal-conf-detail.component';
import { CoalConfUpdateComponent } from './update/coal-conf-update.component';
import { CoalConfDeleteDialogComponent } from './delete/coal-conf-delete-dialog.component';
import { CoalConfRoutingModule } from './route/coal-conf-routing.module';

@NgModule({
  imports: [SharedModule, CoalConfRoutingModule],
  declarations: [CoalConfComponent, CoalConfDetailComponent, CoalConfUpdateComponent, CoalConfDeleteDialogComponent],
  entryComponents: [CoalConfDeleteDialogComponent],
})
export class CoalConfModule {}
