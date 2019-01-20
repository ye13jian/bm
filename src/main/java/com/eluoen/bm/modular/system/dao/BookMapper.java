package com.eluoen.bm.modular.system.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.eluoen.bm.core.common.node.ZTreeNode;
import com.eluoen.bm.modular.system.model.Book;

import java.util.List;

/**
 * <p>
 * 书籍表 Mapper 接口
 * </p>
 *
 * @author eluoen123
 * @since 2018-11-17
 */
public interface BookMapper extends BaseMapper<Book> {

    /**
     * 获取ztree的节点列表
     */
    List<ZTreeNode> tree();
}
