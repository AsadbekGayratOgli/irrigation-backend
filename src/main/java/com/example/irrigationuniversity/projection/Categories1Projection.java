package com.example.irrigationuniversity.projection;







import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.UUID;

public interface Categories1Projection {

    UUID getId();
    String getName();
    String getLan();
    @Value("#{@categories_2Repository.findAllByCategories1AndLan(target.lan,target.id)}")
    List<Categories2Projection> getInnerCategories();
}
