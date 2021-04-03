import axios from 'axios';

const http = axios.create({
    baseURL: "http://localhost:8080"
})

export default{
    listUserPhotos(){
        return http.get('/users/photos');
    },
    addCurrentUserPhoto(newPhotoJSON){
        return http.post('/users/photos', newPhotoJSON)
    }
}