package com.techelevator.controller;

import com.techelevator.dao.PhotoDAO;
import com.techelevator.dao.TagDAO;
import com.techelevator.dao.UserDAO;
import com.techelevator.exceptions.PhotoNotCreatedException;
import com.techelevator.model.*;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.nio.file.attribute.UserPrincipalNotFoundException;
import java.security.Principal;
import java.util.List;
import java.util.Set;

import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
public class AccountController {
    private UserDAO userDAO;
    private PhotoDAO photoDAO;
    private TagDAO tagDAO;

    public AccountController(UserDAO userDAO, PhotoDAO photoDAO, TagDAO tagDAO){
        this.userDAO = userDAO;
        this.photoDAO = photoDAO;
        this.tagDAO = tagDAO;
    }

    @RequestMapping(path = "/users/photos", method = RequestMethod.GET)
    public List<Photo> findPhotosForLoggedInUser(Principal principal) throws UserPrincipalNotFoundException {
        if (principal != null){
            Long user_id = getCurrentUserID(principal);
            return photoDAO.listAllUserPhotosFromSQL(userDAO.getUserById(user_id));
        } else {
            return null;
        }
    }

    @RequestMapping(path = "/users/photos/{photoId}", method = RequestMethod.GET)
    public Photo findPhotoById(Principal principal, @PathVariable long photoId) throws UserPrincipalNotFoundException {
        if (principal != null){
            Long user_id = getCurrentUserID(principal);
            return photoDAO.retrieveUserPhotoFromPhotoID(photoId, userDAO.getUserById(user_id));
        } else {
            return null;
        }
    }

    @RequestMapping(path = "/users/photos/tags/{keyword}", method = RequestMethod.GET)
    public List<Photo> findPhotosByTag(Principal principal, @PathVariable String keyword) throws UserPrincipalNotFoundException {
        if (principal != null){
            Long user_id = getCurrentUserID(principal);
            User user = userDAO.getUserById(user_id);
            return photoDAO.listUserPhotosBySearchTagSQL(keyword, user);
        } else {
            return null;
        }
    }

    @RequestMapping(path = "/users/photos/desc/{keyword}", method = RequestMethod.GET)
    public List<Photo> findPhotosByKeyword(Principal principal, @PathVariable String keyword) throws UserPrincipalNotFoundException {
        if (principal != null){
            Long user_id = getCurrentUserID(principal);
            User user = userDAO.getUserById(user_id);
            return photoDAO.listUserPhotosBySearchDescSQL(keyword, user);
        } else {
            return null;
        }
    }

    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/users/photos", method = RequestMethod.POST)
    public AddPhotoJSON addPhotoForLoggedInUser(@RequestBody AddPhotoJSON addPhotoJSON, Principal principal) throws PhotoNotCreatedException {
        if (principal != null){
            Long user_id = getCurrentUserID(principal);
            User user = userDAO.getUserById(user_id);
            Set<Tag> tagsSet = tagDAO.createTagsSetFromCSV(addPhotoJSON.getTagsAsCSV(), user);
          photoDAO.createNewPhotoAndAddToUserSQL(addPhotoJSON.getUrl(), addPhotoJSON.getDescription(), tagsSet, user );
        return addPhotoJSON;
        } else {
            return null;
        }
    }

    @RequestMapping(path = "/users/tags", method = RequestMethod.GET)
    public Set<Tag> getTagsForLoggedInUser(Principal principal){
        if (principal != null){
            Long user_id = getCurrentUserID(principal);
            User user = userDAO.getUserById(user_id);
            return tagDAO.findUserTags(user.getId());
        } else {
            return null;
        }
    }

    @RequestMapping(path = "/users/tagindex", method = RequestMethod.GET)
    public List<TagIndexDTO> getTagIndexForLoggedInUser(Principal principal){
        if (principal != null){
            Long user_id = getCurrentUserID(principal);
            User user = userDAO.getUserById(user_id);
            return tagDAO.getUserTagIndex(user);
        } else {
            return null;
        }
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(path ="users/photos/{photoId}/{desc}", method = RequestMethod.PATCH)
    public void updateUserPhotoDesc(@PathVariable long photoId, Principal principal,
                                    @PathVariable String desc) {
        if (principal != null) {
            Long user_id = getCurrentUserID(principal);
            User user = userDAO.getUserById(user_id);
            photoDAO.updatePhotoNewDescriptionSQL(photoId, desc, user);
        }
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(path = "users/photos/{photoId}/tags/{newTag}", method = RequestMethod.PATCH)
    public void addTagToPhoto(@PathVariable long photoId, Principal principal, @PathVariable String newTag){
        if(principal != null){
            Long user_id = getCurrentUserID(principal);
            User user = userDAO.getUserById(user_id);
            String photoURL = photoDAO.retrieveUserPhotoURLFromPhotoId(photoId, user);
            photoDAO.addTagToPhotoSQL(photoURL, newTag, user);
        }
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(path = "/users/photos/{photoId}", method = RequestMethod.DELETE)
    public void deleteUserPhoto(@PathVariable long photoId , Principal principal) {
        if (principal != null) {
            Long user_id = getCurrentUserID(principal);
            User user = userDAO.getUserById(user_id);
            photoDAO.deletePhotoFromUserSQL(photoId, user);
        }
    }

    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(path = "/users/photos/{photoId}/{tagId}", method = RequestMethod.DELETE)
    public void deleteTagFromPhoto(@PathVariable long photoId, @PathVariable long tagId,
                                   Principal principal) {
        if (principal != null) {
            Long user_id = getCurrentUserID(principal);
            User user = userDAO.getUserById(user_id);
            String url = photoDAO.retrieveUserPhotoURLFromPhotoId(photoId, user);
            Tag theTag = tagDAO.findTagById(tagId, user);
            photoDAO.deleteTagFromPhotoSQL(url,theTag.getTag_Name(),user);
        }
    }

    private Long getCurrentUserID(Principal principal){
        User theUser = userDAO.findByUsername(principal.getName());
        return theUser.getId();
    }
}
