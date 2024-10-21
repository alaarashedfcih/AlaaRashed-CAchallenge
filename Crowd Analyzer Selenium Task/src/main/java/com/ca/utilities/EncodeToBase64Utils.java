package com.ca.utilities;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;

public class EncodeToBase64Utils {

    private static final Logger logger = LoggerFactory.getLogger(EncodeToBase64Utils.class);

    public String encodeFileToBase64Binary(File file) {
        String encodedFile = null;
        try (FileInputStream fileInputStream = new FileInputStream(file)) {
            byte[] bytes = new byte[(int) file.length()];
            int bytesRead = fileInputStream.read(bytes);

            if (bytesRead == bytes.length) {
                encodedFile = Base64.getEncoder().encodeToString(bytes);
            } else {
                logger.warn("Could not read the entire file. Expected {} bytes, but read {} bytes.", bytes.length, bytesRead);
            }
        } catch (FileNotFoundException e) {
            logger.error("File not found: {}", file.getAbsolutePath(), e);
        } catch (IOException e) {
            logger.error("Error reading file: {}", file.getAbsolutePath(), e);
        }
        return encodedFile;
    }
}
