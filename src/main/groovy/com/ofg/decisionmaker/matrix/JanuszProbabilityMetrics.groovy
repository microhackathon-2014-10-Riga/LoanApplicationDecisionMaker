package com.ofg.decisionmaker.matrix
import com.codahale.metrics.Meter
import com.codahale.metrics.MetricRegistry

class JanuszProbabilityMetrics {

    Meter januszMeter

    Meter othersMeter

    JanuszProbabilityMetrics(MetricRegistry metricRegistry) {
       januszMeter = metricRegistry.meter("decisionmaker.origin.lodz.numberofjanuszes")
        othersMeter = metricRegistry.meter("decisionmaker.origin.lodz.numberofothers")
    }

    void update(boolean isJanusz) {
        if (isJanusz)
        januszMeter.mark()
        else
            othersMeter.mark()
    }
}
