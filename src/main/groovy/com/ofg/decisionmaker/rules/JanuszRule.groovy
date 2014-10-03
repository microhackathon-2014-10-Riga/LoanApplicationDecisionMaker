package com.ofg.decisionmaker.rules

import com.ofg.decisionmaker.LoanApplicationParams
import groovy.transform.PackageScope
import org.springframework.stereotype.Component

@PackageScope
@Component
class JanuszRule implements Rule {

    @Override
    boolean canApply(LoanApplicationParams loanApplicationInfo) {
        return !loanApplicationInfo.firstName.equalsIgnoreCase('janusz')
    }
}
