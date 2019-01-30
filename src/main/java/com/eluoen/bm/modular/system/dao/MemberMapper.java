package com.eluoen.bm.modular.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.eluoen.bm.modular.system.model.Gift;
import com.eluoen.bm.modular.system.model.Member;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author eluoen123
 * @since 2018-11-17
 */
public interface MemberMapper extends BaseMapper<Member> {

    List<Member> selectList_U(@Param("userId") Integer userId, @Param("condition") String condition);

}
