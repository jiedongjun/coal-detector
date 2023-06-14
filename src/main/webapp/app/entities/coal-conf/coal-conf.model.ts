export interface ICoalConf {
  id?: number;
  project?: string | null;
  phone?: string | null;
  send?: number | null;
  soft2?: string | null;
  soft3?: string | null;
  payStatus?: number | null;
  note1?: string | null;
  note2?: string | null;
  coalType?: string | null;
  up_M?: number | null;
  up_M1?: number | null;
  up_M2?: number | null;
  up_M3?: number | null;
  up_M4?: number | null;
  up_A?: number | null;
  up_A1?: number | null;
  up_A2?: number | null;
  up_A3?: number | null;
  up_A4?: number | null;
  up_V?: number | null;
  up_V1?: number | null;
  up_V2?: number | null;
  up_V3?: number | null;
  up_V4?: number | null;
  low_m?: number | null;
  low_m1?: number | null;
  low_m2?: number | null;
  low_m3?: number | null;
  low_m4?: number | null;
  eggCone1?: string | null;
  eggCone2?: string | null;
  eggCone3?: string | null;
  price1?: number | null;
  price2?: number | null;
}

export class CoalConf implements ICoalConf {
  constructor(
    public id?: number,
    public project?: string | null,
    public phone?: string | null,
    public send?: number | null,
    public soft2?: string | null,
    public soft3?: string | null,
    public payStatus?: number | null,
    public note1?: string | null,
    public note2?: string | null,
    public coalType?: string | null,
    public up_M?: number | null,
    public up_M1?: number | null,
    public up_M2?: number | null,
    public up_M3?: number | null,
    public up_M4?: number | null,
    public up_A?: number | null,
    public up_A1?: number | null,
    public up_A2?: number | null,
    public up_A3?: number | null,
    public up_A4?: number | null,
    public up_V?: number | null,
    public up_V1?: number | null,
    public up_V2?: number | null,
    public up_V3?: number | null,
    public up_V4?: number | null,
    public low_m?: number | null,
    public low_m1?: number | null,
    public low_m2?: number | null,
    public low_m3?: number | null,
    public low_m4?: number | null,
    public eggCone1?: string | null,
    public eggCone2?: string | null,
    public eggCone3?: string | null,
    public price1?: number | null,
    public price2?: number | null
  ) {}
}

export function getCoalConfIdentifier(coalConf: ICoalConf): number | undefined {
  return coalConf.id;
}
