package com.ruoyi.system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.ruoyi.system.domain.bo.TestBo;
import com.ruoyi.system.domain.vo.TestVo;
import com.ruoyi.system.domain.Test;
import com.ruoyi.system.mapper.TestMapper;
import com.ruoyi.system.service.ITestService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 测试Service业务层处理
 *
 * @author ruoyi
 * @date 2023-09-12
 */
@RequiredArgsConstructor
@Service
public class TestServiceImpl implements ITestService {

    private final TestMapper baseMapper;

    /**
     * 查询测试
     */
    @Override
    public TestVo queryById(String aaa){
        return baseMapper.selectVoById(aaa);
    }

    /**
     * 查询测试列表
     */
    @Override
    public TableDataInfo<TestVo> queryPageList(TestBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Test> lqw = buildQueryWrapper(bo);
        Page<TestVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询测试列表
     */
    @Override
    public List<TestVo> queryList(TestBo bo) {
        LambdaQueryWrapper<Test> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Test> buildQueryWrapper(TestBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<Test> lqw = Wrappers.lambdaQuery();
        lqw.eq(StringUtils.isNotBlank(bo.getBbb()), Test::getBbb, bo.getBbb());
        return lqw;
    }

    /**
     * 新增测试
     */
    @Override
    public Boolean insertByBo(TestBo bo) {
        Test add = BeanUtil.toBean(bo, Test.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setAaa(add.getAaa());
        }
        return flag;
    }

    /**
     * 修改测试
     */
    @Override
    public Boolean updateByBo(TestBo bo) {
        Test update = BeanUtil.toBean(bo, Test.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Test entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除测试
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<String> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
