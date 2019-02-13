package com.eluoen.bm.modular.book.service;

import com.baomidou.mybatisplus.service.IService;
import com.eluoen.bm.core.common.node.ZTreeNode;
import com.eluoen.bm.modular.system.model.Book;
import com.eluoen.bm.modular.system.model.Gift;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 书籍表 服务类
 * </p>
 *
 * @author eluoen123
 * @since 2018-11-17
 */
public interface IBookService extends IService<Book> {

    List<Gift> selectList_U(Integer userId, String condition);

    /**
     * 获取ztree的节点列表
     */
    List<ZTreeNode> tree(Integer userId);

}
