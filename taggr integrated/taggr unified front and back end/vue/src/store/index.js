import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */
const currentToken = localStorage.getItem('token')
const currentUser = JSON.parse(localStorage.getItem('user'));

if(currentToken != null) {
  axios.defaults.headers.common["Authorization"] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  state: {
    token: currentToken || '',
    user: currentUser || {},
    currentUserPhotos: [],
    currentUserPhotosFiltered: [],
    currentUserTagIndex: [    {
      "tagName": "chocolate",
      "tag_Id": 2,
      "user_Id": 3,
      "count": 4
  },
  {
      "tagName": "cookies",
      "tag_Id": 3,
      "user_Id": 3,
      "count": 1
  },
  {
      "tagName": "healthy-ish",
      "tag_Id": 4,
      "user_Id": 3,
      "count": 1
  },
  {
      "tagName": "egg",
      "tag_Id": 5,
      "user_Id": 3,
      "count": 1
  },
  {
      "tagName": "light",
      "tag_Id": 6,
      "user_Id": 3,
      "count": 1
  },
  {
      "tagName": "Easter",
      "tag_Id": 7,
      "user_Id": 3,
      "count": 2
  }]
  },
  mutations: {
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
    },
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem('user',JSON.stringify(user));
    },
    LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      state.token = '';
      state.user = {};
      axios.defaults.headers.common = {};
    },
    SET_USER_PHOTOS(state, data){
      state.currentUserPhotos = data;
    },
    FILTER_PHOTOS(state, data){
      state.currentUserPhotosFiltered = data;
    },
    DELETE_USER_PHOTO(state, id){
      state.currentUserPhotos.splice(
        state.currentUserPhotos.findIndex(photo => photo.photo_Id ===id),
      )

    }
  }
})
