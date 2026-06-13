package com.coal.config;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import java.util.Optional;
import org.junit.jupiter.api.Test;

class StaticResourcePathResolverTest {

    @Test
    void shouldResolveIdeStaticDirectoryWhenRunningFromTargetClasses() {
        Optional<File> staticDirectory = StaticResourcePathResolver.resolveStaticDirectory(StaticResourcePathResolverTest.class);

        if (new File("target/classes/static/").isDirectory()) {
            assertThat(staticDirectory).isPresent();
            assertThat(staticDirectory.orElseThrow().getPath()).endsWith("target" + File.separator + "classes" + File.separator + "static");
        }
    }

    @Test
    void shouldFormatResourceLocationWithTrailingSlash() {
        File directory = new File("target/classes/static");

        assertThat(StaticResourcePathResolver.toResourceLocation(directory)).endsWith("/");
    }
}
