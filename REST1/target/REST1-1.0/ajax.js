/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
loadDoc();
function loadDoc() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            document.getElementById("demo").innerHTML = this.responseText;
        }
    };

    xhttp.open("GET", "http://localhost:8080/REST1/api/person/random", true);
    xhttp.send();
}
function EditQuote() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            document.getElementById("demo").innerHTML = this.responseText;
        }
    };
    var id = $(".id").val();
    xhttp.open("PUT", "http://localhost:8080/REST1/api/person?id=" + id, true);
    xhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    var quote =$(".quote").val();
    xhttp.send(JSON.stringify({quote: quote}));
}
function Deletequote() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            document.getElementById("demo").innerHTML = this.responseText;
        }
    };
    var id = $(".id").val();
    xhttp.open("DELETE", "http://localhost:8080/REST1/api/person?id=" + id, true);
    xhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    var quote = $(".quote").val();
    xhttp.send(JSON.stringify({quote: quote}));
}
function Createquote() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState == 4 && this.status == 200) {
            document.getElementById("demo").innerHTML = this.responseText;
        }
    };
    xhttp.open("POST", "http://localhost:8080/REST1/api/person", true);
    xhttp.setRequestHeader("Content-Type", "application/json;charset=UTF-8");
    var quote = $(".quote").val();
    xhttp.send(JSON.stringify({quote: quote}));
}