package com.ofg.decisionmaker.rules

import com.ofg.decisionmaker.LoanApplicationInfo
import groovy.transform.PackageScope

@PackageScope
class JanuszRule implements Rule {

    @Override
    boolean canApply(LoanApplicationInfo loanApplicationInfo) {
        return loanApplicationInfo.firstName != 'Janusz'
    }
}
