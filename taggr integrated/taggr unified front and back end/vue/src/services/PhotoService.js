import axios from 'axios';

/**  const http = axios.create({
    baseURL: "http://localhost:8080"
}) */

export default{
   
    listUserPhotos(){
        return axios.get('/users/photos');
    },
    addCurrentUserPhoto(newPhotoJSON){
        return axios.post('/users/photos', newPhotoJSON);
    },
    deletePhoto(photoId){
        return axios.delete(`/users/photos/${photoId}`);
    },
    keywordSearchUserPhotos(keyword){
        return axios.get(`/users/photos/desc/${keyword}`);
    },
    tagFilterUserPhotos(tagName){
        return axios.get(`users/photos/tags/${tagName}`);
    },
    getUserTagIndex(){
        return axios.get('/users/tagindex');
    },
    getPhotoToEditInfo(photo_Id){
        return axios.get(`users/photos/${photo_Id}`);
    },
    deleteTagFromPhoto(photo_Id, tag_Id){
        return axios.delete(`users/photos/${photo_Id}/${tag_Id}`);
    },
    updatePhotoDescription(photo_Id, newDescription){
        return axios.patch(`users/photos/${photo_Id}/${newDescription}`);
    }

}