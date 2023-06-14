import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IExpend } from '../expend.model';
import { ExpendService } from '../service/expend.service';

@Component({
  templateUrl: './expend-delete-dialog.component.html',
})
export class ExpendDeleteDialogComponent {
  expend?: IExpend;

  constructor(protected expendService: ExpendService, public activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.expendService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
