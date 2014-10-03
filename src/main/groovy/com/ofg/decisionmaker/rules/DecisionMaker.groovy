package com.ofg.decisionmaker.rules

import com.ofg.decisionmaker.LoanApplicationInfo
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class DecisionMaker {

    private Collection<Rule> rules

    @Autowired
    DecisionMaker(Collection<Rule> rules) {
        this.rules = rules
    }

    boolean canApply(LoanApplicationInfo loanApplicationInfo) {
        return !rules.any({ rule -> !rule.canApply(loanApplicationInfo) })
    }
}