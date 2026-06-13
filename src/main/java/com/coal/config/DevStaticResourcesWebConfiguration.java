package com.coal.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.CacheControl;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import tech.jhipster.config.JHipsterConstants;

@Configuration
@Profile({ JHipsterConstants.SPRING_PROFILE_DEVELOPMENT })
public class DevStaticResourcesWebConfiguration implements WebMvcConfigurer {

    private static final String[] RESOURCE_SUBDIRECTORIES = new String[] { "app/", "content/", "i18n/", "swagger-ui/" };

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        StaticResourcePathResolver
            .resolveStaticDirectory(getClass())
            .ifPresent(
                staticDirectory -> {
                    String baseLocation = StaticResourcePathResolver.toResourceLocation(staticDirectory);
                    for (String subdirectory : RESOURCE_SUBDIRECTORIES) {
                        String resourcePath = "/" + subdirectory.replace("/", "");
                        registry
                            .addResourceHandler(resourcePath + "/**")
                            .addResourceLocations(baseLocation + subdirectory)
                            .setCacheControl(CacheControl.noCache());
                    }
                }
            );
    }
}
