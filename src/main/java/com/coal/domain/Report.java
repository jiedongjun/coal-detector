package com.coal.domain;

import java.io.Serializable;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Report.
 */
@Entity
@Table(name = "report")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Report extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "coal_conf_id")
    private Long coalConfId;

    @Column(name = "phone")
    private String phone;

    @Column(name = "coal_type")
    private String coalType;

    @Column(name = "check_date")
    private String checkDate;

    @Column(name = "up_m_4")
    private Float up_M4;

    @Column(name = "up_a_4")
    private Float up_A4;

    @Column(name = "up_v_4")
    private Float up_V4;

    @Column(name = "up_s")
    private Float up_S;

    @Column(name = "up_c")
    private Float up_C;

    @Column(name = "report_1")
    private Float report1;

    @Column(name = "report_2")
    private Float report2;

    @Column(name = "report_3")
    private Float report3;

    @Column(name = "egg_cone_2")
    private Float eggCone2;

    @Column(name = "up_aar")
    private Float up_Aar;

    @Column(name = "up_ad")
    private Float up_Ad;

    @Column(name = "up_var")
    private Float up_Var;

    @Column(name = "up_vd")
    private Float up_Vd;

    @Column(name = "up_vdaf")
    private Float up_Vdaf;

    @Column(name = "up_h")
    private String up_H;

    @Column(name = "slime")
    private String slime;

    @Column(name = "clean_coal")
    private String cleanCoal;

    @Column(name = "gan_gue")
    private String ganGue;

    @Column(name = "note")
    private String note;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Report id(Long id) {
        this.id = id;
        return this;
    }

    public Long getCoalConfId() {
        return this.coalConfId;
    }

    public Report coalConfId(Long coalConfId) {
        this.coalConfId = coalConfId;
        return this;
    }

    public void setCoalConfId(Long coalConfId) {
        this.coalConfId = coalConfId;
    }

    public String getPhone() {
        return this.phone;
    }

    public Report phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getCoalType() {
        return this.coalType;
    }

    public Report coalType(String coalType) {
        this.coalType = coalType;
        return this;
    }

    public void setCoalType(String coalType) {
        this.coalType = coalType;
    }

    public String getCheckDate() {
        return this.checkDate;
    }

    public Report checkDate(String checkDate) {
        this.checkDate = checkDate;
        return this;
    }

    public void setCheckDate(String checkDate) {
        this.checkDate = checkDate;
    }

    public Float getUp_M4() {
        return this.up_M4;
    }

    public Report up_M4(Float up_M4) {
        this.up_M4 = up_M4;
        return this;
    }

    public void setUp_M4(Float up_M4) {
        this.up_M4 = up_M4;
    }

    public Float getUp_A4() {
        return this.up_A4;
    }

    public Report up_A4(Float up_A4) {
        this.up_A4 = up_A4;
        return this;
    }

    public void setUp_A4(Float up_A4) {
        this.up_A4 = up_A4;
    }

    public Float getUp_V4() {
        return this.up_V4;
    }

    public Report up_V4(Float up_V4) {
        this.up_V4 = up_V4;
        return this;
    }

    public void setUp_V4(Float up_V4) {
        this.up_V4 = up_V4;
    }

    public Float getUp_S() {
        return this.up_S;
    }

    public Report up_S(Float up_S) {
        this.up_S = up_S;
        return this;
    }

    public void setUp_S(Float up_S) {
        this.up_S = up_S;
    }

    public Float getUp_C() {
        return this.up_C;
    }

    public Report up_C(Float up_C) {
        this.up_C = up_C;
        return this;
    }

    public void setUp_C(Float up_C) {
        this.up_C = up_C;
    }

    public Float getReport1() {
        return this.report1;
    }

    public Report report1(Float report1) {
        this.report1 = report1;
        return this;
    }

    public void setReport1(Float report1) {
        this.report1 = report1;
    }

    public Float getReport2() {
        return this.report2;
    }

    public Report report2(Float report2) {
        this.report2 = report2;
        return this;
    }

    public void setReport2(Float report2) {
        this.report2 = report2;
    }

    public Float getReport3() {
        return this.report3;
    }

    public Report report3(Float report3) {
        this.report3 = report3;
        return this;
    }

    public void setReport3(Float report3) {
        this.report3 = report3;
    }

    public Float getEggCone2() {
        return this.eggCone2;
    }

    public Report eggCone2(Float eggCone2) {
        this.eggCone2 = eggCone2;
        return this;
    }

    public void setEggCone2(Float eggCone2) {
        this.eggCone2 = eggCone2;
    }

    public Float getUp_Aar() {
        return this.up_Aar;
    }

    public Report up_Aar(Float up_Aar) {
        this.up_Aar = up_Aar;
        return this;
    }

    public void setUp_Aar(Float up_Aar) {
        this.up_Aar = up_Aar;
    }

    public Float getUp_Ad() {
        return this.up_Ad;
    }

    public Report up_Ad(Float up_Ad) {
        this.up_Ad = up_Ad;
        return this;
    }

    public void setUp_Ad(Float up_Ad) {
        this.up_Ad = up_Ad;
    }

    public Float getUp_Var() {
        return this.up_Var;
    }

    public Report up_Var(Float up_Var) {
        this.up_Var = up_Var;
        return this;
    }

    public void setUp_Var(Float up_Var) {
        this.up_Var = up_Var;
    }

    public Float getUp_Vd() {
        return this.up_Vd;
    }

    public Report up_Vd(Float up_Vd) {
        this.up_Vd = up_Vd;
        return this;
    }

    public void setUp_Vd(Float up_Vd) {
        this.up_Vd = up_Vd;
    }

    public Float getUp_Vdaf() {
        return this.up_Vdaf;
    }

    public Report up_Vdaf(Float up_Vdaf) {
        this.up_Vdaf = up_Vdaf;
        return this;
    }

    public void setUp_Vdaf(Float up_Vdaf) {
        this.up_Vdaf = up_Vdaf;
    }

    public String getUp_H() {
        return this.up_H;
    }

    public Report up_H(String up_H) {
        this.up_H = up_H;
        return this;
    }

    public void setUp_H(String up_H) {
        this.up_H = up_H;
    }

    public String getSlime() {
        return this.slime;
    }

    public Report slime(String slime) {
        this.slime = slime;
        return this;
    }

    public void setSlime(String slime) {
        this.slime = slime;
    }

    public String getCleanCoal() {
        return this.cleanCoal;
    }

    public Report cleanCoal(String cleanCoal) {
        this.cleanCoal = cleanCoal;
        return this;
    }

    public void setCleanCoal(String cleanCoal) {
        this.cleanCoal = cleanCoal;
    }

    public String getGanGue() {
        return this.ganGue;
    }

    public Report ganGue(String ganGue) {
        this.ganGue = ganGue;
        return this;
    }

    public void setGanGue(String ganGue) {
        this.ganGue = ganGue;
    }

    public String getNote() {
        return this.note;
    }

    public Report note(String note) {
        this.note = note;
        return this;
    }

    public void setNote(String note) {
        this.note = note;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Report)) {
            return false;
        }
        return id != null && id.equals(((Report) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Report{" +
            "id=" + getId() +
            ", coalConfId=" + getCoalConfId() +
            ", phone='" + getPhone() + "'" +
            ", coalType='" + getCoalType() + "'" +
            ", checkDate='" + getCheckDate() + "'" +
            ", up_M4=" + getUp_M4() +
            ", up_A4=" + getUp_A4() +
            ", up_V4=" + getUp_V4() +
            ", up_S=" + getUp_S() +
            ", up_C=" + getUp_C() +
            ", report1=" + getReport1() +
            ", report2=" + getReport2() +
            ", report3=" + getReport3() +
            ", eggCone2=" + getEggCone2() +
            ", up_Aar=" + getUp_Aar() +
            ", up_Ad=" + getUp_Ad() +
            ", up_Var=" + getUp_Var() +
            ", up_Vd=" + getUp_Vd() +
            ", up_Vdaf=" + getUp_Vdaf() +
            ", up_H='" + getUp_H() + "'" +
            ", slime='" + getSlime() + "'" +
            ", cleanCoal='" + getCleanCoal() + "'" +
            ", ganGue='" + getGanGue() + "'" +
            ", note='" + getNote() + "'" +
            "}";
    }
}
