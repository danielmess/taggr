import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    photos: [
      {
        id: 1,
        url: "https://www.instagram.com/p/CMSwIJinMMH/",
        description: "a delicious quick tortellini dish",
        tags: [
          {
            id: 1,
            tagName: "tortellini"
          },
          {
            id: 2,
            tagName: "quick"
          },
          {
            id: 3,
            tagName: "peas"
          },
          {
            id: 4,
            tagName: "pasta"
          },
          {
            id: 5,
            tagName: "proscuitto"
          }
        ]
      },
      {
        id: 2,
        url: "https://www.instagram.com/p/CMSXA_LHRZR/",
        description: "a tasty whole lemon tart",
        tags: [
          {
            id: 6,
            tagName: "baking"
          },
          {
            id: 7,
            tagName: "tart"
          },
          {
            id: 8,
            tagName: "lemon"
          }
        ]
      },
      {
        id: 3,
        url: "https://www.instagram.com/p/CMN8P23pmvj/",
        description: "delicious-looking peanut-butter cookies",
        tags: [
          {
            id: 6,
            tagName: "baking"
          },
          {
            id: 9,
            tagName: "cookies"
          },
          {
            id: 11,
            tagName: "peanut butter"
          }
        ]
      },
      {
        id: 4,
        url: "https://www.flickr.com/photos/smitten/50982280698/",
        description: "summery, spicy, lime-y margaritas",
        tags: [
          {
            id: 12,
            tagName: "alcohol"
          },
          {
            id: 13,
            tagName: "tequila"
          },
          {
            id: 14,
            tagName: "spice"
          },
          {
            id: 15,
            tagName: "margarita"
          },
          {
            id: 16,
            tagName: "lime"
          },
          {
            id: 17,
            tagName: "salt"
          }
        ]
      },
      {
        id: 8,
        url: "https://www.flickr.com/photos/smitten/50955000182/in/photostream/",
        description: "delicious marbled cheesecake hamantaschen",
        tags: [
          {
            id: 30,
            tagName: "marbled"
          },
          {
            id: 6,
            tagName: "baking"
          },
          {
            id: 32,
            tagName: "cheesecake"
          }
        ]
      },
    ]
  },
  mutations: {
  },
  actions: {
  },
  modules: {
  }
})
