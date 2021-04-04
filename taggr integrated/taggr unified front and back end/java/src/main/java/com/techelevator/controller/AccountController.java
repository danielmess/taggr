package com.techelevator.controller;

import com.techelevator.dao.PhotoDAO;
import com.techelevator.dao.TagDAO;
import com.techelevator.dao.UserDAO;
import com.techelevator.exceptions.PhotoNotCreatedException;
import com.techelevator.model.AddPhotoJSON;
import com.techelevator.model.Photo;
import com.techelevator.model.Tag;
import com.techelevator.model.User;
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

    private Long getCurrentUserID(Principal principal){
        User theUser = userDAO.findByUsername(principal.getName());
        return theUser.getId();
    }
}
