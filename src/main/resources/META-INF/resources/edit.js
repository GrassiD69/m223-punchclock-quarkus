function init() {

    const URL = 'http://localhost:8080';
    var entry = JSON.parse(localStorage.getItem('Entry'));

    const checkInDateField = editEntryForm.querySelector('[name="checkInDate"]');
    checkInDateField.value = entry.checkIn;

    const checkOutDateField = editEntryForm.querySelector('[name="checkOutDate"]');
    checkOutDateField.value = entry.checkOut;

    const submit = editEntryForm.addEventListener('submit', (e) => {
        e.preventDefault();
        entry.checkIn = checkInDateField.value;
        entry.checkOut = checkOutDateField.value;
        updateEntry(entry);
    });
   


    const updateEntry = (entry) => {
    
        fetch(`${URL}/entries/`, {
            method: 'PUT'
            ,headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer ' + localStorage.getItem("token")
            },
            body: JSON.stringify(entry)
        }).then((result) => {
            if(result.status == 401) {
                location.href = "login.html";
            }else {
                location.href = `${URL}/index.html`
            }
        });
    };
}