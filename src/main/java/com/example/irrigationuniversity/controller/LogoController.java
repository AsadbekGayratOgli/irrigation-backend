package com.example.irrigationuniversity.controller;

import com.example.irrigationuniversity.services.LogoService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
@CrossOrigin
@RestController
@RequestMapping("/api/logo")
public class LogoController {
    private final LogoService logoService;

    public LogoController(LogoService logoService) {
        this.logoService = logoService;
    }
    @PreAuthorize("hasAnyRole('ROLE_SUPERADMIN','ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<String> saveLogo(@RequestBody MultipartFile file) throws IOException {
        return logoService.saveLogo(file);
    }
    @GetMapping
    public ResponseEntity<String> getLogo(){
        return logoService.getLogo();
    }
    @GetMapping("/{url}/{id}")
    public void getLogoImage(@PathVariable String url,@PathVariable String id, HttpServletResponse httpServletResponse){
        System.out.println(url);
        logoService.getLogoImage(url+"/"+id,httpServletResponse);
    }
}
