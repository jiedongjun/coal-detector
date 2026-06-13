package com.coal.config;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockServletContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

class DevStaticResourcesWebConfigurationTest {

    private DevStaticResourcesWebConfiguration devStaticResourcesWebConfiguration;
    private ResourceHandlerRegistry resourceHandlerRegistry;

    @BeforeEach
    void setUp() {
        devStaticResourcesWebConfiguration = new DevStaticResourcesWebConfiguration();
        MockServletContext servletContext = spy(new MockServletContext());
        WebApplicationContext applicationContext = mock(WebApplicationContext.class);
        resourceHandlerRegistry = spy(new ResourceHandlerRegistry(applicationContext, servletContext));
    }

    @Test
    void shouldRegisterResourceHandlersWhenStaticDirectoryExists() {
        devStaticResourcesWebConfiguration.addResourceHandlers(resourceHandlerRegistry);

        if (StaticResourcePathResolver.resolveStaticDirectory(getClass()).isPresent()) {
            verify(resourceHandlerRegistry, atLeastOnce()).addResourceHandler(any(String[].class));
        } else {
            verify(resourceHandlerRegistry, never()).addResourceHandler(any(String[].class));
        }
    }
}
