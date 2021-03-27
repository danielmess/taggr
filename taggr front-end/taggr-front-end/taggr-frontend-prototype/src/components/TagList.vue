<template>
  <div class="tag-container">
    <tag
      v-for="tag in $store.state.photos.tags"
      v-bind:key="tag.id"
      v-bind:tag="tag"
    />
  </div>
</template>
 
<script>
import Tag from './Tag.vue';
export default {
  name: 'tag-list',
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