# taggr
taggr is a side project I'm creating during full-stack Java bootcamp: a tool to help organize web-based photos, even
across different websites and applications.
<br>
<br>
taggr is a way to bookmark photos on services such as Instagram, PhotoBucket, and flickr. A user enters photos into
taggr with the photos' URLs, a given description for each photo, and tags for each photo.  The user can then do keyword
searches on all of their saved photos by tags or descriptions. This allows the user to integrate photo collections from 
across multiple applications; since taggr uses publicly available URLs as the keys in its database, it's also possible
to tag and save photos from other publicly available photo accounts.
<br>
<br>
Currently taggr is a command-line only program, since that's where my skill level is at. Over time, I'm going to iterate
and grow taggr as my skills increase. Anticipated updates include:
<br>
<ul>
<li> <strike>a fully built out nested menu at the command-line level</strike> <b>implemented 1-31-2021!</b></li>
<li> <strike>a <b>better</b> nested menu at the command-line level</strike> <b>implemented 2-7-2021!</b></li>
<li> <strike>reducing code redundancy through submethods</strike> <b>implemented 2-13-2021!</b> </li>
<li> moving taggr to using a DAO pattern to work with a SQL database, solving the persistence issue and allowing multiple users. the 2-20-21 update is the beginning of this move. New classes have been added, as well as a script for a SQL database. Need to finish figuring out all of my SQL logic, then write my Spring JDBC methods and integration tests, and then implement user state.</li>
<li> security, allowing a user's account to be password protected. Planning on implementing Argon2 for hashing/salting</li>
<li> allowing password reset for users via email confirmation</li>
<li> moving taggr from a command-line interface to a web-based interface</li>
<li> creating gallery options based on using web scraping techniques</li>
<li> an auto-import function, allowing you to add all of the photos from a your Instagram account into your photoSet 
and setting the photos' initial descriptions to the descriptions in Instagram, and setting their initial tags to all 
hashtags in the Instagram descriptions.</li>
<li> and much more, tbd as I learn more skills!</li>
</ul>

<br>
<br>
taggr is driven by two primary Java Classes: Photos and Users.  
<br>
<br>
A Photo instance stores a picture's <b>url</b> and user-entered
<b>description</b> as Strings, and the photo's user-entered <b>tags</b> as a Set of Strings.
<br>
<br>
A User instance has a String <b>userName</b>, a Map <b>photoSet</b> that stores Photos with the key being a String of the photo's <b>url</b>
and the value being the <b>Photo</b> itself, a Set called <b>userTags</b> that stores all <b>tags</b> as a Set of Strings,
and a Map called <b>tagsIndex</b> that stores all of the user's <b>tags</b>, with the key being a String tag and the value
being the number of the user's Photos that have the associated <b>tag</b>. 
<br>
<br>
When a user adds a Photo, the Photo is added
to the user's <b>photoSet</b> and the Photo's tags are added to the User's <b>userTags</b> Set. If the Photo's tag is unique,
the tag is automatically added to the <b>tagsIndex</b> with a value of 1. If the Photo's <b>tag</b> already exists in the
user's <b>tags</b> Set and <b>tagsIndex</b>, the <b>tag</b>'s associated value in <b>tagsIndex</b> is increased by one.
<br>
<br>
When a user deletes a <b>tag</b> from one of their Photos, or a user deletes a Photo, the relevant tags are deleted from <b>tagsIndex</b>
and <b>userTags</b> if the tag had a value of one in <b>tagsIndex</b>. If the <b>tag</b> to be deleted had been on more
than one Photo, it remains in the <b>userTags</b> Set and its value is reduced by one in <b>tagsIndex</b>. 
<br>
<br>
taggr also has an Application class that runs the actual program, and a MenuAndCLI class that drives the logic of the
Application's menu and input/output methods.
<br>
<br>
the TaggrScratch class is just a repository for old code, in case I want to poke at it again.

