package com.example.irrigationuniversity.payload.requestDTO;

import com.example.irrigationuniversity.entity.Categories1Names;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.util.UUID;
@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Category1NamesDTO {
   private UUID id;
   private Categories1Names categories1Names;
}
