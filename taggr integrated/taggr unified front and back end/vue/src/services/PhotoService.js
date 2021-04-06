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
    }

}