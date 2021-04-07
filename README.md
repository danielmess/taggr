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

I also need to put example data in for a typical user into taggr's database schema. I'm hoping to have that done by end of day Wednesday the 7th, boot camp capstone work permitting.

Taggr is still a work in progress! Here are some cool things I'm hoping to implement in the next few weeks:

1) Updating a photo's description
2) Deleting a tag from a photo
3) Adding a new tag to a photo
4) Using a keyword search to filter the logged-in user's photos
5) Clicking on a photo's tag buttons to filter the logged-in user's photos
6) Error handling on the backend side to handle adding a new photo that has no tags given
7) Better styling in general.

Thanks go to: 

- David Pfaltzgraff-Carlson, who when presented with the seed of the idea for taggr or a maze game, said firmly "Go with the photo organization one. That one can grow as you do."
- Katie Dwyer and Beth Campbell, who have contributed invaluable technical advice on particularly sticky problems
- Kyle Kingsbury and Ralph Mess, whose emotional support and patience in letting me ramble about design ideas have been essential for me surviving my boot camp.

I'm excited to find out what people think. Let's make something cool together!
