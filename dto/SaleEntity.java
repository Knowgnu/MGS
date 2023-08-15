package com.icia.mgs.dto;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Date;

@Data
@Entity
@Table(name = "SALE")
@SequenceGenerator(name = "SALE_SEQ_GENERATOR", sequenceName = "SALE_SEQ", allocationSize = 1)
public class SaleEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SALE_SEQ_GENERATOR")
    private int sNum;
    @Column
    private int sDiscount;
    @Column
    private int sdPrice;
    @Column
    private int sHit;
    @Column
    private int sCount;
    @Column
    @CreationTimestamp
    private Date sDate;
    @Column
    private String sfDate;
    @Column
    private int sType;
    @Column
    private int pNum;
    @Column
    private int likeCount;              // 좋아요 수

    public static SaleEntity toEntity(SaleDTO sale){
        SaleEntity entity = new SaleEntity();

        entity.setSNum(sale.getSNum());
        entity.setSDiscount(sale.getSDiscount());
        entity.setSdPrice(sale.getSdPrice());
        entity.setSHit(sale.getSHit());
        entity.setSCount(sale.getSCount());
        entity.setSDate(sale.getSDate());
        entity.setSfDate(sale.getSfDate());
        entity.setSType(sale.getSType());
        entity.setPNum(sale.getPNum());
        entity.setLikeCount(sale.getLikeCount());

        return entity;
    }

}
