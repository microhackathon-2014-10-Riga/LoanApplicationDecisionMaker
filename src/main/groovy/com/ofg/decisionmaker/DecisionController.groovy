package com.ofg.decisionmaker
import com.wordnik.swagger.annotations.Api
import com.wordnik.swagger.annotations.ApiOperation
import groovy.util.logging.Slf4j
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import javax.validation.constraints.NotNull

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE
import static org.springframework.web.bind.annotation.RequestMethod.PUT

@Slf4j
@RestController
@RequestMapping('/decide')
@Api(value = "loanApplication", description = "Get a decision wheter an application is risky or not")
class DecisionController {

    @RequestMapping(
            value = '{loanApplication}',
            method = PUT,
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Deciding whether an applicaton is risky or not",
            notes = "Will check deciding whether an applicaton is risky or not ")
    String decide(@RequestBody @NotNull String applicationInfo) {
        return null
    }

}
