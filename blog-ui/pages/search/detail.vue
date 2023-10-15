<template>
  <div class="continar">
    <div class="blog">
      <div class="title" v-text="blog.title"/>
      <div class="create-time" v-text="blog.createTime"></div>
      <div class="content" v-html="blog.content"></div>
    </div>
  </div>
</template>

<script>
import {getBlog} from "~/api/blog/blog";
export default {
  components: {},
  props: {},
  data() {
    return {
        blog:{}
    };
  },
  watch: {},
  computed: {},
  methods: {
    toDetail() {
      this.$router.push('/search/detail')
    }
  },
  created() {
      let blogId = this.$route.query.blogId
      getBlog(blogId).then(res => {
          this.blog = res.data;
      })
  },
  mounted() { }
};
</script>
<style scoped>
.continar {
  display: flex;
  justify-content: center;
  background-color: #F8F9FA;
  min-height: 100vh;
}

.blog {
  width: 800px;
}

.title {
  font-size: 34px;
  text-align: center;
  margin-top: 100px;
}

.create-time {
  text-align: right;
  margin-top: 10px;
}

.content {
  margin-top: 70px;
}
</style>
