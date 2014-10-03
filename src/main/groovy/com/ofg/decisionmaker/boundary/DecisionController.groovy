package com.ofg.decisionmaker.boundary

import com.ofg.decisionmaker.LoanApplicationInfo
import com.wordnik.swagger.annotations.Api
import com.wordnik.swagger.annotations.ApiOperation
import groovy.util.logging.Slf4j
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import javax.validation.constraints.NotNull

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import static org.springframework.web.bind.annotation.RequestMethod.PUT

@Slf4j
@RestController
@RequestMapping('/api')
@Api(value = "loanApplication", description = "Get a decision if an application is risky or not")
class DecisionController {

    @RequestMapping(
            value = '/loanApplication/{loanApplicationId}',
            method = PUT,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Deciding if an applicaton is risky or not",
            notes = "Will check deciding whether an applicaton is risky or not ")
    void decide(@PathVariable @NotNull Long loanApplicationId, @ModelAttribute @NotNull LoanApplicationInfo applicationInfo) {

    }

}
