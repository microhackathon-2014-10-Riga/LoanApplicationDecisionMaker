package com.ofg.decisionmaker.rules

import com.ofg.decisionmaker.LoanApplicationParams
import groovy.transform.PackageScope
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Component

@PackageScope
@Component
@Order(1)
class FraudRule implements Rule {
    
    @Override
    boolean canApply(LoanApplicationParams applicationInfo) {
        return applicationInfo.fraudStatus != 'FRAUD'
    }
}
