package com.coal.domain;

import java.io.Serializable;
import java.time.Instant;
import javax.persistence.*;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

/**
 * A Expend.
 */
@Entity
@Table(name = "expend")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Expend extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "pay_time")
    private Instant payTime;

    @Column(name = "amount")
    private Float amount;

    @Column(name = "direction")
    private String direction;

    @Column(name = "pay_way")
    private String payWay;

    @Column(name = "writer")
    private String writer;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Expend id(Long id) {
        this.id = id;
        return this;
    }

    public Instant getPayTime() {
        return this.payTime;
    }

    public Expend payTime(Instant payTime) {
        this.payTime = payTime;
        return this;
    }

    public void setPayTime(Instant payTime) {
        this.payTime = payTime;
    }

    public Float getAmount() {
        return this.amount;
    }

    public Expend amount(Float amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getDirection() {
        return this.direction;
    }

    public Expend direction(String direction) {
        this.direction = direction;
        return this;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public String getPayWay() {
        return this.payWay;
    }

    public Expend payWay(String payWay) {
        this.payWay = payWay;
        return this;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }

    public String getWriter() {
        return this.writer;
    }

    public Expend writer(String writer) {
        this.writer = writer;
        return this;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public Instant getCreatedDate() {
        return super.getCreatedDate();
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Expend)) {
            return false;
        }
        return id != null && id.equals(((Expend) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Expend{" +
            "id=" + getId() +
            ", payTime='" + getPayTime() + "'" +
            ", amount=" + getAmount() +
            ", direction='" + getDirection() + "'" +
            ", payWay='" + getPayWay() + "'" +
            ", writer='" + getWriter() + "'" +
            "}";
    }
}
