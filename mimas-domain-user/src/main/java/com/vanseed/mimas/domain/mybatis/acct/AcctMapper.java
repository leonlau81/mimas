package com.vanseed.mimas.domain.mybatis.acct;

import com.github.pagehelper.Page;
import com.vanseed.mimas.domain.model.user.Sample;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Mapper
@Repository
public interface AcctMapper {

    @Select("SELECT * FROM TB_ACCT_INFO WHERE ACCT_NO = #{acctNo}")
    Sample findByNo(@Param("acctNo") String acctNo);

    @Select("SELECT * FROM TB_ACCT_INFO WHERE STATUS = #{status}")
    Page<Sample> findByStatusPaging(@Param("status") Integer status);

    @Insert("INSERT INTO TB_ACCT_INFO(user_id, acct_type, acct_no, acct_name, status ) VALUES(#{userId},#{acctType},#{acctNo},#{acctName},1)")
    int insert(@Param("userId") Long userId, @Param("acctType") Integer acctType, @Param("acctNo") String acctNo, @Param("acctName") String acctName);

    Sample findByAcctNoAndStatus(@Param("acctNo") String acctNo, @Param("status") int status);

}