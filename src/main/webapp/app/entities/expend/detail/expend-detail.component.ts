import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IExpend } from '../expend.model';

@Component({
  selector: 'jhi-expend-detail',
  templateUrl: './expend-detail.component.html',
})
export class ExpendDetailComponent implements OnInit {
  expend: IExpend | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ expend }) => {
      this.expend = expend;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
