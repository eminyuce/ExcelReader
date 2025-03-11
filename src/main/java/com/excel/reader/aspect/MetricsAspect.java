package com.excel.reader.aspect;

import io.micrometer.core.instrument.MeterRegistry;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

@Aspect
@Component
@Slf4j
public class MetricsAspect {

    private final MeterRegistry meterRegistry;

    public MetricsAspect(MeterRegistry meterRegistry) {
        this.meterRegistry = meterRegistry;
    }

    @Around("@annotation(com.excel.reader.annotation.TimedExecution)")
    public Object trackExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.nanoTime();
        try {
            // Proceed with the method execution
            return joinPoint.proceed();
        } finally {
            long end = System.nanoTime();
            long durationInNano = end - start;

            // Convert nanoseconds to seconds
            double durationInSeconds = durationInNano / 1_000_000_000.0;

            // Format the duration in seconds with 3 decimal places
            DecimalFormat df = new DecimalFormat("#.###");
            String formattedDuration = df.format(durationInSeconds);

            // Record the execution time in Prometheus via Micrometer
            meterRegistry.timer("custom.execution.time", "method", joinPoint.getSignature().toString())
                    .record(durationInNano, TimeUnit.NANOSECONDS);

            // Log the execution time in seconds with a readable format
            log.info("Execution time of method {}: {} seconds", joinPoint.getSignature(), formattedDuration);
        }
    }
}
