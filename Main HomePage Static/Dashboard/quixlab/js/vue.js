new Vue({
  el: '#app',
  data: {

    all: true;
    gay: true;
    socialIssue: true;
    environment: true;
    poverty: true;
  },

  methods: {

    socialIssues() {
      this.socialIssue = true;
      this.gay = false;
      this.environment = false;
    },

    gay() {
      this.socialIssue = false;
      this.gay = true;
      this.environment = false;

    },

    environment() {
      this.socialIssue = false;
      this.gay = false;
      this.environment = true;

    },

    showAll() {

      this.socialIssue = true;
      this.gay = true;
      this.environment = true;
    },

    poverty() {
      this.socialIssue = false;
      this.gay = false;
      this.environment = false;
      this.poverty = true;
      
    }





  }




});
