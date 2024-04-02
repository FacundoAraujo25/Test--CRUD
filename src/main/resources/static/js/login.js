Vue.createApp({

    data() {
        return {
            email: "",
            password: "",
            user: [],

            name: "",
            lastName: "",
            newEmail: "",
            newPassword: "",
        }
    },

    created() {

    },

    methods: {

        logIn() {
            axios.post('/api/login', `email=${this.email}&password=${this.password}`, { headers: { 'content-type': 'application/x-www-form-urlencoded' } })
                .then(response => {
                    console.log("Succesful login")
                    Swal.fire({
                        title: "Succesful login",
                        // text: "Sesion iniciada correctamente, ya puedes hacer pedidos en nuestra app",
                        icon: "success",
                        confirmButtonColor: '#12A098',
                        confirmButtonText: "Welcome to Fifth Impact",
                        width: "40%",
                    })
                        .then(response => {
                            if (this.email.includes('@fifthimpact.com')) {
                                window.location.href = '/web/index.html'
                            } else if (this.email.includes('@teachersFI.com')) {
                                window.location.href = '/web/teachersView/teachersView.html'
                            }
                            else {
                                window.location.href = '/web/studentsView/studentsView.html'
                            }
                        })
                })
                .catch(response => {
                    console.log("error")
                    Swal.fire({
                        title: "Log In Error",
                        text: "Please check your information and try again",
                        icon: "error",
                        confirmButtonText: "Try Again",
                        width: "30%",
                    })
                })
        },

    },

    computed: {
    }



}).mount('#app')