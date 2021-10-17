<template>
  <div class="tag-index-entry-button">
    <button v-bind:value="tagIndexElement.tag_Id">{{ tagIndexElement.tagName }} ( {{tagIndexElement.count}} )</button>
  </div>
</template>
 
<script>
export default {
  name: 'tag-index-entry',
  props: ["tagIndexElement"],
};
</script>
 
<style>
.tag-index-entry-button {
  font-family: "Courier New", Courier, monospace;
}
</style>