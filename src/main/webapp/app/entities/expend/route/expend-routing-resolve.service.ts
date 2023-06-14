import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { mergeMap } from 'rxjs/operators';

import { IExpend, Expend } from '../expend.model';
import { ExpendService } from '../service/expend.service';

@Injectable({ providedIn: 'root' })
export class ExpendRoutingResolveService implements Resolve<IExpend> {
  constructor(protected service: ExpendService, protected router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<IExpend> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        mergeMap((expend: HttpResponse<Expend>) => {
          if (expend.body) {
            return of(expend.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Expend());
  }
}
