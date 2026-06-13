package com.coal.config;

import static java.net.URLDecoder.decode;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Optional;
import org.springframework.boot.system.ApplicationHome;

/**
 * Resolves filesystem paths relative to the application home directory.
 * <p>
 * In IDE or {@code spring-boot:run} mode, the home directory is the project root.
 * In packaged JAR mode, the home directory is the folder that contains the JAR file.
 */
public final class ApplicationPathResolver {

    private ApplicationPathResolver() {}

    public static File resolveApplicationDirectory(Class<?> anchorClass) {
        ApplicationHome applicationHome = new ApplicationHome(anchorClass);
        File source = applicationHome.getSource();
        if (source != null && source.isFile() && source.getName().endsWith(".jar")) {
            return applicationHome.getDir();
        }
        return resolveProjectRootDirectory(anchorClass).orElseGet(() -> new File(System.getProperty("user.dir")));
    }

    public static Optional<File> resolveProjectRootDirectory(Class<?> anchorClass) {
        try {
            URL resource = anchorClass.getResource("");
            if (resource == null) {
                return Optional.empty();
            }
            String fullExecutablePath = decode(resource.getPath(), StandardCharsets.UTF_8.name());
            String rootPath = Paths.get(".").toUri().normalize().getPath();
            String extractedPath = fullExecutablePath.replace(rootPath, "");
            int extractionEndIndex = extractedPath.indexOf("target/");
            if (extractionEndIndex > 0) {
                return Optional.of(new File(extractedPath.substring(0, extractionEndIndex)));
            }
        } catch (UnsupportedEncodingException ignored) {
            // Fall through.
        }
        return Optional.empty();
    }

    public static File resolveConfiguredPath(Class<?> anchorClass, String configuredPath) {
        if (configuredPath == null || configuredPath.isBlank()) {
            throw new IllegalArgumentException("Configured path must not be blank");
        }
        String path = configuredPath.trim();
        if (path.startsWith("file:")) {
            path = path.substring("file:".length());
        }
        while (path.startsWith("./")) {
            path = path.substring(2);
        }
        File configuredFile = new File(path);
        if (configuredFile.isAbsolute()) {
            return configuredFile;
        }
        return new File(resolveApplicationDirectory(anchorClass), path);
    }
}
