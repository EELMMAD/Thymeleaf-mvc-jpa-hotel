var selectedRow = null

function onFormSubmit() {
    if (validate()) {
        var formData = readFormData();
        if (selectedRow == null)
            insertNewRecord(formData);
        else
            updateRecord(formData);
        resetForm();
    }
}

function readFormData() {
    var formData = {};
    formData["describe"] = document.getElementById("describe").value;
    return formData;
}

function insertNewRecord(data) {
    var table = document.getElementById("roomTypeList").getElementsByTagName('tbody')[0];
    var newRow = table.insertRow(table.length);
    cell1 = newRow.insertCell(0);
    cell1.innerHTML = data.describe;
    cell4 = newRow.insertCell(1);
    cell4.innerHTML = `<a onClick="onEdit(this)">Edit</a>
                       <a onClick="onDelete(this)">Delete</a>`;
}

function resetForm() {
    document.getElementById("describe").value = "";
    selectedRow = null;
}

function onEdit(td) {
    selectedRow = td.parentElement.parentElement;
    document.getElementById("describe").value = selectedRow.cells[0].innerHTML;
}
function updateRecord(formData) {
    selectedRow.cells[0].innerHTML = formData.describe;
}

function onDelete(td) {
    if (confirm('Are you sure want to delete?')) {
        row = td.parentElement.parentElement;

        document.getElementById("roomTypeList").deleteRow(row.rowIndex);
        resetForm();
    }
}
function validate() {
    isValid = true;
    if (document.getElementById("describe").value === "") {
        isValid = false;
        document.getElementById("describeValidationError").classList.remove("hide");
    } else {
        isValid = true;
        if (!document.getElementById("describeValidationError").classList.contains("hide"))
            document.getElementById("describeValidationError").classList.add("hide");
    }
    return isValid;
}
