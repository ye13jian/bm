package com.eluoen.bm.modular.market.service;

import com.baomidou.mybatisplus.service.IService;
import com.eluoen.bm.modular.system.model.Member;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author eluoen123
 * @since 2018-11-17
 */
public interface IMemberService extends IService<Member> {

    List<Member> selectList_U(Integer userId, String condition);
}
