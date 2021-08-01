/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function searchElectionsDelete() {
    var input, filter, table, tr, td, i, txtValue, tBody;
    input = document.getElementById("searchBar");
    filter = input.value.toUpperCase();
    table = document.getElementById("electionTable");
    tBody = table.getElementsByTagName("tbody");
    tr = tBody[0].getElementsByTagName("tr");
    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[1];
        if (td) {
            txtValue = td.textContent || td.innerText;
            if (txtValue.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
}
