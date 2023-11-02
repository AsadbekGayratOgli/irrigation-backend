package com.example.irrigationuniversity.services;

import com.example.irrigationuniversity.entity.Logo;
import com.example.irrigationuniversity.repository.LogoRepository;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class LogoServiceImpl implements LogoService{
    private final LogoRepository logoRepository;
    private final FileService fileService;
    @Override
    public ResponseEntity<String> saveLogo(MultipartFile file) throws IOException {
        for (Logo logo : logoRepository.findAll()) {
            fileService.deleteFiles(logo.getUrl());
        }
        logoRepository.deleteAll();
        return  ResponseEntity.ok().body(logoRepository.save(new Logo(UUID.randomUUID(),fileService.saveFile("logo",file))).getUrl());
    }
    @Override
    public ResponseEntity<String> getLogo() {
        return ResponseEntity.ok().body(logoRepository.findUrlById());
    }
    @Override
    public void getLogoImage(String url, HttpServletResponse httpServletResponse) {
        fileService.getFile(url,httpServletResponse);
    }


}
