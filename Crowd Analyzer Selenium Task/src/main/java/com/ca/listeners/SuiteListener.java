package com.ca.listeners;

import lombok.extern.slf4j.Slf4j;
import org.testng.ISuite;
import org.testng.ISuiteListener;

@Slf4j
public class SuiteListener implements ISuiteListener {

    @Override
    public void onStart(ISuite suite) {
        log.info("Starting suite: {}", suite.getName());
        log.info("Extent Reports Version 5 is initializing for suite: {}", suite.getName());
    }


    @Override
    public void onFinish(ISuite suite) {
        try {
            log.info("Finishing suite: {}", suite.getName());
            log.info("Extent Reports Version 5 is completing for suite: {}", suite.getName());

            // Ensure the TestListener.extent object is not null before flushing
            if (TestListener.extent != null) {
                TestListener.extent.flush();
                log.info("Extent Reports successfully flushed.");
            } else {
                log.warn("Extent Reports instance was null, skipping flush operation.");
            }
        } catch (Exception e) {
            log.error("Error while finalizing the Extent Reports: ", e);
        }
    }
}
