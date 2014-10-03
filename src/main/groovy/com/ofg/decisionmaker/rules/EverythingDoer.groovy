package com.ofg.decisionmaker.rules
import com.ofg.decisionmaker.LoanApplicationParams
import com.ofg.decisionmaker.domain.DecisionResult
import com.ofg.decisionmaker.domain.DecisionResultRepository
import com.ofg.infrastructure.web.resttemplate.fluent.ServiceRestClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class EverythingDoer {

    private final Collection<Rule> rules
    private final DecisionResultRepository decisionResultRepository
    private final ServiceRestClient serviceRestClient

    @Autowired
    EverythingDoer(Collection<Rule> rules, DecisionResultRepository decisionResultRepository, ServiceRestClient serviceRestClient) {
        this.serviceRestClient = serviceRestClient
        this.rules = rules
        this.decisionResultRepository = decisionResultRepository
    }

    boolean doTheJob(LoanApplicationParams loanApplicationInfo, Long applicationId) {
        boolean result = !rules.any({ rule -> !rule.canApply(loanApplicationInfo) })
        DecisionResult decisionResult = decisionResultRepository.findByApplicationId(applicationId)
        if (decisionResult != null) {
            decisionResult.result = result
        } else {
            decisionResult = new DecisionResult(result: result, applicationId: applicationId)
        }
        decisionResultRepository.save(decisionResult)
        propagateToMarketing(applicationId, loanApplicationInfo, result)
        propagateToReporting(applicationId, loanApplicationInfo, result)
        return result
    }

    private String propagateToReporting(long applicationId, LoanApplicationParams loanApplicationInfo, boolean result) {
        serviceRestClient.forService("reporting-service")
                .post()
                .onUrl("/reporting")
                .body("""{
                    "loanId" : "$applicationId}",
                    "job" : "${loanApplicationInfo.job}",
                    "amount" : ${loanApplicationInfo.amount},
                    "fraudStatus" : "${loanApplicationInfo.fraudStatus}",
                    "decision" : "$result"
                }""")
                .anObject()
                .ofType(String)
    }

    private String propagateToMarketing(long applicationId, LoanApplicationParams loanApplicationInfo, boolean result) {
        serviceRestClient.forService("marketing-offer-generator")
                .post()
                .onUrl("/marketing/$applicationId")
                .body("""{
                        "person" : {
                        "firstName" : "${loanApplicationInfo.firstName}",
                        "lastName" : "${loanApplicationInfo.lastName}"
                         },
                     "decision" : "$result"
                    }""")
                .anObject()
                .ofType(String)
    }

    public DecisionResult checkDecision(Long applicationId) {
        return decisionResultRepository.findByApplicationId(applicationId)
    }
}