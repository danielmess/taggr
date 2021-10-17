<template>
  <div class="tag-index-container">

      <tag-index-entry
        v-for="tagIndexElement in $store.state.currentUserTagIndex"
        v-bind:key="tagIndexElement.tag_Id"
        v-bind:tagIndexElement="tagIndexElement"
      />
    </div>

</template>
 
<script>

import TagIndexEntry from "./TagIndexEntry.vue";
export default {
  name: "tag-index",
  components: {
    TagIndexEntry,
  },
};
</script>
 
<style>
.tag-index-container {
  display: flex;
  justify-content: space-evenly;
  flex-wrap: wrap;
}
</style>