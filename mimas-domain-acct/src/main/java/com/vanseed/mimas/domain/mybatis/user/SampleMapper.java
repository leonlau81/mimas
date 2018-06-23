package com.vanseed.mimas.domain.mybatis.user;

import java.math.BigDecimal;

import org.apache.ibatis.annotations.*;

import com.github.pagehelper.Page;
import com.vanseed.mimas.domain.model.user.Sample;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface SampleMapper {

    @Select("SELECT * FROM TB_SAMPLE WHERE NAME = #{name}")
    Sample findByName(@Param("name") String name);
    
    @Select("SELECT * FROM TB_SAMPLE WHERE STATUS = #{status}")
    Page<Sample> findByStatusPaging(@Param("status") Integer status);

    @Insert("INSERT INTO TB_SAMPLE(name, amount, status, apply_date, create_time) VALUES(#{name},#{amount},1,CURDATE(),NOW())")
    int insert(@Param("name") String name, @Param("amount") BigDecimal amount);
    
    Sample findSampleMapping(@Param("name") String name, @Param("status")int status);

}