package com.ofg.decisionmaker.rules

import com.ofg.decisionmaker.LoanApplicationInfo
import groovy.transform.PackageScope
import org.springframework.stereotype.Component

@PackageScope
@Component

class QARole implements Rule {

    @Override
    boolean canApply(LoanApplicationInfo loanApplicationInfo) {
        return loanApplicationInfo.job != 'QA'
    }
}
