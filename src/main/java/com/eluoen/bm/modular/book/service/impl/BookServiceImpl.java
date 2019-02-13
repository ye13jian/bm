package com.eluoen.bm.modular.book.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.eluoen.bm.modular.book.service.IBookService;
import com.eluoen.bm.modular.system.dao.BookMapper;
import com.eluoen.bm.modular.system.model.Book;
import com.eluoen.bm.core.common.node.ZTreeNode;
import com.eluoen.bm.modular.book.service.IBookService;
import com.eluoen.bm.modular.system.dao.BookMapper;
import com.eluoen.bm.modular.system.model.Book;
import com.eluoen.bm.modular.system.model.Gift;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 书籍表 服务实现类
 * </p>
 *
 * @author eluoen123
 * @since 2018-11-17
 */
@Service
public class BookServiceImpl extends ServiceImpl<BookMapper, Book> implements IBookService {

    @Resource
    private BookMapper bookMapper;

    @Override
    public List<Gift> selectList_U(Integer userId, String condition) {
        return this.bookMapper.selectList_U(userId,condition);
    }

    @Override
    public List<ZTreeNode> tree(Integer userId) {
        return this.baseMapper.tree(userId);
    }
}
