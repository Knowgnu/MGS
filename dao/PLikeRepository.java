package com.icia.mgs.dao;

import com.icia.mgs.dto.PLikeDTO;
import com.icia.mgs.dto.PLikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PLikeRepository extends JpaRepository <PLikeEntity, Integer> {

    List<PLikeEntity> findBymId(String mId);
}
