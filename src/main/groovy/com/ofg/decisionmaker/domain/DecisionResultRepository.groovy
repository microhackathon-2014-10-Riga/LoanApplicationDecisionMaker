package com.ofg.decisionmaker.domain
import org.springframework.data.repository.CrudRepository

interface DecisionResultRepository extends CrudRepository<DecisionResult, Long> {

    DecisionResult findByApplicationId(String applicationId);

}
