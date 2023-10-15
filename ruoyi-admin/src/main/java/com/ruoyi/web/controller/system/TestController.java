package com.ruoyi.web.controller.system;

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
import com.ruoyi.system.domain.vo.TestVo;
import com.ruoyi.system.domain.bo.TestBo;
import com.ruoyi.system.service.ITestService;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 测试
 *
 * @author ruoyi
 * @date 2023-09-12
 */
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/system/test")
public class TestController extends BaseController {

    private final ITestService iTestService;

    /**
     * 查询测试列表
     */
    @SaCheckPermission("system:test:list")
    @GetMapping("/list")
    public TableDataInfo<TestVo> list(TestBo bo, PageQuery pageQuery) {
        return iTestService.queryPageList(bo, pageQuery);
    }

    /**
     * 导出测试列表
     */
    @SaCheckPermission("system:test:export")
    @Log(title = "测试", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(TestBo bo, HttpServletResponse response) {
        List<TestVo> list = iTestService.queryList(bo);
        ExcelUtil.exportExcel(list, "测试", TestVo.class, response);
    }

    /**
     * 获取测试详细信息
     *
     * @param aaa 主键
     */
    @SaCheckPermission("system:test:query")
    @GetMapping("/{aaa}")
    public R<TestVo> getInfo(@NotNull(message = "主键不能为空")
                                     @PathVariable String aaa) {
        return R.ok(iTestService.queryById(aaa));
    }

    /**
     * 新增测试
     */
    @SaCheckPermission("system:test:add")
    @Log(title = "测试", businessType = BusinessType.INSERT)
    @RepeatSubmit()
    @PostMapping()
    public R<Void> add(@Validated(AddGroup.class) @RequestBody TestBo bo) {
        return toAjax(iTestService.insertByBo(bo));
    }

    /**
     * 修改测试
     */
    @SaCheckPermission("system:test:edit")
    @Log(title = "测试", businessType = BusinessType.UPDATE)
    @RepeatSubmit()
    @PutMapping()
    public R<Void> edit(@Validated(EditGroup.class) @RequestBody TestBo bo) {
        return toAjax(iTestService.updateByBo(bo));
    }

    /**
     * 删除测试
     *
     * @param aaas 主键串
     */
    @SaCheckPermission("system:test:remove")
    @Log(title = "测试", businessType = BusinessType.DELETE)
    @DeleteMapping("/{aaas}")
    public R<Void> remove(@NotEmpty(message = "主键不能为空")
                          @PathVariable String[] aaas) {
        return toAjax(iTestService.deleteWithValidByIds(Arrays.asList(aaas), true));
    }
}
