package com.example.irrigationuniversity.payload.requestDTO;

import com.example.irrigationuniversity.entity.Categories2Names;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category2NamesDTO {
    private UUID id;
    private Categories2Names categories2Names;
}
