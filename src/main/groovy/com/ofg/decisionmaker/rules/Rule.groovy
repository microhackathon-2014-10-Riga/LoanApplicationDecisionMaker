package com.ofg.decisionmaker.rules

import com.ofg.decisionmaker.LoanApplicationInfo
import groovy.transform.PackageScope

@PackageScope
interface Rule {
    boolean canApply(LoanApplicationInfo applicationInfo)
}