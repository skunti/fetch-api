package com.fetch.utilities;

import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.Layout;
import org.apache.log4j.spi.LoggingEvent;
import org.testng.Reporter;

/**
 * ReportAppender is a custom Log4j appender that integrates log messages
 * with TestNG reports by sending log events to the TestNG Reporter.
 */
public class ReportAppender extends AppenderSkeleton {

    /**
     * Appends a logging event to the TestNG Reporter.
     *
     * @param event the logging event to append
     */
    @Override
    protected void append(final LoggingEvent event) {
        Reporter.log(eventToString(event));
    }

    /**
     * Converts a LoggingEvent to a formatted string, including any throwable details.
     *
     * @param event the logging event to convert
     * @return a string representation of the event
     */
    private String eventToString(final LoggingEvent event) {
        final StringBuilder result = new StringBuilder(layout.format(event));

        if (layout.ignoresThrowable()) {
            final String[] s = event.getThrowableStrRep();
            if (s != null) {
                for (final String value : s) {
                    result.append(value).append(Layout.LINE_SEP);
                }
            }
        }
        return result.toString();
    }

    /**
     * Closes the appender. This implementation does nothing.
     */
    @Override
    public void close() {
        // No resources to close
    }

    /**
     * Indicates whether a layout is required for this appender.
     *
     * @return true, as a layout is required
     */
    @Override
    public boolean requiresLayout() {
        return true;
    }
}
