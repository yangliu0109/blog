<template>
    <div class="continar">
        <div class="search-box">
            <input placeholder="请输入关键字..." class="search-input" type="text" v-model="title" name="" id="">
            <button @click="search()" class="search-btn">搜索</button>
        </div>
    </div>
</template>

<script>
export default {
  components: {},
  props: {},
  data() {
    return {
        title: ""
    };
  },
  watch: {},
  computed: {},
  methods: {
    search() {
        this.$router.push(`/search?title=${this.title}`)
    }
  },
  created() {},
  mounted() {}
};
</script>
<style scoped>
    .search-box {
        text-align: center;
        width: 500px;
        height: 350px;
        margin: auto;
        position: absolute;
        top: 0;
        left: 0;
        right: 0;
        bottom: 0;

        display: flex;
        align-items: center;
        justify-content: center;
    }

    .search-btn {
        padding: 9px;
    }

    .search-input {
        padding: 10px;
        width: 340px;
    }
</style>
