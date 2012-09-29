function validar(){
	var retornar = true;
	var mensaje = "Corrija la siguiente información:\n";
	var f1 = document.getElementById("fecha1");
	var f2 = document.getElementById("fecha2");
	if (f1.value == "" || f2.value == ""){
		mensaje += "- Rango de fechas es requerido.\n";
		retornar = false;
	} else if (converToDate(f1.value) > converToDate(f2.value)){
		mensaje += "- Fecha Inicio debe ser menor que Fecha Fin.\n";
		retornar = false;
	}
	var field = document.getElementsByName("rdRegis");
	var regis = document.getElementById("regis");
	if (field[1].checked && regis.value == ""){
		mensaje += "- Debe seleccionar por lo menos un Código de Digitador.\n";
		retornar = false;
	}
	var calif = document.getElementById("calif");
	if (calif.value == ""){
		mensaje += "- Debe seleccionar por lo menos un Estado de las presentaciones.\n";
		retornar = false;
	}
	if (!retornar){
		alert(mensaje);
	}
	return retornar;
}

function validar1(){
	var f1 = document.getElementById("fecha1");
	var f2 = document.getElementById("fecha2");
	if (f1.value == "" || f2.value == ""){
		alert("Rango de fechas es requerido.");
		return false;
	} else if (converToDate(f1.value) > converToDate(f2.value)){
		alert("Fecha Inicio debe ser menor que Fecha Fin.");
		return false;
	}
	return true;
}

function converToDate(string) {
	var date = new Date();
	mes = parseInt(string.substring(3, 5));
	date.setMonth(mes - 1); //en javascript los meses van de 0 a 11
	date.setDate(string.substring(0, 2));
	date.setYear(string.substring(6, 10));
	return date;
}

function fechaActual(){
	var mydate = new Date();
    var year = mydate.getFullYear();
    var month = mydate.getMonth()+1;
    if (month < 10)
    	month = "0"+month;
    var daym = mydate.getDate();
    if (daym < 10)
     daym = "0" + daym;
    return daym + "/" + month + "/" + year;
}

function inicioMes(){
	var mydate = new Date();
    var year = mydate.getFullYear();
    var month = mydate.getMonth()+1;
    if (month < 10)
    	month = "0"+month;
    return "01/" + month + "/" + year;
}

function cargarRegistradores(sn){
	if (validar1()){
		document.getElementById("regis").value = "";
		document.getElementById('accion').value = sn;
		var form1 = document.getElementById("Form1");
		form1.action="";
		form1.target="";
		form1.onsubmit="";
		form1.submit();
	}
}

function textoReso(sn){
	document.getElementById('vertxt').value = sn;
}

function checkEstado(name) {
	document.getElementById("calif").value = "";
	var field = document.getElementsByName(name);
	for (var i = 0; i < field.length; i++) {
		if (field[i].checked) {
			document.getElementById("calif").value += "'"+field[i].value+"',";
		}

	}
	str = document.getElementById("calif").value;
	if (str.length>0){
		document.getElementById("calif").value = str.substring(0,str.length-1);
	}
	return true;
}

function checkDigitador(name) {
	document.getElementById("regis").value = "";
	var field = document.getElementsByName(name);
	for (var i = 0; i < field.length; i++) {
		if (field[i].checked) {
			document.getElementById("regis").value += "'"+field[i].value+"',";
		}

	}
	str = document.getElementById("regis").value;
	if (str.length>0){
		document.getElementById("regis").value = str.substring(0,str.length-1);
	}
	return true;
}

function printReporte(){
	var form1 = document.getElementById("Form1");
	form1.action="reptsrc/reportes.jsp";
	form1.submit();
}
