<template>
  <div class="tag-container">

      <edit-tag
        v-for="tag in tagArray"
        v-bind:key="tag.tag_Id"
        v-bind:tagElement="tag"
        
      />
    </div>

</template>
 
<script>
import EditTag from "./EditTag.vue";


export default {
  name: "edit-tag-list",
  props: ["tagArray"],
  components: {
    EditTag,
  }

  
};
</script>
 
<style>
.tag-container {
  display: flex;
  justify-content: space-evenly;
  flex-wrap: wrap;
}
</style>