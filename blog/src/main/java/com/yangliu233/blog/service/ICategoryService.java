package com.yangliu233.blog.service;

import com.yangliu233.blog.domain.Category;
import com.yangliu233.blog.domain.vo.CategoryVo;
import com.yangliu233.blog.domain.bo.CategoryBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 博客分类Service接口
 *
 * @author ruoyi
 * @date 2023-10-15
 */
public interface ICategoryService {

    /**
     * 查询博客分类
     */
    CategoryVo queryById(String categoryId);

    /**
     * 查询博客分类列表
     */
    TableDataInfo<CategoryVo> queryPageList(CategoryBo bo, PageQuery pageQuery);

    /**
     * 查询博客分类列表
     */
    List<CategoryVo> queryList(CategoryBo bo);

    /**
     * 新增博客分类
     */
    Boolean insertByBo(CategoryBo bo);

    /**
     * 修改博客分类
     */
    Boolean updateByBo(CategoryBo bo);

    /**
     * 校验并批量删除博客分类信息
     */
    Boolean deleteWithValidByIds(Collection<String> ids, Boolean isValid);
}
