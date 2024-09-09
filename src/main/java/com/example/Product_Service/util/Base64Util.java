package com.example.Product_Service.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Base64;

public class Base64Util {


    public static String encodeFileToBase64String(Path filePath) throws IOException {
        byte[] fileContent = Files.readAllBytes(filePath);
        return Base64.getEncoder().encodeToString(fileContent);
    }
}
