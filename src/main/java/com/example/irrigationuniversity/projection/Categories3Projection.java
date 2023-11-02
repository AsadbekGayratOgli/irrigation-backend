package com.example.irrigationuniversity.projection;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.UUID;

public interface Categories3Projection {

    UUID getId();
    String getName();
    String getLan();
    @Value("#{@categories_4Repository.findAllByCategories3AndLan(target.lan,target.id)}")
    List<CategoriesProjection> getInnerCategories();
}
