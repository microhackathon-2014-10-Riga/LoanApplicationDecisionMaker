package com.ofg.decisionmaker.rules

import com.ofg.decisionmaker.LoanApplicationParams
import groovy.transform.PackageScope

@PackageScope
interface Rule {
    boolean canApply(LoanApplicationParams applicationInfo)
}