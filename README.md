# taggr
taggr is a side project I'm creating during full-stack Java bootcamp: a tool to help organize web-based photos, even
across different websites and applications.
</br>
</br>
taggr is a way to bookmark photos on services such as Instagram, PhotoBucket, and flickr. A user enters photos into
taggr with the photos' URLs, a given description for each photo, and tags for each photo.  The user can then do keyword
searches on all of their saved photos by tags or descriptions. This allows the user to integrate photo collections from 
across multiple applications; since taggr uses publicly available URLs as the keys in its database, it's also possible
to tag and save photos from other publicly available photo accounts.
</br>
</br>
Currently taggr is a command-line only program, since that's where my skill level is at. Over time, I'm going to iterate
and grow taggr as my skills increase. Anticipated updates include:
</br>
<ul>
<li> a fully built out nested menu at the command-line level</li>
<li> persistence, allowing the saving of multiple user profiles with photo info</li>
<li> security, allowing a user's account to be password protected</li>
<li> moving taggr from a command-line interface to a web-based interface</li>
<li> creating gallery options based on using scraping techniques</li>
<li> and much more!</li>
</ul>
</br>
</br>
taggr is driven by two Java Classes: Photos and Users.  
</br>
</br>
A Photo instance stores a picture's <b>url</b> and user-entered
<b>description</b> as Strings, and the photo's user-entered <b>tags</b> as a Set of Strings.
</br>
</br>
A User instance has a String <b>userName</b>, a Map <b>photoSet</b> that stores Photos with the key being a String of the photo's <b>url</b>
and the value being the <b>Photo</b> itself, a Set called <b>userTags</b> that stores all <b>tags</b> as a Set of Strings,
and a Map called <b>tagsIndex</b> that stores all of the user's <b>tags</b>, with the key being a String tag and the value
being the number of the user's Photos that have the associated <b>tag</b>. 
</br>
</br>
When a user adds a Photo, the Photo is added
to the user's <b>photoSet</b> and the Photo's tags are added to the User's <b>userTags</b> Set. If the Photo's tag is unique,
the tag is automatically added to the <b>tagsIndex</b> with a value of 1. If the Photo's <b>tag</b> already exists in the
user's <b>tags</b> Set and <b>tagsIndex</b>, the <b>tag</b>'s associated value in <b>tagsIndex</b> is increased by one.
</br>
</br>
When a user deletes a <b>tag</b> from one of their Photos, or a user deletes a Photo, the relevant tags are deleted from <b>tagsIndex</b>
and <b>userTags</b> if the tag had a value of one in <b>tagsIndex</b>. If the <b>tag</b> to be deleted had been on more
than one Photo, it remains in the <b>userTags</b> Set and its value is reduced by one in <b>tagsIndex</b>. 


