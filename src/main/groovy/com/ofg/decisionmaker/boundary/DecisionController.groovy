package com.ofg.decisionmaker.boundary
import com.ofg.decisionmaker.LoanApplicationParams
import com.ofg.decisionmaker.domain.DecisionResult
import com.ofg.decisionmaker.matrix.JanuszProbabilityMetrics
import com.ofg.decisionmaker.rules.EverythingDoer
import com.wordnik.swagger.annotations.Api
import com.wordnik.swagger.annotations.ApiOperation
import groovy.util.logging.Slf4j
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import javax.validation.constraints.NotNull

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import static org.springframework.web.bind.annotation.RequestMethod.GET
import static org.springframework.web.bind.annotation.RequestMethod.PUT

@Slf4j
@RestController
@RequestMapping('/api')
@Api(value = "loanApplication", description = "Get a decision if an application is risky or not")
class DecisionController {

    private final EverythingDoer everythinDoer

    JanuszProbabilityMetrics januszProbabilityMetrics;

    @Autowired
    DecisionController(EverythingDoer decisionMaker, JanuszProbabilityMetrics januszProbabilityMetrics) {
        this.everythinDoer = decisionMaker
        this.januszProbabilityMetrics = januszProbabilityMetrics
    }

    @RequestMapping(
            value = '/loanApplication/{loanApplicationId}',
            method = PUT,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Deciding if an applicaton is risky or not",
            notes = "Will check deciding whether an applicaton is risky or not ")
    void decide(@PathVariable @NotNull String loanApplicationId, @RequestBody @NotNull LoanApplicationParams applicationInfo) {
        boolean result = everythinDoer.doTheJob(applicationInfo, loanApplicationId)
        januszProbabilityMetrics.update(applicationInfo.firstName.equalsIgnoreCase('janusz'))
        log.info("application $loanApplicationId risk evaluation result. Can apply: $result")
    }

    @RequestMapping(
            value = '/loanApplication/{loanApplicationId}',
            method = GET,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "the same as above",
            notes = "but better ")
    DecisionResult checkDecision(@PathVariable @NotNull String loanApplicationId) {

        DecisionResult result = everythinDoer.checkDecision(loanApplicationId)

        log.info("checking for: $loanApplicationId . result: $result")
        return result
    }

}
