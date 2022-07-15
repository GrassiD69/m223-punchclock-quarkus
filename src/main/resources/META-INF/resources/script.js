const URL = 'http://localhost:8080';
let entries = [];
let categories = [];

const dateAndTimeToDate = (dateString, timeString) => {
    return new Date(`${dateString}T${timeString}`).toISOString();
};

const logout = () => {
    var logoutBtn = document.getElementById("logoutBtn");
    logoutBtn.onclick = () => {
        localStorage.clear();
        location.href = "login.html";
    }
}

const createEntry = (e) => {
    e.preventDefault();
    const formData = new FormData(e.target);
    const entry = {};
    entry['checkIn'] =
        dateAndTimeToDate(formData.get('checkInDate'), formData.get('checkInTime'));
    entry['checkOut'] = dateAndTimeToDate(formData.get('checkOutDate'), formData.get('checkOutTime'));

    fetch(`${URL}/entries`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
            'Authorization': 'Bearer ' + localStorage.getItem("token")
        },
        body: JSON.stringify(entry)
    }).then((result) => {
        if (result.status == 401) {
            location.href = "login.html";
        } else {
            result.json().then((entry) => {
                entries.push(entry);
                renderEntries();
            });
        }
    });
};

const indexEntries = () => {
    fetch(`${URL}/entries`, {
        method: 'GET',
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem("token")
        }
    }).then((result) => {
        if (result.status == 401) {
            location.href = "login.html";
        } else {
            result.json().then((result) => {
                entries = result;
                renderEntries();
            });
        }
    });
    renderEntries();
};

const createCell = (text) => {
    const cell = document.createElement('td');
    cell.innerText = text;
    return cell;
};

const renderEntries = () => {
    const display = document.querySelector('#entryDisplay');
    display.innerHTML = '';
    entries.forEach((entry) => {
        const row = document.createElement('tr');
        row.appendChild(createCell(entry.id));
        row.appendChild(createCell(new Date(entry.checkIn).toLocaleString()));
        row.appendChild(createCell(new Date(entry.checkOut).toLocaleString()));
        row.appendChild(createCell(entry.getCat));
        let btn = document.createElement("button");
        btn.innerHTML = "Delete";
        btn.type = "submit";
        btn.name = "formBtn";
        btn.onclick = function () {
            deleteEntry(entry.id);
        };

        let updt = document.createElement("button");
        updt.innerHTML = "Edit";
        updt.type = "submit";
        updt.name = "formUpdt";
        updt.onclick = function () {
            getEntry(entry.id);
        };

        row.appendChild(btn);
        row.appendChild(updt);
        display.appendChild(row);
    });
};


const deleteEntry = (entryId) => {

    fetch(`${URL}/entries/${entryId}`, {
        method: 'DELETE',
        headers: {
            'Authorization': 'Bearer ' + localStorage.getItem("token")
        }
    }).then((result) => {
        if (result.status == 401) {
            location.href = "login.html";
        } else {
            indexEntries();
        }
    });
};

const getEntry = (entryId) => {

    fetch(`${URL}/entries/${entryId}`, {
        method: 'GET'
        ,headers: {
            'Authorization': 'Bearer ' + localStorage.getItem("token")
        }
    }).then((result) => {
        result.text().then((result) => {
        if (result.status == 401) {
            location.href = "login.html";
        } else {
            localStorage.setItem("Entry", result);
            location.href = `${URL}/edit.html`
        }
        });
    });
};


const indexCategories = () => {
    fetch(`${URL}/category`, {
        method: 'GET'
    }).then((result) => {
        result.json().then((result) => {
            categories = result;
        });
    });
    renderEntries();
};



const renderCategories = (categories) => {
    const selection = document.querySelector('#categories');
    categories.forEach((category) => {
        var option = document.createElement("option");
        option.value = category.id;
        option.text = category.name;
        selection.appendChild(option);

    });
};









document.addEventListener('DOMContentLoaded', function () {
    const createEntryForm = document.querySelector('#createEntryForm');
    createEntryForm.addEventListener('submit', createEntry);
    const logoutBtn = document.querySelector('#logoutBtn')
    logoutBtn.onclick = logout;

    indexEntries();
});