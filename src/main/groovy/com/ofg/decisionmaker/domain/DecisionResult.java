package com.ofg.decisionmaker.domain;

import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.OptimisticLockType;
import org.hibernate.annotations.OptimisticLocking;

import javax.persistence.*;

@Entity
@Table(name = "DECISION_RESULTS")
@DynamicUpdate(value = true)
@OptimisticLocking(type = OptimisticLockType.VERSION)
@BatchSize(size = 10)
public class DecisionResult {

    private Long applicationId;

    private Boolean result;

    @Id
    @GeneratedValue(generator = "DECISION_RESULTS_SEQ", strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getResult() {
        return result;
    }

    public void setResult(Boolean result) {
        this.result = result;
    }

    public Long getApplicationId() {
        return applicationId;
    }

    public void setApplicationId(Long applicationId) {
        this.applicationId = applicationId;
    }


}
