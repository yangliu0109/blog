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
import com.yangliu233.blog.domain.vo.BlogVo;
import com.yangliu233.blog.domain.bo.BlogBo;
import com.yangliu233.blog.service.IBlogService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 博客管理
 *
 * @author ruoyi
 * @date 2023-10-15
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/blog/blog")
public class BlogController extends BaseController {

    private final IBlogService iBlogService;

    /**
     * 查询博客管理列表
     */
    @SaCheckPermission("blog:blog:list")
    @GetMapping("/list")
    public TableDataInfo<BlogVo> list(BlogBo bo, PageQuery pageQuery) {
        return iBlogService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出博客管理列表
     */
    @SaCheckPermission("blog:blog:export")
    @Log(title = "博客管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(BlogBo bo, HttpServletResponse response) {
        List<BlogVo> list = iBlogService.queryList(bo);
        ExcelUtil.exportExcel(list, "博客管理", BlogVo.class, response);
    }

    /**
     * 获取博客管理详细信息
     *
     * @param blogId 主键
     */
    @SaCheckPermission("blog:blog:query")
    @GetMapping("/{blogId}")
    public R<BlogVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable String blogId) {
        BlogVo blogVo = iBlogService.queryById(blogId);

        return R.ok(blogVo);
    }

    /**
     * 新增博客管理
     */
    @SaCheckPermission("blog:blog:add")
    @Log(title = "博客管理", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody BlogBo bo) {
        return toAjax(iBlogService.insertByBo(bo));
    }

    /**
     * 修改博客管理
     */
    @SaCheckPermission("blog:blog:edit")
    @Log(title = "博客管理", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody BlogBo bo) {
        return toAjax(iBlogService.updateByBo(bo));
    }

    /**
     * 删除博客管理
     *
     * @param blogIds 主键串
     */
    @SaCheckPermission("blog:blog:remove")
    @Log(title = "博客管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{blogIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable String[] blogIds) {
        return toAjax(iBlogService.deleteWithValidByIds(Arrays.asList(blogIds), true));
    }
}
