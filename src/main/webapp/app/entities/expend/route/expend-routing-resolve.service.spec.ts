jest.mock('@angular/router');

import { TestBed } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { ActivatedRouteSnapshot, Router } from '@angular/router';
import { of } from 'rxjs';

import { IExpend, Expend } from '../expend.model';
import { ExpendService } from '../service/expend.service';

import { ExpendRoutingResolveService } from './expend-routing-resolve.service';

describe('Service Tests', () => {
  describe('Expend routing resolve service', () => {
    let mockRouter: Router;
    let mockActivatedRouteSnapshot: ActivatedRouteSnapshot;
    let routingResolveService: ExpendRoutingResolveService;
    let service: ExpendService;
    let resultExpend: IExpend | undefined;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
        providers: [Router, ActivatedRouteSnapshot],
      });
      mockRouter = TestBed.inject(Router);
      mockActivatedRouteSnapshot = TestBed.inject(ActivatedRouteSnapshot);
      routingResolveService = TestBed.inject(ExpendRoutingResolveService);
      service = TestBed.inject(ExpendService);
      resultExpend = undefined;
    });

    describe('resolve', () => {
      it('should return IExpend returned by find', () => {
        // GIVEN
        service.find = jest.fn(id => of(new HttpResponse({ body: { id } })));
        mockActivatedRouteSnapshot.params = { id: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultExpend = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultExpend).toEqual({ id: 123 });
      });

      it('should return new IExpend if id is not provided', () => {
        // GIVEN
        service.find = jest.fn();
        mockActivatedRouteSnapshot.params = {};

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultExpend = result;
        });

        // THEN
        expect(service.find).not.toBeCalled();
        expect(resultExpend).toEqual(new Expend());
      });

      it('should route to 404 page if data not found in server', () => {
        // GIVEN
        spyOn(service, 'find').and.returnValue(of(new HttpResponse({ body: null })));
        mockActivatedRouteSnapshot.params = { id: 123 };

        // WHEN
        routingResolveService.resolve(mockActivatedRouteSnapshot).subscribe(result => {
          resultExpend = result;
        });

        // THEN
        expect(service.find).toBeCalledWith(123);
        expect(resultExpend).toEqual(undefined);
        expect(mockRouter.navigate).toHaveBeenCalledWith(['404']);
      });
    });
  });
});
