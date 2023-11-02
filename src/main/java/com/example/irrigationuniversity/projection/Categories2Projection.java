package com.example.irrigationuniversity.projection;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.UUID;

public interface  Categories2Projection {

    UUID getId();
    String getName();
    String getLan();
    @Value("#{@categories_3Repository.findAllByCategories2AndLan(target.lan,target.id)}")
    List<Categories3Projection> getInnerCategories();
}
