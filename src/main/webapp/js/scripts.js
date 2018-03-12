var userName;

function sendLoginData() {
    function toJSONString( form ) {
        var obj = {};
        var elements = form.querySelectorAll( "input, select, textarea" );
        for( var i = 0; i < elements.length; ++i ) {
            var element = elements[i];
            var name = element.name;
            userName = name;
            var value = element.value;

            if( name ) {
                obj[ name ] = value;
            }
        }
        return JSON.stringify( obj );
    }

    document.addEventListener( "DOMContentLoaded", function() {
        var form = document.getElementById( "test" );
        var output = document.getElementById( "output" );
        form.addEventListener( "submit", function( e ) {
            e.preventDefault();
            var json = toJSONString( this );
            output.innerHTML = json;
            userName = json;
            var xhr = new XMLHttpRequest();
            xhr.open('POST', 'http://localhost:8081/api/v1/users', true);
            xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
            xhr.setRequestHeader('Accept', 'application/json -d');
            xhr.send(json);
        }, false);

    });
};

function getCurrentUserName() {
    return userName;
}