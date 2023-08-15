package com.icia.mgs.dto;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("search")
public class SearchDTO {
    private String category;
    private String keyword;
}
