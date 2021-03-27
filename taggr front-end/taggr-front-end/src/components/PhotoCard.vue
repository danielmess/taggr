<template>
<div class="card">
    <br>
    <iframe class="photo-iframe" v-bind:src="instagramEmbed(photo.url)"></iframe>
    <a v-bind:href="photo.url">Original</a>
    <p class="photo-description">{{ photo.description }}</p>
    <tag-list />
    </div>
</template>

<script>
import TagList from './TagList.Vue'
export default {
    name: "photo-card",
    props: ["photo"],
    components: {
        TagList
    },
    methods:{
        instagramEmbed(url){
            if(url.includes('instagram')){
                url = url + 'embed';
                return url;
            } else {
                return url;
            }

        }
    }
}
</script>

<style>
.card {
  border: 2px solid black;
  border-radius: 10px;
  width: 350px;
  height: 450px;
  margin: 20px;
}

.photo-iframe{
    width: 300 px;
    max-width: 100%;
    height: 338 px;
    max-height:100%;
}

</style>