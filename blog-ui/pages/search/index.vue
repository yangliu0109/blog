<template>
    <div class="continar">
        <div class="box">
            <div class="left-box">
                <div class="header">
                    <div class="logo"></div>
                </div>
                <div class="title"><a href="/">首页</a></div>
                <div class="search-result">
                    <div class="blog-continar">
                        <div @click="toDetail(blog)" v-for="blog in blogList" class="blog-box">
                            <div>
                                <img v-if="blog.cover" class="blog_img"
                                     src="https://upload.wikimedia.org/wikipedia/commons/thumb/d/d1/Autism-stacking-cans_2nd_edit.jpg/150px-Autism-stacking-cans_2nd_edit.jpg"
                                     alt="">
                            </div>
                            <div class="blog-text-box">
                                <div class="blog-title"><a href="" style="color: #000">{{ blog.title }}</a></div>
                                <div class="blog-sumary" v-text="blog.contentText"/>
                                <div class="blog-createTime">{{ blog.contentSize }} KB ({{ blog.contentNum }} words) -
                                    {{ blog.createTime }}
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <a-pagination @change="changePage" @showSizeChange="showSizeChange" size="small" v-model="current"
                          :total="total" show-size-changer show-quick-jumper/>
        </div>
    </div>
</template>

<script>
import {listBlog} from "~/api/blog/blog";

export default {
    components: {},
    props: {},
    data() {
        return {
            param: {
                pageNum: this.current,
                pageSize: this.total,
                title: this.title
            },
            title: '',
            blog: {},
            current: 1,
            total: 10,
            blogList: []
        };
    },
    watch: {},
    computed: {},
    methods: {
        getBlogList(){
            listBlog(this.param).then(res => {
                this.blogList = res.rows;
                this.total = res.total;
            })
        },
        toDetail(blog) {
            this.blog = blog
            this.$router.push(`/search/detail?blogId=${this.blog.blogId}`)
        },
        changePage(page, pageSize) {
            this.param = {
                pageNum: page,
                pageSize: pageSize,
                title: this.title
            }
            this.getBlogList()
        },
        showSizeChange(current, size) {
            this.param = {
                pageNum: current,
                pageSize: size,
                title: this.title
            }
            this.getBlogList()
        }
    },
    created() {
        this.title = this.$route.query.title
        this.param.title = this.$route.query.title
        this.getBlogList()
    },
    mounted() {
    }
};
</script>
<style scoped>
.continar {
    display: flex;
    justify-content: center;
    background-color: #F8F9FA;
    min-height: 100vh;
}

.search-result {
    min-height: 800px;
}

.box {
    background-color: #FFFFFF;
    max-width: 1600px;
    min-width: 1600px;
}

.menu {
    display: flex;
    justify-content: space-around;
    flex-direction: column;
    width: 22px;
    height: 22px;
    padding: 8px;
}

.menu:hover {
    background-color: #F8F9FA;
}

.menu:active {
    background-color: #54595d;
}

.blog_img {
    width: 92px;
    height: 92px;
    object-fit: cover;
    border: 1px solid #c8ccd1;
}

.blog-continar {
}

.blog-box {
    display: flex;
    margin-top: 20px;
    margin-bottom: 20px;
}

.left-box {
    max-width: 860px;
}

.blog-title {
    font-size: 18px;
}

.blog-sumary {
    font-size: 14px;
    line-height: 23px;

    display: -webkit-box;
    -webkit-box-orient: vertical;
    -webkit-line-clamp: 2;
    overflow: hidden;
}

.blog-createTime {
    font-size: 13px;
    color: #54595d;
}

.blog-text-box {
    display: flex;
    flex-direction: column;
    justify-content: center;
    margin-left: 10px;
}
</style>
