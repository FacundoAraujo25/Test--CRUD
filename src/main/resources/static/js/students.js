let students = [];
let courses = [];

function fetchApiData() {
    const urlStudents = '/api/students';
    const urlCourses = '/api/courses';

    axios.get(urlStudents)
        .then(response => {
            students = response.data;
            console.log('Students:', students);
            fillTable(students);
        })
        .catch(error => {
            console.error('Error fetching students:', error);
        });

    axios.get(urlCourses)
        .then(response => {
            courses = response.data;
            console.log('Courses:', courses);
            fillCourseSelect(courses);
        })
        .catch(error => {
            console.error('Error fetching courses:', error);
        });

}

function fillTable(data) {
    const tableBody = document.getElementById('studentsTable').getElementsByTagName('tbody')[0];
    // tableBody.innerHTML = ''; // Limpiar la tabla

    data.forEach(student => {
        let row = tableBody.insertRow();

        let idCell = row.insertCell(0);
        idCell.innerText = student.studentId;

        let nameCell = row.insertCell(1);
        nameCell.innerText = student.name;

        let emailCell = row.insertCell(2);
        emailCell.innerText = student.email;

        let statusCell = row.insertCell(3);
        if (student.activeStudent) {
            statusCell.innerHTML = '<span class="status green"></span>';
        }
        else {
            statusCell.innerHTML = '<span class="status red"></span>';
        }

        let editCell = row.insertCell(4);
        editCell.innerHTML = "<button class='edit-btn'> <box-icon name='edit' type='solid' color='#1f8fc3' ></box-icon> </button>"

        let deleteCell = row.insertCell(5);
        deleteCell.innerHTML = "<button class='delete-btn'> <box-icon type='solid' name='trash' color='#1f8fc3' ></box-icon> </button>"

    });
}

function fillCourseSelect(courses) {
    const select = document.getElementById('courseSelect');
    select.innerHTML = '';

    select.add(new Option('Todos los cursos', ''));


    courses.forEach(course => {
        let option = new Option(course.name, course.name);
        select.add(option);
    });
}

function filterStudents() {
    const searchText = document.getElementById('searchInput').value.toLowerCase();
    const selectedCourse = document.getElementById('courseSelect').value;
    const tableBody = document.getElementById('studentsTable').getElementsByTagName('tbody')[0];

    students.forEach(student => {

        const nameMatches = student.name.toLowerCase().includes(searchText);

        const courseMatches = selectedCourse === '' || student.courses.some(course => course.courseName === selectedCourse);

        if (nameMatches && courseMatches) {
            // Si ambos, nombre y curso, coinciden, añadir el estudiante a la tabla
            let row = tableBody.insertRow();

            let idCell = row.insertCell(0);
            idCell.innerText = student.studentId;
            let nameCell = row.insertCell(1);
            nameCell.innerText = student.name;
            let emailCell = row.insertCell(2);
            emailCell.innerText = student.email;
            let statusCell = row.insertCell(3);
            if (student.activeStudent) {
                statusCell.innerHTML = '<span class="status green"></span>';
            }
            else {
                statusCell.innerHTML = '<span class="status red"></span>';
            }
            let editCell = row.insertCell(4);
            editCell.innerHTML = "<button class='edit-btn'> <box-icon name='edit' type='solid' color='#1f8fc3' ></box-icon> </button>"
            let deleteCell = row.insertCell(5);
            deleteCell.innerHTML = "<button class='delete-btn'> <box-icon type='solid' name='trash' color='#1f8fc3' ></box-icon> </button>"
        }


    });
}
document.addEventListener('DOMContentLoaded', () => {
    document.getElementById('searchInput').addEventListener('input', filterStudents);
    document.getElementById('courseSelect').addEventListener('change', filterStudents);
});

fetchApiData();