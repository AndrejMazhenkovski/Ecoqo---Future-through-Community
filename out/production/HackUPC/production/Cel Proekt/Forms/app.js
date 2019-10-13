
(function() {
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
		var output = document.getElementById( "output" );
		form.addEventListener( "submit", function( e ) {
			e.preventDefault();
			var json = toJSONString( this );
			output.innerHTML = json;
			
			//easyHTTP.prototype.post("http://localhost:8000",json).then (data => console.log(data)).catch (error=> console.log(error))
			
			const http = new easyHTTP();
			http.post("http://localhost:8000",json).then (data => console.log(data)).catch (error=> console.log(error));

		}, false);

	});

	
	//const http = new easyHTTP();
	//easyHTTP.prototype.post("http://localhost:8000",json).then (data => console.log(data)).catch (error=> console.log(error))

})();