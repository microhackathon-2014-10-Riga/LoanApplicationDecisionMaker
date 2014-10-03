package com.ofg.decisionmaker.matrix

import com.codahale.metrics.MetricRegistry
import groovy.transform.TypeChecked
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@TypeChecked
@Configuration
class JanuszMetricsConfiguration {

    @Bean
    JanuszProbabilityMetrics matchProbabilityMetrics(MetricRegistry metricRegistry) {
        return new JanuszProbabilityMetrics(metricRegistry)
    }
}
