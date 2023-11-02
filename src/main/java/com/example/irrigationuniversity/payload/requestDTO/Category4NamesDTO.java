package com.example.irrigationuniversity.payload.requestDTO;


import com.example.irrigationuniversity.entity.Categories4Names;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Category4NamesDTO {
    private UUID id;

    private Categories4Names categories4Names;
}
