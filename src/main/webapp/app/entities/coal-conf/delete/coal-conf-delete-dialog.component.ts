import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { ICoalConf } from '../coal-conf.model';
import { CoalConfService } from '../service/coal-conf.service';

@Component({
  templateUrl: './coal-conf-delete-dialog.component.html',
})
export class CoalConfDeleteDialogComponent {
  coalConf?: ICoalConf;

  constructor(protected coalConfService: CoalConfService, public activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.coalConfService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
