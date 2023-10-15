package com.yangliu233.blog.service;

import com.yangliu233.blog.domain.Tag;
import com.yangliu233.blog.domain.vo.TagVo;
import com.yangliu233.blog.domain.bo.TagBo;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;

import java.util.Collection;
import java.util.List;

/**
 * 博客标签Service接口
 *
 * @author ruoyi
 * @date 2023-10-15
 */
public interface ITagService {

    /**
     * 查询博客标签
     */
    TagVo queryById(String tagId);

    /**
     * 查询博客标签列表
     */
    TableDataInfo<TagVo> queryPageList(TagBo bo, PageQuery pageQuery);

    /**
     * 查询博客标签列表
     */
    List<TagVo> queryList(TagBo bo);

    /**
     * 新增博客标签
     */
    Boolean insertByBo(TagBo bo);

    /**
     * 修改博客标签
     */
    Boolean updateByBo(TagBo bo);

    /**
     * 校验并批量删除博客标签信息
     */
    Boolean deleteWithValidByIds(Collection<String> ids, Boolean isValid);
}
