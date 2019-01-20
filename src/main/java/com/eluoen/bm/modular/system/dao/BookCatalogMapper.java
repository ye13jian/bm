package com.eluoen.bm.modular.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.eluoen.bm.modular.system.model.BookCatalog;
import com.eluoen.bm.modular.system.model.BookCatalog;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 书籍目录 Mapper 接口
 * </p>
 *
 * @author stylefeng123
 * @since 2018-11-24
 */
public interface BookCatalogMapper extends BaseMapper<BookCatalog> {

    /**
     * 根据条件查询书本目录列表
     */
    List<Map<String, Object>> selectList(@Param("condition") String condition, @Param("bookid") Integer bookid);

    /**
     * 查询一本书所有的信息用于生成二维码
     */
    List<Map<String, Object>> selectQrList(@Param("bookid") Integer bookid);
}
