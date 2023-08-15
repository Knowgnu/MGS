package com.icia.mgs.dao;

import com.icia.mgs.dto.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<ProductEntity, Integer> {
    ProductEntity findBypNum(int pNum);
}
