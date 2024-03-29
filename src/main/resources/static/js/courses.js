function fetchApiData() {
    const url = '/api/courses';



    axios.get(url)
        .then(response => {
            const courses = response.data;

            console.log('Courses:', courses);

            fillTable(courses);

        })
        .catch(error => {
            // Manejo de errores
            console.error('Error fetching data:', error);
        });
}

function fillTable(data) {
    const tableBody = document.getElementById('coursesTable').getElementsByTagName('tbody')[0];
    // tableBody.innerHTML = ''; // Limpiar la tabla

    data.forEach(course => {
        let row = tableBody.insertRow();
        let nameCell = row.insertCell(0);
        nameCell.innerText = course.name;

        let startDateCell = row.insertCell(1);
        startDateCell.innerText = course.startDate;

        let finishDateCell = row.insertCell(2);
        finishDateCell.innerText = course.finishDate;

        let editCell = row.insertCell(3);
        editCell.innerHTML = "<button class='edit-btn'> <box-icon name='edit' type='solid' color='#1f8fc3' ></box-icon> </button>"

        let deleteCell = row.insertCell(4);
        deleteCell.innerHTML = "<button class='delete-btn'> <box-icon type='solid' name='trash' color='#1f8fc3' ></box-icon> </button>"

    });
}


fetchApiData();