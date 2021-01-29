package taggr;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Photo {
    private String url;
    private String description;
    private Set<String> tags = new HashSet();

    public Photo(String url, String description, Set tags) {
        this.url = url;
        this.description = description;
        this.tags = tags;
    }

// set setters - no setter for tags; instead we do a specific method to Add tags to the tags Set.

    public void setUrl(String url) {
        this.url = url;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // set getters
    public String getUrl() {
        return this.url;
    }

    public String getDescription() {
        return this.description;
    }

    public void getTags() {
        Iterator var1 = this.tags.iterator();

        while(var1.hasNext()) {
            String tag = (String)var1.next();
            System.out.print(tag + ", ");
        }

    }

    //set methods

    public void setTags(List<String> tagList) {
        for (String tag: tagList) {
            tags.add(tag);
        }
    }


}
