package com.yangliu233.blog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.yangliu233.blog.domain.bo.CategoryBo;
import com.yangliu233.blog.domain.vo.CategoryVo;
import com.yangliu233.blog.domain.Category;
import com.yangliu233.blog.mapper.CategoryMapper;
import com.yangliu233.blog.service.ICategoryService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 博客分类Service业务层处理
 *
 * @author ruoyi
 * @date 2023-10-15
 */
@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements ICategoryService {

    private final CategoryMapper baseMapper;

    /**
     * 查询博客分类
     */
    @Override
    public CategoryVo queryById(String categoryId){
        return baseMapper.selectVoById(categoryId);
    }

    /**
     * 查询博客分类列表
     */
    @Override
    public TableDataInfo<CategoryVo> queryPageList(CategoryBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Category> lqw = buildQueryWrapper(bo);
        Page<CategoryVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询博客分类列表
     */
    @Override
    public List<CategoryVo> queryList(CategoryBo bo) {
        LambdaQueryWrapper<Category> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Category> buildQueryWrapper(CategoryBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<Category> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getName()), Category::getName, bo.getName());
        return lqw;
    }

    /**
     * 新增博客分类
     */
    @Override
    public Boolean insertByBo(CategoryBo bo) {
        Category add = BeanUtil.toBean(bo, Category.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setCategoryId(add.getCategoryId());
        }
        return flag;
    }

    /**
     * 修改博客分类
     */
    @Override
    public Boolean updateByBo(CategoryBo bo) {
        Category update = BeanUtil.toBean(bo, Category.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Category entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除博客分类
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<String> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
