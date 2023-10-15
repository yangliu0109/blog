package com.yangliu233.blog.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.StrUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.PageQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.yangliu233.blog.domain.vo.TagVo;
import com.yangliu233.blog.mapper.CategoryMapper;
import com.yangliu233.blog.mapper.TagMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.yangliu233.blog.domain.bo.BlogBo;
import com.yangliu233.blog.domain.vo.BlogVo;
import com.yangliu233.blog.domain.Blog;
import com.yangliu233.blog.mapper.BlogMapper;
import com.yangliu233.blog.service.IBlogService;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Collection;
import java.util.stream.Collectors;

/**
 * 博客管理Service业务层处理
 *
 * @author ruoyi
 * @date 2023-10-15
 */
@RequiredArgsConstructor
@Service
public class BlogServiceImpl implements IBlogService {

    private final BlogMapper baseMapper;
    private final CategoryMapper categoryMapper;
    private final TagMapper tagMapper;

    /**
     * 查询博客管理
     */
    @Override
    public BlogVo queryById(String blogId){
        Blog blog = baseMapper.selectById(blogId);
        BlogVo blogVo = BeanUtil.copyProperties(blog, BlogVo.class);
        blogVo.setTagIds(Arrays.asList(blog.getTagId().split(",")));
        return blogVo;
    }

    /**
     * 查询博客管理列表
     */
    @Override
    public TableDataInfo<BlogVo> queryPageList(BlogBo bo, PageQuery pageQuery) {
        LambdaQueryWrapper<Blog> lqw = buildQueryWrapper(bo);
        Page<BlogVo> result = baseMapper.selectVoPage(pageQuery.build(), lqw);
        for (BlogVo record : result.getRecords()) {
            record.setCategoryId(categoryMapper.selectVoById(record.getCategoryId()).getName());
            // 封装tag
            List<String> tagIds = Arrays.asList(record.getTagId().split(","));
            List<TagVo> tags = tagMapper.selectVoBatchIds(tagIds);
            String tagNames = tags.stream().map(TagVo::getTagName).collect(Collectors.joining(","));
            record.setTagNames(tagNames);
        }
        return TableDataInfo.build(result);
    }

    /**
     * 查询博客管理列表
     */
    @Override
    public List<BlogVo> queryList(BlogBo bo) {
        LambdaQueryWrapper<Blog> lqw = buildQueryWrapper(bo);
        return baseMapper.selectVoList(lqw);
    }

    private LambdaQueryWrapper<Blog> buildQueryWrapper(BlogBo bo) {
        Map<String, Object> params = bo.getParams();
        LambdaQueryWrapper<Blog> lqw = Wrappers.lambdaQuery();
        lqw.like(StringUtils.isNotBlank(bo.getTitle()), Blog::getTitle, bo.getTitle()).or().like(StringUtils.isNotBlank(bo.getTitle()), Blog::getContent, bo.getTitle());
//        lqw.eq(StringUtils.isNotBlank(bo.getContent()), Blog::getContent, bo.getContent());
//        lqw.eq(StringUtils.isNotBlank(bo.getCategoryId()), Blog::getCategoryId, bo.getCategoryId());
//        lqw.eq(StringUtils.isNotBlank(bo.getContentNum()), Blog::getContentNum, bo.getContentNum());
//        lqw.eq(StringUtils.isNotBlank(bo.getContentSize()), Blog::getContentSize, bo.getContentSize());
//        lqw.eq(StringUtils.isNotBlank(bo.getCover()), Blog::getCover, bo.getCover());
//        lqw.eq(StringUtils.isNotBlank(bo.getTagId()), Blog::getTagId, bo.getTagId());
        return lqw;
    }

    /**
     * 新增博客管理
     */
    @Override
    public Boolean insertByBo(BlogBo bo) {
        bo.setContentText(bo.getContentText().replace("\n", ""));
        bo.setContentNum(StrUtil.toString(bo.getContentText().length()));
        bo.setContentSize(StrUtil.toString(bo.getContentText().getBytes().length));
        Blog add = BeanUtil.toBean(bo, Blog.class);
        add.setTagId(String.join(",", bo.getTagIds()));
        validEntityBeforeSave(add);
        boolean flag = baseMapper.insert(add) > 0;
        if (flag) {
            bo.setBlogId(add.getBlogId());
        }
        return flag;
    }

    /**
     * 修改博客管理
     */
    @Override
    public Boolean updateByBo(BlogBo bo) {
        bo.setContentText(bo.getContentText().replace("\n", ""));
        bo.setContentNum(StrUtil.toString(bo.getContentText().length()));
        bo.setContentSize(StrUtil.toString(bo.getContentText().getBytes().length));
        Blog update = BeanUtil.toBean(bo, Blog.class);
        update.setTagId(String.join(",", bo.getTagIds()));
        validEntityBeforeSave(update);
        return baseMapper.updateById(update) > 0;
    }

    /**
     * 保存前的数据校验
     */
    private void validEntityBeforeSave(Blog entity){
        //TODO 做一些数据校验,如唯一约束
    }

    /**
     * 批量删除博客管理
     */
    @Override
    public Boolean deleteWithValidByIds(Collection<String> ids, Boolean isValid) {
        if(isValid){
            //TODO 做一些业务上的校验,判断是否需要校验
        }
        return baseMapper.deleteBatchIds(ids) > 0;
    }
}
