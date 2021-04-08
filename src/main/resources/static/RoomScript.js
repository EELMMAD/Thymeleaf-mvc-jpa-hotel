var selectedRow = null

function onFormSubmit() {
    if (true) {
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
    formData["code"] = document.getElementById("code").value;
    formData["describe"] = document.getElementById("describe").value;
    formData["roomType"] = document.getElementById("roomType").value;
    if(document.getElementById("r1").checked){
        formData["status"] = document.getElementById("r1").value;

    }else if(document.getElementById("r2").checked){
        formData["status"] = document.getElementById("r2").value;
    }
    formData["floor"] = document.getElementById("floor").value;

    return formData;
}

function insertNewRecord(data){
    var table = document.getElementById("RoomList").getElementsByTagName('tbody')[0];
    var newRow = table.insertRow(table.length);
    cell1 = newRow.insertCell(0);
    cell1.innerHTML = data.code;
    cell2 = newRow.insertCell(1);
    cell2.innerHTML = data.describe;
    cell3 = newRow.insertCell(2);
    cell3.innerHTML = data.roomType;

    cell4 = newRow.insertCell(3);
    cell4.innerHTML = data.status;
    cell5 = newRow.insertCell(4);
    cell5.innerHTML = data.floor;
    cell4 = newRow.insertCell(5);
    cell4.innerHTML = `<a onClick="onEdit(this)">Edit</a>
                       <a onClick="onDelete(this)">Delete</a>`;
}

function resetForm() {
    document.getElementById("code").value = "";
    document.getElementById("describe").value = "";
    document.getElementById("roomType").value = "";
    document.getElementById("r2").checked="checked";
    document.getElementById("floor").value = "";
    selectedRow = null;
}

function onEdit(td) {
    selectedRow = td.parentElement.parentElement;
    document.getElementById("code").value = selectedRow.cells[0].innerHTML;
    document.getElementById("describe").value = selectedRow.cells[1].innerHTML;
    document.getElementById("roomType").value = selectedRow.cells[2].innerHTML;
    document.getElementById("floor").value = selectedRow.cells[4].innerHTML;
}
function updateRecord(formData) {
    selectedRow.cells[0].innerHTML = formData.code;
    selectedRow.cells[1].innerHTML = formData.describe;
    selectedRow.cells[2].innerHTML = formData.roomType;
    selectedRow.cells[3].innerHTML = formData.status;
    selectedRow.cells[3].innerHTML = formData.floor;

}

function onDelete(td) {
    if (confirm('Are you sure want to delete?')) {
        row = td.parentElement.parentElement;

        document.getElementById("RoomList").deleteRow(row.rowIndex);
        resetForm();
    }
}
function validate() {
    isValid = true;
    if (document.getElementById("codeValidationError").value === "") {
        isValid = false;
        document.getElementById("codeValidationError").classList.remove("hide");
    } else {
        isValid = true;
        if (!document.getElementById("codeValidationError").classList.contains("hide"))
            document.getElementById("codeValidationError").classList.add("hide");
    }

    return isValid;
}
