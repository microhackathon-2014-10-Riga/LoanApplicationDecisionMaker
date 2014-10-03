package com.ofg.decisionmaker.boundary

import groovy.transform.CompileStatic
import groovy.transform.PackageScope

@PackageScope
@CompileStatic
class LoanApplicationInfo {
    
    String firstName
    String lastName
    String job
    BigDecimal amount
    String fraudStatus
}
