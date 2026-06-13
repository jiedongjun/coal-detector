package com.coal.config;

import static org.assertj.core.api.Assertions.assertThat;

import java.io.File;
import org.junit.jupiter.api.Test;

class ApplicationPathResolverTest {

    @Test
    void shouldResolveConfiguredPathRelativeToProjectRootInIde() {
        File resolvedPath = ApplicationPathResolver.resolveConfiguredPath(ApplicationPathResolverTest.class, "secret.key");

        assertThat(resolvedPath.getName()).isEqualTo("secret.key");
        assertThat(resolvedPath.getParentFile()).isNotNull();
    }

    @Test
    void shouldStripFilePrefixFromConfiguredPath() {
        File resolvedPath = ApplicationPathResolver.resolveConfiguredPath(ApplicationPathResolverTest.class, "file:./secret.key");

        assertThat(resolvedPath.getName()).isEqualTo("secret.key");
    }
}
