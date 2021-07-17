/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function searchElectionsView() {
    var input, filter, formCollection, form, button, txtValue, i, table;
    input = document.getElementById("searchBar");
    filter = input.value.toUpperCase();
    table = document.getElementById("electionList");
    formCollection = table.getElementsByTagName("form");
    debugger;
    for (i = 0; i < formCollection.length; i++) {
        form = formCollection[i];
        button = form.getElementsByTagName("button")
        if (button) {
            txtValue = button[0].textContent || button[0].innerText;
            if (txtValue.toUpperCase().indexOf(filter) > -1) {
                button[0].style.display = "block";
            } else {
                button[0].style.display = "none";
            }
        }
    }
}
