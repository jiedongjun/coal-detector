package com.coal.domain;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A CoalConf.
 */
@Entity
@Table(name = "coal_conf")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class CoalConf extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "project")
    private String project;

    @Column(name = "phone")
    private String phone;

    @Column(name = "send")
    private String send;

    @Column(name = "soft_2")
    private Float soft2;

    @Column(name = "soft_3")
    private Float soft3;

    @Column(name = "pay_status")
    private String payStatus;

    @Column(name = "note_1")
    private String note1;

    @Column(name = "note_2")
    private String note2;

    @Column(name = "coal_type")
    private String coalType;

    @Column(name = "up_m")
    private Float up_M;

    @Column(name = "up_m_1")
    private Float up_M1;

    @Column(name = "up_m_2")
    private Float up_M2;

    @Column(name = "up_m_3")
    private Float up_M3;

    @Column(name = "up_m_4")
    private Float up_M4;

    @Column(name = "up_a")
    private Float up_A;

    @Column(name = "up_a_1")
    private Float up_A1;

    @Column(name = "up_a_2")
    private Float up_A2;

    @Column(name = "up_a_3")
    private Float up_A3;

    @Column(name = "up_a_4")
    private Float up_A4;

    @Column(name = "up_v")
    private Float up_V;

    @Column(name = "up_v_1")
    private Float up_V1;

    @Column(name = "up_v_2")
    private Float up_V2;

    @Column(name = "up_v_3")
    private Float up_V3;

    @Column(name = "up_v_4")
    private Float up_V4;

    @Column(name = "low_m")
    private Float low_m;

    @Column(name = "low_m_1")
    private Float low_m1;

    @Column(name = "low_m_2")
    private Float low_m2;

    @Column(name = "low_m_3")
    private Float low_m3;

    @Column(name = "low_m_4")
    private Float low_m4;

    @Column(name = "egg_cone_1")
    private Float eggCone1;

    @Column(name = "egg_cone_2")
    private Float eggCone2;

    @Column(name = "egg_cone_3")
    private Float eggCone3;

    @Column(name = "price_1")
    private Float price1;

    @Column(name = "price_2")
    private Float price2;

    @Column(name = "up_S")
    private Float up_S;

    @Column(name = "up_C")
    private Float up_C;

    @Column(name = "SC_1")
    private Float SC1;

    @Column(name = "SC_2")
    private Float SC2;

    @Column(name = "SC_3")
    private Float SC3;

    @Column(name = "res_1")
    private Float report1;

    @Column(name = "res_2")
    private Float report2;

    @Column(name = "res_3")
    private Float report3;

    @Column(name = "up_Aar")
    private Float up_Aar;

    @Column(name = "up_Ad")
    private Float up_Ad;

    @Column(name = "up_Var")
    private Float up_Var;

    @Column(name = "up_Vd")
    private Float up_Vd;

    @Column(name = "up_Vdaf")
    private Float up_Vdaf;

    public Float getUp_S() {
        return up_S;
    }

    public void setUp_S(Float up_S) {
        this.up_S = up_S;
    }

    public Float getUp_C() {
        return up_C;
    }

    public void setUp_C(Float up_C) {
        this.up_C = up_C;
    }

    public Float getSC1() {
        return SC1;
    }

    public void setSC1(Float SC1) {
        this.SC1 = SC1;
    }

    public Float getSC2() {
        return SC2;
    }

    public void setSC2(Float SC2) {
        this.SC2 = SC2;
    }

    public Float getSC3() {
        return SC3;
    }

    public void setSC3(Float SC3) {
        this.SC3 = SC3;
    }

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CoalConf id(Long id) {
        this.id = id;
        return this;
    }

    public String getProject() {
        return this.project;
    }

    public CoalConf project(String project) {
        this.project = project;
        return this;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getPhone() {
        return this.phone;
    }

    public CoalConf phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSend() {
        return this.send;
    }

    public CoalConf send(String send) {
        this.send = send;
        return this;
    }

    public void setSend(String send) {
        this.send = send;
    }

    public Float getSoft2() {
        return this.soft2;
    }

    public CoalConf soft2(Float soft2) {
        this.soft2 = soft2;
        return this;
    }

    public void setSoft2(Float soft2) {
        this.soft2 = soft2;
    }

    public Float getSoft3() {
        return this.soft3;
    }

    public CoalConf soft3(Float soft3) {
        this.soft3 = soft3;
        return this;
    }

    public void setSoft3(Float soft3) {
        this.soft3 = soft3;
    }

    public String getPayStatus() {
        return this.payStatus;
    }

    public CoalConf payStatus(String payStatus) {
        this.payStatus = payStatus;
        return this;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public String getNote1() {
        return this.note1;
    }

    public CoalConf note1(String note1) {
        this.note1 = note1;
        return this;
    }

    public void setNote1(String note1) {
        this.note1 = note1;
    }

    public String getNote2() {
        return this.note2;
    }

    public CoalConf note2(String note2) {
        this.note2 = note2;
        return this;
    }

    public void setNote2(String note2) {
        this.note2 = note2;
    }

    public String getCoalType() {
        return this.coalType;
    }

    public CoalConf coalType(String coalType) {
        this.coalType = coalType;
        return this;
    }

    public void setCoalType(String coalType) {
        this.coalType = coalType;
    }

    public Float getUp_M() {
        return this.up_M;
    }

    public CoalConf up_M(Float up_M) {
        this.up_M = up_M;
        return this;
    }

    public void setUp_M(Float up_M) {
        this.up_M = up_M;
    }

    public Float getUp_M1() {
        return this.up_M1;
    }

    public CoalConf up_M1(Float up_M1) {
        this.up_M1 = up_M1;
        return this;
    }

    public void setUp_M1(Float up_M1) {
        this.up_M1 = up_M1;
    }

    public Float getUp_M2() {
        return this.up_M2;
    }

    public CoalConf up_M2(Float up_M2) {
        this.up_M2 = up_M2;
        return this;
    }

    public void setUp_M2(Float up_M2) {
        this.up_M2 = up_M2;
    }

    public Float getUp_M3() {
        return this.up_M3;
    }

    public CoalConf up_M3(Float up_M3) {
        this.up_M3 = up_M3;
        return this;
    }

    public void setUp_M3(Float up_M3) {
        this.up_M3 = up_M3;
    }

    public Float getUp_M4() {
        return this.up_M4;
    }

    public CoalConf up_M4(Float up_M4) {
        this.up_M4 = up_M4;
        return this;
    }

    public void setUp_M4(Float up_M4) {
        this.up_M4 = up_M4;
    }

    public Float getUp_A() {
        return this.up_A;
    }

    public CoalConf up_A(Float up_A) {
        this.up_A = up_A;
        return this;
    }

    public void setUp_A(Float up_A) {
        this.up_A = up_A;
    }

    public Float getUp_A1() {
        return this.up_A1;
    }

    public CoalConf up_A1(Float up_A1) {
        this.up_A1 = up_A1;
        return this;
    }

    public void setUp_A1(Float up_A1) {
        this.up_A1 = up_A1;
    }

    public Float getUp_A2() {
        return this.up_A2;
    }

    public CoalConf up_A2(Float up_A2) {
        this.up_A2 = up_A2;
        return this;
    }

    public void setUp_A2(Float up_A2) {
        this.up_A2 = up_A2;
    }

    public Float getUp_A3() {
        return this.up_A3;
    }

    public CoalConf up_A3(Float up_A3) {
        this.up_A3 = up_A3;
        return this;
    }

    public void setUp_A3(Float up_A3) {
        this.up_A3 = up_A3;
    }

    public Float getUp_A4() {
        return this.up_A4;
    }

    public CoalConf up_A4(Float up_A4) {
        this.up_A4 = up_A4;
        return this;
    }

    public void setUp_A4(Float up_A4) {
        this.up_A4 = up_A4;
    }

    public Float getUp_V() {
        return this.up_V;
    }

    public CoalConf up_V(Float up_V) {
        this.up_V = up_V;
        return this;
    }

    public void setUp_V(Float up_V) {
        this.up_V = up_V;
    }

    public Float getUp_V1() {
        return this.up_V1;
    }

    public CoalConf up_V1(Float up_V1) {
        this.up_V1 = up_V1;
        return this;
    }

    public void setUp_V1(Float up_V1) {
        this.up_V1 = up_V1;
    }

    public Float getUp_V2() {
        return this.up_V2;
    }

    public CoalConf up_V2(Float up_V2) {
        this.up_V2 = up_V2;
        return this;
    }

    public void setUp_V2(Float up_V2) {
        this.up_V2 = up_V2;
    }

    public Float getUp_V3() {
        return this.up_V3;
    }

    public CoalConf up_V3(Float up_V3) {
        this.up_V3 = up_V3;
        return this;
    }

    public void setUp_V3(Float up_V3) {
        this.up_V3 = up_V3;
    }

    public Float getUp_V4() {
        return this.up_V4;
    }

    public CoalConf up_V4(Float up_V4) {
        this.up_V4 = up_V4;
        return this;
    }

    public void setUp_V4(Float up_V4) {
        this.up_V4 = up_V4;
    }

    public Float getLow_m() {
        return this.low_m;
    }

    public CoalConf low_m(Float low_m) {
        this.low_m = low_m;
        return this;
    }

    public void setLow_m(Float low_m) {
        this.low_m = low_m;
    }

    public Float getLow_m1() {
        return this.low_m1;
    }

    public CoalConf low_m1(Float low_m1) {
        this.low_m1 = low_m1;
        return this;
    }

    public void setLow_m1(Float low_m1) {
        this.low_m1 = low_m1;
    }

    public Float getLow_m2() {
        return this.low_m2;
    }

    public CoalConf low_m2(Float low_m2) {
        this.low_m2 = low_m2;
        return this;
    }

    public void setLow_m2(Float low_m2) {
        this.low_m2 = low_m2;
    }

    public Float getLow_m3() {
        return this.low_m3;
    }

    public CoalConf low_m3(Float low_m3) {
        this.low_m3 = low_m3;
        return this;
    }

    public void setLow_m3(Float low_m3) {
        this.low_m3 = low_m3;
    }

    public Float getLow_m4() {
        return this.low_m4;
    }

    public CoalConf low_m4(Float low_m4) {
        this.low_m4 = low_m4;
        return this;
    }

    public void setLow_m4(Float low_m4) {
        this.low_m4 = low_m4;
    }

    public Float getEggCone1() {
        return this.eggCone1;
    }

    public CoalConf eggCone1(Float eggCone1) {
        this.eggCone1 = eggCone1;
        return this;
    }

    public void setEggCone1(Float eggCone1) {
        this.eggCone1 = eggCone1;
    }

    public Float getEggCone2() {
        return this.eggCone2;
    }

    public CoalConf eggCone2(Float eggCone2) {
        this.eggCone2 = eggCone2;
        return this;
    }

    public void setEggCone2(Float eggCone2) {
        this.eggCone2 = eggCone2;
    }

    public Float getEggCone3() {
        return this.eggCone3;
    }

    public CoalConf eggCone3(Float eggCone3) {
        this.eggCone3 = eggCone3;
        return this;
    }

    public void setEggCone3(Float eggCone3) {
        this.eggCone3 = eggCone3;
    }

    public Float getPrice1() {
        return this.price1;
    }

    public CoalConf price1(Float price1) {
        this.price1 = price1;
        return this;
    }

    public void setPrice1(Float price1) {
        this.price1 = price1;
    }

    public Float getPrice2() {
        return this.price2;
    }

    public CoalConf price2(Float price2) {
        this.price2 = price2;
        return this;
    }

    public void setPrice2(Float price2) {
        this.price2 = price2;
    }

    public Long getCreated_date() {
        return super.getCreatedDate().toEpochMilli();
    }

    public Float getReport1() {
        return report1;
    }

    public void setReport1(Float report1) {
        this.report1 = report1;
    }

    public Float getReport2() {
        return report2;
    }

    public void setReport2(Float report2) {
        this.report2 = report2;
    }

    public Float getReport3() {
        return report3;
    }

    public void setReport3(Float report3) {
        this.report3 = report3;
    }

    public Float getUp_Aar() {
        return up_Aar;
    }

    public void setUp_Aar(Float up_Aar) {
        this.up_Aar = up_Aar;
    }

    public Float getUp_Ad() {
        return up_Ad;
    }

    public void setUp_Ad(Float up_Ad) {
        this.up_Ad = up_Ad;
    }

    public Float getUp_Var() {
        return up_Var;
    }

    public void setUp_Var(Float up_Var) {
        this.up_Var = up_Var;
    }

    public Float getUp_Vd() {
        return up_Vd;
    }

    public void setUp_Vd(Float up_Vd) {
        this.up_Vd = up_Vd;
    }

    public Float getUp_Vdaf() {
        return up_Vdaf;
    }

    public void setUp_Vdaf(Float up_Vdaf) {
        this.up_Vdaf = up_Vdaf;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof CoalConf)) {
            return false;
        }
        return id != null && id.equals(((CoalConf) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CoalConf{" +
            "id=" + getId() +
            ", project='" + getProject() + "'" +
            ", phone='" + getPhone() + "'" +
            ", send=" + getSend() +
            ", soft2='" + getSoft2() + "'" +
            ", soft3='" + getSoft3() + "'" +
            ", payStatus=" + getPayStatus() +
            ", note1='" + getNote1() + "'" +
            ", note2='" + getNote2() + "'" +
            ", coalType='" + getCoalType() + "'" +
            ", up_M=" + getUp_M() +
            ", up_M1=" + getUp_M1() +
            ", up_M2=" + getUp_M2() +
            ", up_M3=" + getUp_M3() +
            ", up_M4=" + getUp_M4() +
            ", up_A=" + getUp_A() +
            ", up_A1=" + getUp_A1() +
            ", up_A2=" + getUp_A2() +
            ", up_A3=" + getUp_A3() +
            ", up_A4=" + getUp_A4() +
            ", up_V=" + getUp_V() +
            ", up_V1=" + getUp_V1() +
            ", up_V2=" + getUp_V2() +
            ", up_V3=" + getUp_V3() +
            ", up_V4=" + getUp_V4() +
            ", low_m=" + getLow_m() +
            ", low_m1=" + getLow_m1() +
            ", low_m2=" + getLow_m2() +
            ", low_m3=" + getLow_m3() +
            ", low_m4=" + getLow_m4() +
            ", eggCone1='" + getEggCone1() + "'" +
            ", eggCone2='" + getEggCone2() + "'" +
            ", eggCone3='" + getEggCone3() + "'" +
            ", price1=" + getPrice1() +
            ", price2=" + getPrice2() +
            "}";
    }
}
