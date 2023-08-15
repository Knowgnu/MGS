package com.icia.mgs.dao;

import com.icia.mgs.dto.ProductDTO;
import com.icia.mgs.dto.SaleDTO;
import com.icia.mgs.dto.SearchDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SaleDAO {

    List<SaleDTO> saleList();

    List<SaleDTO> sSearchList(SearchDTO search);

    SaleDTO sView(int sNum);

    SaleDTO likeup2(int sNum);

    SaleDTO likedown2(int sNum);

    SaleDTO likecheck(int sNum);

    List<SaleDTO> saleList2(int type);
}
