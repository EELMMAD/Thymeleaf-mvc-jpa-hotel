var selectedRow = null

function onFormSubmit() {
    var formData = readFormData();
    if (selectedRow == null)
        insertNewRecord(formData);
    else
        updateRecord(formData);
    resetForm();
}

function readFormData() {
    var formData = {};
    formData["customer"] = document.getElementById("customer").value;
    formData["room"] = document.getElementById("room").value;
    formData["CreateDate"] = document.getElementById("CreateDate").value;
    formData["ReserveDate"] = document.getElementById("ReserveDate").value;
    return formData;
}

function insertNewRecord(data) {
    var table = document.getElementById("BookingList").getElementsByTagName('tbody')[0];
    var newRow = table.insertRow(table.length);
    cell1 = newRow.insertCell(0);
    cell1.innerHTML = data.customer;
    cell2 = newRow.insertCell(1);
    cell2.innerHTML = data.room;
    cell3 = newRow.insertCell(2);
    cell3.innerHTML = data.CreateDate;
    cell4 = newRow.insertCell(3);
    cell4.innerHTML = data.ReserveDate;
    cell4 = newRow.insertCell(4);
    cell4.innerHTML = `<a onClick="onEdit(this)">Edit</a>
                       <a onClick="onDelete(this)">Delete</a>`;
}

function resetForm() {
    document.getElementById("customer").value = "";
    document.getElementById("room").value = "";
    document.getElementById("CreateDate").value = "";
    document.getElementById("ReserveDate").value = "";
    selectedRow = null;
}

function onEdit(td) {
    selectedRow = td.parentElement.parentElement;
    document.getElementById("customer").value = selectedRow.cells[0].innerHTML;
    document.getElementById("room").value = selectedRow.cells[1].innerHTML;
    document.getElementById("CreateDate").value = selectedRow.cells[2].innerHTML;
    document.getElementById("ReserveDate").value = selectedRow.cells[3].innerHTML;
}
function updateRecord(formData) {
    selectedRow.cells[0].innerHTML = formData.customer;
    selectedRow.cells[1].innerHTML = formData.room;
    selectedRow.cells[2].innerHTML = formData.CreateDate;
    selectedRow.cells[3].innerHTML = formData.ReserveDate;
}

function onDelete(td) {
    if (confirm('آیا از خذف مطمئن هستید؟')) {
        row = td.parentElement.parentElement;

        document.getElementById("BookingList").deleteRow(row.rowIndex);
        resetForm();
    }
}
