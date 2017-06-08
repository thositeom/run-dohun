/**
 * 
 */
//id값으로 길이 체크
function checkLength(length, id){
	var val = document.getElementById(id);
	if(length == val.value.length){
		return "true";
	}else{
		return "false";
	}
} 

//vaildation
function vaildation(form){
}