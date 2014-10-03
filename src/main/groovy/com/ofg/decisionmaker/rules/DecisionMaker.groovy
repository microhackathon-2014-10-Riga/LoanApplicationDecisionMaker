package com.ofg.decisionmaker.rules

import com.ofg.decisionmaker.LoanApplicationParams
import com.ofg.decisionmaker.domain.DecisionResult
import com.ofg.decisionmaker.domain.DecisionResultRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DecisionMaker {

    private final Collection<Rule> rules
    private final DecisionResultRepository decisionResultRepository

    @Autowired
    DecisionMaker(Collection<Rule> rules, DecisionResultRepository decisionResultRepository) {
        this.rules = rules
        this.decisionResultRepository = decisionResultRepository
    }

    boolean canApply(LoanApplicationParams loanApplicationInfo, Long applicationId) {
        boolean result = !rules.any({ rule -> !rule.canApply(loanApplicationInfo) })
        DecisionResult decisionResult = decisionResultRepository.findByApplicationId(applicationId)
        if (decisionResult != null) {
            decisionResult.result = result
        } else {
            decisionResult = new DecisionResult(result: result, applicationId: applicationId)
        }
        decisionResultRepository.save(decisionResult)
    }
}