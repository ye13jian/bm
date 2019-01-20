package com.eluoen.bm.multi.service.impl;

import cn.stylefeng.roses.core.mutidatasource.annotion.DataSource;
import com.eluoen.bm.multi.entity.Test;
import com.eluoen.bm.multi.mapper.TestMapper;
import com.eluoen.bm.multi.service.TestService;
import com.eluoen.bm.core.common.constant.DatasourceEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author fengshuonan
 * @since 2018-07-10
 */
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestMapper testMapper;

    @Override
    @DataSource(name = DatasourceEnum.DATA_SOURCE_BIZ)
    @Transactional
    public void testBiz() {
        Test test = new Test();
        test.setBbb("bizTest");
        testMapper.insert(test);
    }

    @Override
    @DataSource(name = DatasourceEnum.DATA_SOURCE_GUNS)
    @Transactional
    public void testGuns() {
        Test test = new Test();
        test.setBbb("gunsTest");
        testMapper.insert(test);
    }
}
