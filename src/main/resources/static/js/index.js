function fetchApiData() {
    const urls = [
        '/api/courses',
        '/api/teachers',
        '/api/students'
    ];

    const requests = urls.map(url => axios.get(url));

    Promise.all(requests)
        .then(responses => {
            const courses = responses[0].data;
            const teachers = responses[1].data;
            const students = responses[2].data;

            console.log('Courses:', courses);
            console.log('Teachers:', teachers);
            console.log('Students:', students);

            document.getElementById('coursesCount').innerText = courses.length;
            document.getElementById('teachersCount').innerText = teachers.length;
            document.getElementById('studentsCount').innerText = students.length;

            fillGeneralTable(students, "Student");
            fillGeneralTable(teachers, "Teacher");
            newCourses(courses);

        })
        .catch(error => {
            // Manejo de errores
            console.error('Error fetching data:', error);
        });
}

function fillGeneralTable(data, type) {
    const tableBody = document.getElementById('generalTable').getElementsByTagName('tbody')[0];
    // tableBody.innerHTML = ''; // Limpiar la tabla

    data.forEach(user => {
        let row = tableBody.insertRow();
        let nameCell = row.insertCell(0);
        nameCell.innerText = user.name;

        let typeCell = row.insertCell(1);
        typeCell.innerText = type;

        let anyCourseCell = row.insertCell(2);
        if (user.courses.filter(course => course.courseWithStudent || course.courseWithTeacher).length !== 0) {
            anyCourseCell.innerHTML = '<span class="status green"></span>Course/s asigned';
        }
        else {
            anyCourseCell.innerHTML = '<span class="status red"></span>Any course asigned';
        }


        // Agregar más celdas según tus datos
    });
}

function newCourses(courses) {
    const cardBody = document.getElementById('new-courses');

    coursesInfo = courses.reduce((acc, course) => {
        if (course.activeCourse) {
            return (
                acc +
                `
            <div class="course">
                <div class="info info-courses">
                        <h4>${course.name}</h4>
                        <small>Start Date: ${course.startDate}</small>
                        <small>Finish Date: ${course.finishDate}</small>
                </div>
            </div>`
            );
        }

    }, "")
    cardBody.innerHTML = coursesInfo;
}

fetchApiData();
