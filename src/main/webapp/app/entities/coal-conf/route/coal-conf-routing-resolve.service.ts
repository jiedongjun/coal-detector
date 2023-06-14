import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { ICoalConf, CoalConf } from '../coal-conf.model';
import { CoalConfService } from '../service/coal-conf.service';

@Injectable({ providedIn: 'root' })
export class CoalConfRoutingResolveService implements Resolve<ICoalConf> {
  constructor(protected service: CoalConfService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICoalConf> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((coalConf: HttpResponse<CoalConf>) => {
          if (coalConf.body) {
            return of(coalConf.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new CoalConf());
  }
}
