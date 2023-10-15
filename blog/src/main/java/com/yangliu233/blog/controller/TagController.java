package com.yangliu233.blog.controller;

import java.util.List;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import lombok.RequiredArgsConstructor;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.*;
import cn.dev33.satoken.annotation.SaCheckPermission;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.annotation.Validated;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import com.ruoyi.common.core.validate.QueryGroup;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.yangliu233.blog.domain.vo.TagVo;
import com.yangliu233.blog.domain.bo.TagBo;
import com.yangliu233.blog.service.ITagService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 博客标签
 *
 * @author ruoyi
 * @date 2023-10-15
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/blog/tag")
public class TagController extends BaseController {

    private final ITagService iTagService;

    /**
     * 查询博客标签列表
     */
    @SaCheckPermission("blog:tag:list")
    @GetMapping("/list")
    public TableDataInfo<TagVo> list(TagBo bo, PageQuery pageQuery) {
        return iTagService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出博客标签列表
     */
    @SaCheckPermission("blog:tag:export")
    @Log(title = "博客标签", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(TagBo bo, HttpServletResponse response) {
        List<TagVo> list = iTagService.queryList(bo);
        ExcelUtil.exportExcel(list, "博客标签", TagVo.class, response);
    }

    /**
     * 获取博客标签详细信息
     *
     * @param tagId 主键
     */
    @SaCheckPermission("blog:tag:query")
    @GetMapping("/{tagId}")
    public R<TagVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable String tagId) {
        return R.ok(iTagService.queryById(tagId));
    }

    /**
     * 新增博客标签
     */
    @SaCheckPermission("blog:tag:add")
    @Log(title = "博客标签", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody TagBo bo) {
        return toAjax(iTagService.insertByBo(bo));
    }

    /**
     * 修改博客标签
     */
    @SaCheckPermission("blog:tag:edit")
    @Log(title = "博客标签", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody TagBo bo) {
        return toAjax(iTagService.updateByBo(bo));
    }

    /**
     * 删除博客标签
     *
     * @param tagIds 主键串
     */
    @SaCheckPermission("blog:tag:remove")
    @Log(title = "博客标签", businessType = BusinessType.DELETE)
    @DeleteMapping("/{tagIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable String[] tagIds) {
        return toAjax(iTagService.deleteWithValidByIds(Arrays.asList(tagIds), true));
    }
}
