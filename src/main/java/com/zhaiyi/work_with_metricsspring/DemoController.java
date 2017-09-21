package com.zhaiyi.work_with_metricsspring;

import com.codahale.metrics.Histogram;
import com.codahale.metrics.UniformReservoir;
import com.codahale.metrics.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by zhaiyi on 2017/9/21.
 */

@Controller
public class DemoController {
    final Random random = new Random();
    Set<Integer> set = new HashSet<Integer>();

    @PostConstruct
    public void init() {
        Thread thread = new Thread(() -> {
            while (true) {
                set.add(random.nextInt(10000));
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                }
            }
        });
        thread.setDaemon(true);
        thread.start();
    }

    @Metric
    public Histogram histogram = new Histogram(new UniformReservoir());

    @RequestMapping("/histogram")
    public void histogram() {
        histogram.update(random.nextInt(2000));
    }

    @Timed
    @RequestMapping("/timed")
    public void timed() throws InterruptedException {
        Thread.sleep(random.nextInt(150));
    }

    @Metered
    @RequestMapping("/metered")
    public void metered() throws InterruptedException {
        Thread.sleep(random.nextInt(150));
    }

    @Counted
    @RequestMapping("/counted")
    public void counted() throws InterruptedException {
        Thread.sleep(random.nextInt(150));
    }

    @Gauge
    public int setSize() {
        return set.size();
    }

    @CachedGauge(timeout = 2, timeoutUnit = TimeUnit.SECONDS)
    public int cacheSetSize() {
        return set.size();
    }

    @ExceptionMetered
    @RequestMapping("/exceptionmetered")
    public void exceptionMetered() {
        if (random.nextBoolean()) {
            throw new RuntimeException();
        }
    }


}
