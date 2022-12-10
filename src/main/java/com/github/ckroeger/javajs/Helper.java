package com.github.ckroeger.javajs;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public class Helper {

    private static final Logger LOGGER = LoggerFactory.getLogger(Helper.class);

    public Optional<String> loadFromClassPath(String pathToResource) {
        try {
            URL resource = getClass().getResource(pathToResource);
            if (resource == null) {
                LOGGER.error("Can not load {} from classpath.", pathToResource);
                return Optional.empty();
            }
            Path path = Paths.get(resource.toURI());
            return Optional.of(Files.readString(path, StandardCharsets.UTF_8));
        } catch (URISyntaxException | IOException e) {
            LOGGER.error("Can not load {} from classpath. message={}", pathToResource, e.getMessage());
            return Optional.empty();
        }
    }
}
