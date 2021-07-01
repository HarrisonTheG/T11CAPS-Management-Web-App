window.onload = function(){
	var element = document.getElementById("keyword");
	var id = document.getElementById("courseId");
	
	element.oninput = function () {
		if(element.value == null || element.value == ""){
            window.location.href = "http://localhost:8082/course/" + id.value + "/student-list";
            } 
        }
}