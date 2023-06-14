package com.coal.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties specific to Coal Detector.
 * <p>
 * Properties are configured in the {@code application.yml} file.
 * See {@link tech.jhipster.config.JHipsterProperties} for a good example.
 */
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {

    private String reportTemplatePath;
    private String keyPath;
    private String reportPath;

    public String getReportTemplatePath() {
        return reportTemplatePath;
    }

    public void setReportTemplatePath(String reportTemplatePath) {
        this.reportTemplatePath = reportTemplatePath;
    }

    public String getKeyPath() {
        return keyPath;
    }

    public void setKeyPath(String keyPath) {
        this.keyPath = keyPath;
    }

    public String getReportPath() {
        return reportPath;
    }

    public void setReportPath(String reportPath) {
        this.reportPath = reportPath;
    }
}
