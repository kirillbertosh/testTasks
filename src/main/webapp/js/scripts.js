var userName;
var userPhone;

function sendLoginData() {
    function toJSONString( form ) {
        var obj = {};
        var elements = form.querySelectorAll( "input, select, textarea" );
        for( var i = 0; i < elements.length; ++i ) {
            var element = elements[i];
            var name = element.name;
            var value = element.value;
            if( name ) {
                obj[ name ] = value;
            }
        }
        return JSON.stringify( obj );
    }

    document.addEventListener( "DOMContentLoaded", function() {
        var form = document.getElementById( "test" );
        form.addEventListener( "submit", function( e ) {
            e.preventDefault();
            var json = toJSONString( this );
            var xhr = new XMLHttpRequest();
            xhr.open('POST', 'http://localhost:8081/api/v1/users', true);
            xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
            xhr.setRequestHeader('Accept', 'application/json -d');
            xhr.send(json);


        }, false);

    });
}

function getCurrentUserName() {
    var theElement = document.getElementById("user-name");
    theElement.innerHTML = userName;
}

function getCurrentUserPhone() {
    /*var json = JSON.stringify(userName);
    var xhr = new XMLHttpRequest();
    xhr.open('GET', 'http://localhost:8081/api/v1/users/name/' + json, false);
    xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
    xhr.setRequestHeader('Accept', 'application/json');
    xhr.send();
    console.log(xhr.responseText);
    */
    var theElement = document.getElementById("user-phone")
    theElement.innerHTML = userPhone;
}

function change_visibility(block_for_open, block_for_close) {
    document.getElementById(block_for_open).style.display='';
    document.getElementById(block_for_close).style.display='none';
}

function joinToDeal() {

}