window.onload = function(){
	var element = document.getElementById("keyword");
	
	element.oninput = function () {
		if(element.value == null || element.value == ""){
            window.location.href = "http://localhost:8082/lecturer/student-list";
            } 
        }
}