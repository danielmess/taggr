<template>
  <div class="tag-button">
    <button>{{ tag.tagName }}</button>
  </div>
</template>
 
<script>
export default {
  name: "tag",
  props: ["tag"],
};
</script>
 
<style>
.tag-button {
  font-family: "Courier New", Courier, monospace;
}
</style>