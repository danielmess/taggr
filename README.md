# taggr


My main side project during my bootcamp has been taggr, which I conceived of as a way to organize photos on web-based apps like Instagram, Flickr, Photobucket, and more.

taggr began its life as a solely Java-based application with a basic command line interface and no persistent state. Over time, I've added to its capabilities as I've been learning more in boot camp. 

The current iteration of taggr has a front end built in Vue, a back end built in Java, a PostgreSQL database to store user information and photo information, and Spring Boot to tie together the database with the back end. I inherited some basic code to handle authorization and security and a basic start to my database scripts, but the rest of the project is my code. 

To give taggr a try, take the following steps: 
1) Download the project! 
2) In your terminal navigate to the database folder at taggr/tagger integrated/taggr unified front and back end/java/database . Put in ```./create.sh``` to create the starting database. 
3) Then open the folder at taggr/tagger integrated/taggr unified front and back end/java/ in your IDE and run the application to get the backend server going.
4) Then open the folder at /taggr/taggr integrated/taggr unified front and back end/vue in Visual Studio Code.
5) Run ```npm install``` in the terminal for the project in VSC. Once the Node elves have finished installing the many, many fiddly bits, run ```npm run serve``` in the project's terminal to fire up the front end. Ctrl+click on the link that pops up, and get to tagging!

Please note: you must have the backend server running *before* running the front end server.

Please also note: taggr is currently optimized for displaying Instagram-based photos. I'd like to be able to do image-scraping eventually, but right now photos are being displayed via iframe elements. Instagram works well for this, but Flicker straight-up blocks embedding in other websites.

You can see how photos look when displayed for an example user by logging in with "danmess" as the user and "password" as the password. This example user has five photos, 18 tags, and 22 tag-photo relations stored in the database.

Please note that the full-stack version of the app is in the 'taggr integrated' folder. What's under the 'taggr back-end prototype' and 'taggr front-end prototype' folders is the code I built while I was still learning in camp. It's still a little awkward, folks!

Taggr is still a work in progress! There's a lot of functionality I had in the pure Java-and-Spring version that I haven't implemented into the full app yet, so there's a lot of methods floating around in my Java code for that. Some of that I'll adapt for modern usage, and some of it I'll trash.

Here are some cool things I'm hoping to implement in the next few weeks, in no particular order:

1) Updating a photo's description
2) Deleting a tag from a photo
3) Adding a new tag to a photo
4) Using a keyword search to filter the logged-in user's photos
5) Clicking on a photo's tag buttons to filter the logged-in user's photos
6) Error handling on the backend side to handle adding a new photo that has no tags given
7) Better styling in general.
8) much much better JDoc-style documentation of my methods and functions
9) Porting the backend of taggr to be hosted by heroku and the frontend to be hosted by netlify to make it easier for people to use!
10) Code cleanup!!

<del>10) populating the database with an example user so you can see how taggr looks and functions right off the bat without adding photos yourself</del> **implemented 4-6-21!**


Thanks go to: 

- David Pfaltzgraff-Carlson, who when presented with my potential side project possibilities of the seed of the idea for taggr or a maze game, said firmly "Go with the photo organization one. That one can grow as you do."
- Katie Dwyer and Beth Campbell, who have contributed invaluable technical advice on particularly sticky problems
- Kyle Kingsbury and Ralph Mess, whose emotional support and patience in letting me ramble about design ideas have been essential for me surviving my boot camp.

I'm excited to find out what people think. Let's make something cool together!
