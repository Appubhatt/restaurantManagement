package com.task.restaurantmanagement.fileupload;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.apache.commons.lang3.RandomStringUtils;
public class FileUploadUtil {

    public static String saveFile(String fileName, MultipartFile multipartFile) throws IOException {

        Path uploadDirectory = Paths.get("C:/Users/baps/IdeaProjects/restaurantManagement/src/main/resources/static/images");
        String fileCode = RandomStringUtils.randomAlphanumeric(8);

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadDirectory.resolve(fileCode + "-" + fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);

        } catch (IOException e) {
            throw new IOException("Error saving uploaded File: " + fileName, e);
        }
        return fileCode + "-" + fileName;
    }
}
