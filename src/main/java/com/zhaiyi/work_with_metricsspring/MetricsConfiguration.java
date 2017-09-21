package com.zhaiyi.work_with_metricsspring;

import com.codahale.metrics.ConsoleReporter;
import com.codahale.metrics.MetricRegistry;
import com.codahale.metrics.jvm.FileDescriptorRatioGauge;
import com.codahale.metrics.jvm.GarbageCollectorMetricSet;
import com.codahale.metrics.jvm.MemoryUsageGaugeSet;
import com.codahale.metrics.jvm.ThreadStatesGaugeSet;
import com.ryantenney.metrics.spring.config.annotation.EnableMetrics;
import com.ryantenney.metrics.spring.config.annotation.MetricsConfigurerAdapter;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

/**
 * Created by zhaiyi on 2017/9/21.
 */

@Configuration
@EnableMetrics
public class MetricsConfiguration extends MetricsConfigurerAdapter {

    @Override
    public void configureReporters(MetricRegistry metricRegistry) {
        //配置jvm metric
//        metricRegistry.register("gc", new GarbageCollectorMetricSet());
//        metricRegistry.register("memory usage", new MemoryUsageGaugeSet());
//        metricRegistry.register("thread state", new ThreadStatesGaugeSet());
//        metricRegistry.register("fd ratio", new FileDescriptorRatioGauge());

        registerReporter(ConsoleReporter.forRegistry(metricRegistry).build())
                .start(10, TimeUnit.SECONDS);
    }
}
