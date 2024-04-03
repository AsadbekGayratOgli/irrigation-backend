package com.example.irrigationuniversity.services;

import ij.ImagePlus;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import ij.ImagePlus;
import ij.process.ImageProcessor;
import org.springframework.web.multipart.MultipartFile;
import java.util.Arrays;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FileServiceImpl implements FileService{
    @Override
    public String saveFile(String packageName, MultipartFile file) throws IOException {
        String id = UUID.randomUUID().toString();
        try {
            File output = new File("C:/Users/Администратор/СайтTiiamebb/resources/"+packageName+"_resources/" + id + ".webp");
//             Read the input image


            BufferedImage inputImage = ImageIO.read(file.getInputStream());
            ImagePlus imagePlus = new ImagePlus(file.getOriginalFilename(), inputImage);

            // Create a new BufferedImage with TYPE_3BYTE_BGR format
            BufferedImage outputImage = new BufferedImage(inputImage.getWidth(), inputImage.getHeight(), BufferedImage.TYPE_INT_ARGB);

            // Copy the input image to the output image
            outputImage.getGraphics().drawImage(inputImage, 0, 0, null);
            // Get a WebP ImageWriter
            ImageWriter writer = ImageIO.getImageWritersByMIMEType("image/webp").next();
            // Configure the WebP ImageWriteParam
            ImageWriteParam writeParam = writer.getDefaultWriteParam();
            writeParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            writeParam.setCompressionType("Lossy");
            if(file.getSize()/1024>400) {
                writeParam.setCompressionQuality(0.5f);
                System.out.println(0.5);
            }else {
                writeParam.setCompressionQuality(0.8f);
                System.out.println(0.7);
            }

            // Create an ImageOutputStream to write the output image
            ImageOutputStream imageOutputStream = ImageIO.createImageOutputStream(output);

            // Set the output format to WebP
            writer.setOutput(imageOutputStream);

            // Write the input image as WebP
            writer.write(null, new javax.imageio.IIOImage(outputImage, null, null), writeParam);

            // Clean up resources
            imageOutputStream.close();
            writer.dispose();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
            return packageName + "_resources/" + id;
        }


    @SneakyThrows
    @Override
    public void getFile(String url, HttpServletResponse httpServletResponse){
        InputStream inputStream = new FileInputStream("C:/Users/Администратор/СайтTiiamebb/resources/"+url+".webp");
        inputStream.transferTo(httpServletResponse.getOutputStream());
        inputStream.close();
        httpServletResponse.getOutputStream().close();
    }
    @Override
    public ResponseEntity<String > deleteFiles(String url){
        Path path = Paths.get("C:/Users/Администратор/СайтTiiamebb/resources/"+url+".webp");
        try {
            Files.delete(path);
        } catch (IOException e) {
            return ResponseEntity.ok().body("File not found!");
        }
        return ResponseEntity.ok().body("Successfully deleted!");
    }
}
