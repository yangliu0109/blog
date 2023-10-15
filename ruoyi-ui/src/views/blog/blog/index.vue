<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="文章标题" prop="title">
        <el-input
          v-model="queryParams.title"
          placeholder="请输入文章标题"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
        <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="el-icon-plus"
          size="mini"
          @click="handleAdd"
          v-hasPermi="['blog:blog:add']"
        >新增
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="el-icon-edit"
          size="mini"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['blog:blog:edit']"
        >修改
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="el-icon-delete"
          size="mini"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['blog:blog:remove']"
        >删除
        </el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['blog:blog:export']"
        >导出
        </el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="blogList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center"/>
      <el-table-column label="" align="center" prop="blogId" v-if="false"/>
      <el-table-column label="文章标题" align="center" prop="title"/>
<!--      <el-table-column label="文章类型内容" align="center" prop="content"/>-->
      <el-table-column label="文章分类" align="center" prop="categoryId"/>
<!--      <el-table-column label="文字数量(单位：字)" align="center" prop="contentNum"/>-->
<!--      <el-table-column label="文字占用的大小(单位：byte)" align="center" prop="contentSize"/>-->
<!--      <el-table-column label="文章封面(url)" align="center" prop="cover"/>-->
      <el-table-column label="标签" align="center" prop="tagNames"/>
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click="handleUpdate(scope.row)"
            v-hasPermi="['blog:blog:edit']"
          >修改
          </el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click="handleDelete(scope.row)"
            v-hasPermi="['blog:blog:remove']"
          >删除
          </el-button>
        </template>
      </el-table-column>
    </el-table>

    <pagination
      v-show="total>0"
      :total="total"
      :page.sync="queryParams.pageNum"
      :limit.sync="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改博客管理对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="100%" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="文章标题" prop="title">
          <el-input v-model="form.title" placeholder="请输入文章标题"/>
        </el-form-item>
        <el-form-item label="文章类型内容">
          <editor @on-change="textChange" v-model="form.content" :min-height="192"/>
        </el-form-item>
        <!--        <el-form-item label="文章分类id" prop="categoryId">-->
        <!--          <el-input v-model="form.categoryId" placeholder="请输入文章分类id" />-->
        <!--        </el-form-item>-->
        <el-form-item label="文章分类" prop="categoryId">
          <el-select v-model="form.categoryId" placeholder="请选择">
            <el-option
              v-for="item in blogCategoryList"
              :key="item.categoryId"
              :label="item.name"
              :value="item.categoryId">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="标签" prop="tagIds">
<!--          <el-input v-model="form.tagId" placeholder="请输入标签id，使用逗号分隔"/>-->
          <el-select v-model="form.tagIds" multiple placeholder="请输入标签">
            <el-option
              v-for="item in tagList"
              :key="item.tagId"
              :label="item.tagName"
              :value="item.tagId">
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="文章封面" prop="cover">
          <el-input v-model="form.cover" type="textarea" placeholder="请输入内容"/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button :loading="buttonLoading" type="primary" @click="submitForm">确 定</el-button>
        <el-button @click="cancel">取 消</el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import {listBlog, getBlog, delBlog, addBlog, updateBlog} from "@/api/blog/blog";
import {listCategory, getCategory, delCategory, addCategory, updateCategory} from "@/api/blog/category";
import { listTag, getTag, delTag, addTag, updateTag } from "@/api/blog/tag";
export default {
  name: "Blog",
  data() {
    return {
      contentText: '',
      tagList: [],
      blogCategoryList: [],
      // 按钮loading
      buttonLoading: false,
      // 遮罩层
      loading: true,
      // 选中数组
      ids: [],
      // 非单个禁用
      single: true,
      // 非多个禁用
      multiple: true,
      // 显示搜索条件
      showSearch: true,
      // 总条数
      total: 0,
      // 博客管理表格数据
      blogList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        title: undefined,
        content: undefined,
        categoryId: undefined,
        contentNum: undefined,
        contentSize: undefined,
        cover: undefined,
        tagId: undefined,
      },
      // 表单参数
      form: {},
      // 表单校验
      rules: {
        blogId: [
          {required: true, message: "不能为空", trigger: "blur"}
        ],
        title: [
          {required: true, message: "文章标题不能为空", trigger: "blur"}
        ],
        content: [
          {required: true, message: "文章类型内容不能为空", trigger: "blur"}
        ],
        categoryId: [
          {required: true, message: "文章分类id不能为空", trigger: "blur"}
        ],
        contentNum: [
          {required: true, message: "文字数量(单位：字)不能为空", trigger: "blur"}
        ],
        contentSize: [
          {required: true, message: "文字占用的大小(单位：byte)不能为空", trigger: "blur"}
        ],
        tagIds: [
          {required: true, message: "标签id，使用逗号分隔不能为空", trigger: "blur"}
        ],
      }
    };
  },
  created() {
    this.getList();
    listCategory({
      pageNum: 1,
      pageSize: 99999
    }).then(res => {
      this.blogCategoryList = res.rows;
    })
    listTag({
      pageNum: 1,
      pageSize: 99999
    }).then(res => {
      this.tagList = res.rows;
    })
  },
  methods: {
    textChange(data) {
      this.contentText = data.text
    },
    /** 查询博客管理列表 */
    getList() {
      this.loading = true;
      listBlog(this.queryParams).then(response => {
        this.blogList = response.rows;
        this.total = response.total;
        this.loading = false;
      });
    },
    // 取消按钮
    cancel() {
      this.open = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        blogId: undefined,
        title: undefined,
        content: undefined,
        categoryId: undefined,
        contentNum: undefined,
        contentSize: undefined,
        cover: undefined,
        tagIds: undefined,
        delFlag: undefined,
        createBy: undefined,
        createTime: undefined,
        updateBy: undefined,
        updateTime: undefined
      };
      this.resetForm("form");
    },
    /** 搜索按钮操作 */
    handleQuery() {
      this.queryParams.pageNum = 1;
      this.getList();
    },
    /** 重置按钮操作 */
    resetQuery() {
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.blogId)
      this.single = selection.length !== 1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加博客管理";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.loading = true;
      this.reset();
      const blogId = row.blogId || this.ids
      getBlog(blogId).then(response => {
        this.loading = false;
        this.form = response.data;
        this.open = true;
        this.title = "修改博客管理";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.buttonLoading = true;
          delete this.form.createTime
          this.form.contentText = this.contentText
          if (this.form.blogId != null) {
            updateBlog(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            }).finally(() => {
              this.buttonLoading = false;
            });
          } else {
            addBlog(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            }).finally(() => {
              this.buttonLoading = false;
            });
          }
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const blogIds = row.blogId || this.ids;
      this.$modal.confirm('是否确认删除博客管理编号为"' + blogIds + '"的数据项？').then(() => {
        this.loading = true;
        return delBlog(blogIds);
      }).then(() => {
        this.loading = false;
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {
      }).finally(() => {
        this.loading = false;
      });
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('blog/blog/export', {
        ...this.queryParams
      }, `blog_${new Date().getTime()}.xlsx`)
    }
  }
};
</script>
