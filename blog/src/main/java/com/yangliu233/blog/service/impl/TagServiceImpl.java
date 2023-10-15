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
import com.yangliu233.blog.domain.bo.TagBo;
import com.yangliu233.blog.domain.vo.TagVo;
import com.yangliu233.blog.domain.Tag;
import com.yangliu233.blog.mapper.TagMapper;
import com.yangliu233.blog.service.ITagService;

import java.util.List;
import java.util.Map;
import java.util.Collection;

/**
 * 博客标签Service业务层处理
 *
 * @author ruoyi
 * @date 2023-10-15
 */
@RequiredArgsConstructor
@Service
public class TagServiceImpl implements ITagService {

    private final TagMapper baseMapper;

    /**
     * 查询博客标签
     */
    @Override
    public TagVo queryById(String tagId){
        return baseMapper.selectVoById(tagId);
    }

    /**
     * 查询博客标签列表
     */
    @Override
    public TableDataInfo<TagVo> queryPageList(TagBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Tag> lqw = buildQueryWrapper(bo);
        Page<TagVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        return TableDataInfo.build(result);
    }

    /**
     * 查询博客标签列表
     */
    @Override
    public List<TagVo> queryList(TagBo bo) {
        LambdaQueryWrapper<Tag> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Tag> buildQueryWrapper(TagBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<Tag> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getTagName()), Tag::getTagName, bo.getTagName());
        return lqw;
    }

    /**
     * 新增博客标签
     */
    @Override
    public Boolean insertByBo(TagBo bo) {
        Tag add = BeanUtil.toBean(bo, Tag.class);
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setTagId(add.getTagId());
        }
        return flag;
    }

    /**
     * 修改博客标签
     */
    @Override
    public Boolean updateByBo(TagBo bo) {
        Tag update = BeanUtil.toBean(bo, Tag.class);
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Tag entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除博客标签
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<String> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
