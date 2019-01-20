package com.eluoen.bm.modular.book.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.eluoen.bm.modular.system.dao.BookCatalogMapper;
import com.eluoen.bm.modular.system.model.BookCatalog;
import com.eluoen.bm.modular.book.service.IBookCatalogService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 书籍目录 服务实现类
 * </p>
 *
 * @author eluoen
 * @since 2018-11-24
 */
@Service
public class BookCatalogServiceImpl extends ServiceImpl<BookCatalogMapper, BookCatalog> implements IBookCatalogService {

    @Override
    public List<Map<String, Object>> selectList(String condition, Integer bookid) {
        return this.baseMapper.selectList(condition,bookid);
    }

    @Override
    public List<Map<String, Object>> selectQrList(Integer bookid) {
        return this.baseMapper.selectQrList(bookid);
    }
}
