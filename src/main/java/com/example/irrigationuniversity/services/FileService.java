package com.example.irrigationuniversity.services;

import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface FileService {

    String saveFile(String packageName, MultipartFile file) throws IOException;

    @SneakyThrows
    void getFile(String url, HttpServletResponse httpServletResponse);
    ResponseEntity<String > deleteFiles(String url);
}
