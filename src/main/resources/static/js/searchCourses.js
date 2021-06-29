window.onload = function() {
	var element = document.getElementById("keyword");
	var id = document.getElementById("studentId");
	
	window.console.log(id.value);
	
	element.oninput = function () {
		if(element.value == null || element.value == ""){
            window.location.href = "http://localhost:8082/course/viewCourses/" + id.value;
            } 
        }
}