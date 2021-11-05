<template>
  <div id="about">
    <h1>Welcome to <span class="logo">taggr</span></h1>
    <br>
    <div id="about-text">
    <p><span class="logo">taggr</span> is a full-stack app built with a Java/Spring backend and Postgres databse, and a Vue.js frontend.  
    Its backend is hosted via <a href="https://www.heroku.com/">Heroku</a> and its frontend is hosted via <a href="https://www.netlify.com/">Netlify</a>. <span class="logo">taggr</span> is a way to organize
    Instagram-hosted photos, allowing you to give each photo its own description and tags. You can then filter your saved photos by keyword searches of the descriptions, or by tag. There is also
    a tag index that lists out all of your saved tags along with a count of how many photos each tag is on.  <span class="logo">taggr</span> is a non-commercial side project built to practice the technical skills I learned in <a href="http://www.techelevator.com">my bootcamp</a>;
     it's built upon a project seed provided by my bootcamp that allowed for basic authorization. All of the Vue components besides "Login," "Logout," "Register," "App," and "Home" are entirely written by me, and the "App" and "Home" components were almost entirely unpopulated. 
     On the backend side, the security directory, authentication controller, and User and Application classes were prewritten; everything else is my code.
    </p>
    <br>
    <p>If you'd like to see an example account with some pre-populated photos and tags, you can log in with the user id of "danmess" and the password "password". Or feel free to make your own account!</p>
    <br>
    <p> Future directions for <span class="logo">taggr</span> could include:</p>
     <ul>
         <li>better Jdoc documentation on methods</li>
         <li>integration testing on the backend to make sure that APIs' CRUD operations work correctly against the database</li>
         <li>integration testing on the frontend using Cypress</li>
         <li>further styling refinements</li>
         <li>changing out <span class="logo">taggr</span>'s hashing from <a href="https://www.npmjs.com/package/bcrypt">bcrypt</a> to <a href="https://argon2.online/">Argon2</a></li>
         <li>using responsive design to make <span class="logo">taggr</span> a better experience on mobile.</li>
         </ul>
    <br>
    <p>If you'd like to see the code for <span class="logo">taggr</span>, please go to <a href="https://github.com/danielmess/taggr">my GitHub repository</a>. Thanks for stopping by!</p>


 </div>
  </div>
</template>

<script>


export default {
  
  name: "about"
//   components: {
    
//   },
//   data() {
//     return {

//       }
//   },
//   methods:{
// }
};
</script>

<style>
#about {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
  background-color: aliceblue;
}

#about-text{
    width: 500px;
    margin: auto;
    text-align: left;
}

html{
  background-color: aliceblue;
}

h1{
  text-align: center;
}

.logo{
  color: rgb(27, 27, 194);
  font-weight: bold;
}

</style>
