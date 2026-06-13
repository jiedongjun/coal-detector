package com.coal.config;

import java.io.File;
import java.util.Optional;
import org.springframework.boot.system.ApplicationHome;

/**
 * Resolves the filesystem location of frontend static assets.
 * <p>
 * In IDE or {@code spring-boot:run} mode, assets are read from {@code target/classes/static/}.
 * In packaged JAR mode, assets are read from a {@code static/} directory next to the JAR file.
 */
public final class StaticResourcePathResolver {

    private StaticResourcePathResolver() {}

    public static Optional<File> resolveStaticDirectory(Class<?> anchorClass) {
        File ideStaticDirectory = new File(
            ApplicationPathResolver.resolveProjectRootDirectory(anchorClass).orElse(new File(".")),
            "target/classes/static"
        );
        if (ideStaticDirectory.isDirectory()) {
            return Optional.of(ideStaticDirectory);
        }
        return resolveJarAdjacentStaticDirectory(anchorClass);
    }

    public static String toResourceLocation(File directory) {
        String location = directory.toURI().toString();
        return location.endsWith("/") ? location : location + "/";
    }

    private static Optional<File> resolveJarAdjacentStaticDirectory(Class<?> anchorClass) {
        ApplicationHome applicationHome = new ApplicationHome(anchorClass);
        File source = applicationHome.getSource();
        if (source != null && source.isFile() && source.getName().endsWith(".jar")) {
            return Optional.of(new File(applicationHome.getDir(), "static"));
        }
        return Optional.empty();
    }
}
