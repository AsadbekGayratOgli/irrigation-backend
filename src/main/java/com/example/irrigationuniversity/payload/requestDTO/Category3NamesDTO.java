package com.example.irrigationuniversity.payload.requestDTO;


import com.example.irrigationuniversity.entity.Categories3Names;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category3NamesDTO {
    private UUID id;

    private Categories3Names categories3Names;
}
