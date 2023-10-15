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
import com.yangliu233.blog.domain.vo.CategoryVo;
import com.yangliu233.blog.domain.bo.CategoryBo;
import com.yangliu233.blog.service.ICategoryService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 博客分类
 *
 * @author ruoyi
 * @date 2023-10-15
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/blog/category")
public class CategoryController extends BaseController {

    private final ICategoryService iCategoryService;

    /**
     * 查询博客分类列表
     */
    @SaCheckPermission("blog:category:list")
    @GetMapping("/list")
    public TableDataInfo<CategoryVo> list(CategoryBo bo, PageQuery pageQuery) {
        return iCategoryService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出博客分类列表
     */
    @SaCheckPermission("blog:category:export")
    @Log(title = "博客分类", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(CategoryBo bo, HttpServletResponse response) {
        List<CategoryVo> list = iCategoryService.queryList(bo);
        ExcelUtil.exportExcel(list, "博客分类", CategoryVo.class, response);
    }

    /**
     * 获取博客分类详细信息
     *
     * @param categoryId 主键
     */
    @SaCheckPermission("blog:category:query")
    @GetMapping("/{categoryId}")
    public R<CategoryVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable String categoryId) {
        return R.ok(iCategoryService.queryById(categoryId));
    }

    /**
     * 新增博客分类
     */
    @SaCheckPermission("blog:category:add")
    @Log(title = "博客分类", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody CategoryBo bo) {
        return toAjax(iCategoryService.insertByBo(bo));
    }

    /**
     * 修改博客分类
     */
    @SaCheckPermission("blog:category:edit")
    @Log(title = "博客分类", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody CategoryBo bo) {
        return toAjax(iCategoryService.updateByBo(bo));
    }

    /**
     * 删除博客分类
     *
     * @param categoryIds 主键串
     */
    @SaCheckPermission("blog:category:remove")
    @Log(title = "博客分类", businessType = BusinessType.DELETE)
    @DeleteMapping("/{categoryIds}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable String[] categoryIds) {
        return toAjax(iCategoryService.deleteWithValidByIds(Arrays.asList(categoryIds), true));
    }
}
