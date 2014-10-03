package com.ofg.decisionmaker

import groovy.transform.CompileStatic
import groovy.transform.PackageScope
import groovy.transform.ToString

@PackageScope
@CompileStatic
@ToString
class LoanApplicationParams {

    String firstName
    String lastName
    String job
    BigDecimal amount
    String fraudStatus
}
