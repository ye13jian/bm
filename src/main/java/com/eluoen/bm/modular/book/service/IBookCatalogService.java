package com.eluoen.bm.modular.book.service;

import com.baomidou.mybatisplus.service.IService;
import com.eluoen.bm.modular.system.model.BookCatalog;
import com.eluoen.bm.modular.system.model.BookCatalog;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 书籍目录 服务类
 * </p>
 *
 * @author stylefeng123
 * @since 2018-11-24
 */
public interface IBookCatalogService extends IService<BookCatalog> {

    /**
     * 根据条件查询书本目录列表
     */
    List<Map<String, Object>> selectList(@Param("condition") String condition, @Param("bookid") Integer bookid);

    /**
     * 查询一本书所有的信息用于生成二维码
     */
    List<Map<String, Object>> selectQrList(@Param("bookid") Integer bookid);

}
