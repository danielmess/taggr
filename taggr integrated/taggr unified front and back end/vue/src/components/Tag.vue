<template>
  <div class="tag-button">
    <button v-bind:value="tagElement.tag_Id">{{ tagElement.tag_Name }}</button>
  </div>
</template>
 
<script>
export default {
  name: 'tag',
  props: ["tagElement"],
};
</script>
 
<style>
.tag-button {
  font-family: "Courier New", Courier, monospace;
}
</style>