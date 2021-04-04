<template>
  <div class="tag-container">

      <tag
        v-for="tag in tagArray"
        v-bind:key="tag.tag_id"
        v-bind:tagElement="tag"
      />
    </div>

</template>
 
<script>
import Tag from "./Tag.vue";
export default {
  name: "tag-list",
  props: ["tagArray"],
  components: {
    Tag,
  },
};
</script>
 
<style>
.tag-container {
  display: flex;
  justify-content: space-evenly;
  flex-wrap: wrap;
}
</style>