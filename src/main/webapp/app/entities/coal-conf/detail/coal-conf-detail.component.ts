import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICoalConf } from '../coal-conf.model';

@Component({
  selector: 'jhi-coal-conf-detail',
  templateUrl: './coal-conf-detail.component.html',
})
export class CoalConfDetailComponent implements OnInit {
  coalConf: ICoalConf | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ coalConf }) => {
      this.coalConf = coalConf;
    });
  }

  previousState(): void {
    window.history.back();
  }
}
