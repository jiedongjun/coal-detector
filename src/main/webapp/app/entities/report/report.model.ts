export interface IReport {
  id?: number;
  coalConfId?: number | null;
  phone?: string | null;
  coalType?: string | null;
  checkDate?: string | null;
  up_M4?: number | null;
  up_A4?: number | null;
  up_V4?: number | null;
  up_S?: number | null;
  up_C?: number | null;
  report1?: number | null;
  report2?: number | null;
  report3?: number | null;
  eggCone2?: number | null;
  up_Aar?: number | null;
  up_Ad?: number | null;
  up_Var?: number | null;
  up_Vd?: number | null;
  up_Vdaf?: number | null;
  up_H?: string | null;
  slime?: string | null;
  cleanCoal?: string | null;
  ganGue?: string | null;
  note?: string | null;
}

export class Report implements IReport {
  constructor(
    public id?: number,
    public coalConfId?: number | null,
    public phone?: string | null,
    public coalType?: string | null,
    public checkDate?: string | null,
    public up_M4?: number | null,
    public up_A4?: number | null,
    public up_V4?: number | null,
    public up_S?: number | null,
    public up_C?: number | null,
    public report1?: number | null,
    public report2?: number | null,
    public report3?: number | null,
    public eggCone2?: number | null,
    public up_Aar?: number | null,
    public up_Ad?: number | null,
    public up_Var?: number | null,
    public up_Vd?: number | null,
    public up_Vdaf?: number | null,
    public up_H?: string | null,
    public slime?: string | null,
    public cleanCoal?: string | null,
    public ganGue?: string | null,
    public note?: string | null
  ) {}
}

export function getReportIdentifier(report: IReport): number | undefined {
  return report.id;
}
