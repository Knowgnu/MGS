package com.icia.mgs.dao;

import com.icia.mgs.dto.ProductDTO;
import com.icia.mgs.dto.ProductEntity;
import com.icia.mgs.dto.SearchDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductDAO {
    List<ProductDTO> pSearchList(SearchDTO search);

    List<ProductDTO> productList();

    ProductDTO pView(int pNum);

}
