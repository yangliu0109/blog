package com.yangliu233.blog.service;

import com.yangliu233.blog.domain.Blog;
import com.yangliu233.blog.domain.vo.BlogVo;
import com.yangliu233.blog.domain.bo.BlogBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 博客管理Service接口
 *
 * @author ruoyi
 * @date 2023-10-15
 */
public interface IBlogService {

    /**
     * 查询博客管理
     */
    BlogVo queryById(String blogId);

    /**
     * 查询博客管理列表
     */
    TableDataInfo<BlogVo> queryPageList(BlogBo bo, PageQuery pageQuery);

    /**
     * 查询博客管理列表
     */
    List<BlogVo> queryList(BlogBo bo);

    /**
     * 新增博客管理
     */
    Boolean insertByBo(BlogBo bo);

    /**
     * 修改博客管理
     */
    Boolean updateByBo(BlogBo bo);

    /**
     * 校验并批量删除博客管理信息
     */
    Boolean deleteWithValidByIds(Collection<String> ids, Boolean isValid);
}
