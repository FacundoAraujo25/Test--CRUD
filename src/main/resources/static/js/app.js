const studentsUrl = "/api/students";
const teachersUrl = "/api/teachers";
const coursesUrl = "/api/courses"

const { createApp } = Vue;

const app = createApp({

    data() {
        return {
            message: "Hello",
            data: [],
            dataBackUp: [],
            teachers: [],
            courses: [],
            searchInput: "",
            selectCourse: [],
            courseSelected: "all",
            editCourseModal: {
                courseId: "",
                name: "",
                startDate: "",
                finishDate: "",
                show: false,
                create: false,
            },
            editUserModal: {
                firstName: "",
                lastName: "",
                email: "",
                password: "",
                hasCourses: false,
                show: false,
                create: false,
            }

        }
    },

    created() {
        this.getData()
    },

    methods: {
        getData() {
            if (document.title == "Students") {
                axios.get(studentsUrl)
                    .then(response => {
                        this.data = response.data;
                        this.dataBackUp = response.data;
                    })
                    .catch(error => {
                        console.log(error.message);
                    })

                axios.get(coursesUrl)
                    .then(response => {
                        this.selectCourse = response.data.map(course => course.name);
                    })
            }
            if (document.title == "Teachers") {
                axios.get(teachersUrl)
                    .then(response => {
                        this.data = response.data;
                        this.dataBackUp = response.data;
                    })
                axios.get(coursesUrl)
                    .then(response => {
                        this.selectCourse = response.data.map(course => course.name);
                    })
            }
            if (document.title == "Courses") {
                axios.get(coursesUrl)
                    .then(response => {
                        this.data = response.data;
                        this.dataBackUp = response.data;
                    })
            }
        },

        openModal(course = this.editCourseModal, create = false) {
            this.editCourseModal = course;
            this.editCourseModal.show = true;
            if (create) {
                this.editCourseModal.create = true;
            }
        },
        closeModal() {
            this.editCourseModal.show = false;
        },

        createCourse() {
            const { name, startDate, finishDate } = this.editCourseModal;
            axios.post(`/api/courses?name=${name}&startDate=${startDate}&finishDate=${finishDate}`)
                .then(() => {
                    this.editCourseModal.show = false;
                    this.getData();
                })
                .catch(error => {
                    console.log(error.message);
                })
        },
        updateCourse() {
            const { courseId, name, startDate, finishDate } = this.editCourseModal;
            axios.patch(`/api/courses/${courseId}?name=${name}&startDate=${startDate}&finishDate=${finishDate}`)
                .then(() => {
                    this.editCourseModal.show = false;
                    this.getData();
                })
                .catch(error => {
                    console.log(error.message);
                })
        },
        deleteCourse(id) {
            axios.delete(`/api/courses/${id}`)
                .then(() => {
                    this.getData();
                })
                .catch(error => {
                    console.log(error);
                })

        },

        openModalUser(user = this.editUserModal, create = false) {
            this.editUserModal = user;
            this.editUserModal.show = true;
            if (create) {
                this.editUserModal.create = true;
            }
        },
        closeModalUser() {
            this.editUserModal.show = false;
        },

        createUser() {
            const { firstName, lastName, email, password } = this.editUserModal;
            if (document.title == "Teachers") {
                axios.post(`/api/teachers?firstName=${firstName}&lastName=${lastName}&email=${email}&password=${password}`)
                    .then(() => {
                        this.editUserModal.show = false;
                        this.getData();
                    })
                    .catch(error => {
                        console.log(error.message);
                    })
            }
            if (document.title == "Students") {
                axios.post(`/api/students?firstName=${firstName}&lastName=${lastName}&email=${email}&password=${password}`)
                    .then(() => {
                        this.editUserModal.show = false;
                        this.getData();
                    })
                    .catch(error => {
                        console.log(error.message);
                    })
            }
        },
        deleteUser(id) {
            if (document.title == "Teachers") {
                axios.delete(`/api/teachers/${id}`)
                    .then(() => {
                        this.getData();
                    })
                    .catch(error => {
                        console.log(error);
                    })
            }
            if (document.title == "Students") {
                axios.delete(`/api/students/${id}`)
                    .then(() => {
                        this.getData();
                    })
                    .catch(error => {
                        console.log(error);
                    })
            }
        }
    },

    computed: {
        filterData() {
            this.data = this.dataBackUp.filter(user => user.name.toLowerCase().includes(this.searchInput.toLowerCase()) && (user.courses.some(course => course.courseName == this.courseSelected) || this.courseSelected == "all"))
        },

        filterDataByName() {
            this.data = this.dataBackUp.filter(user => user.name.toLowerCase().includes(this.searchInput.toLowerCase()))
        }

    }

})

app.mount("#app")