import * as dayjs from 'dayjs';

export interface IExpend {
  id?: number;
  payTime?: dayjs.Dayjs | null;
  amount?: number | null;
  direction?: string | null;
  payWay?: string | null;
  writer?: string | null;
}

export class Expend implements IExpend {
  constructor(
    public id?: number,
    public payTime?: dayjs.Dayjs | null,
    public amount?: number | null,
    public direction?: string | null,
    public payWay?: string | null,
    public writer?: string | null
  ) {}
}

export function getExpendIdentifier(expend: IExpend): number | undefined {
  return expend.id;
}
