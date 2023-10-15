package com.yangliu233.blog.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.annotation.RepeatSubmit;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.PageQuery;
import com.ruoyi.common.core.domain.R;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.validate.AddGroup;
import com.ruoyi.common.core.validate.EditGroup;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.yangliu233.blog.domain.bo.BlogBo;
import com.yangliu233.blog.domain.vo.BlogVo;
import com.yangliu233.blog.service.IBlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;

/**
 * 博客管理
 *
 * @author ruoyi
 * @date 2023-10-15
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/web/blog")
public class WebBlogController extends BaseController {

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

}
