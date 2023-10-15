package com.ruoyi.system.service;

import com.ruoyi.system.domain.Test;
import com.ruoyi.system.domain.vo.TestVo;
import com.ruoyi.system.domain.bo.TestBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 测试Service接口
 *
 * @author ruoyi
 * @date 2023-09-12
 */
public interface ITestService {

    /**
     * 查询测试
     */
    TestVo queryById(String aaa);

    /**
     * 查询测试列表
     */
    TableDataInfo<TestVo> queryPageList(TestBo bo, PageQuery pageQuery);

    /**
     * 查询测试列表
     */
    List<TestVo> queryList(TestBo bo);

    /**
     * 新增测试
     */
    Boolean insertByBo(TestBo bo);

    /**
     * 修改测试
     */
    Boolean updateByBo(TestBo bo);

    /**
     * 校验并批量删除测试信息
     */
    Boolean deleteWithValidByIds(Collection<String> ids, Boolean isValid);
}
