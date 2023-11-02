package com.example.irrigationuniversity.services;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface LogoService {


    ResponseEntity<String> saveLogo(MultipartFile file) throws IOException;

    ResponseEntity<String> getLogo();


    void getLogoImage(String url, HttpServletResponse httpServletResponse);
}
