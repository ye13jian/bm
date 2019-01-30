package com.eluoen.bm.modular.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.eluoen.bm.modular.system.model.Member;
import com.eluoen.bm.modular.system.model.QrcodeAuth;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 二维码认证 Mapper 接口
 * </p>
 *
 * @author eluoen123
 * @since 2018-12-08
 */
public interface QrcodeAuthMapper extends BaseMapper<QrcodeAuth> {

    List<Map<String,Object>> selectList_U(@Param("userId") Integer userId, @Param("condition") String condition);

}
