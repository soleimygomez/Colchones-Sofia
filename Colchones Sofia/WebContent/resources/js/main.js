

$(document).ready(function () {
	let test= [...document.getElementsByClassName("sofia-container-text-image__h1")];
	if(test.length > 0){
	    var mouseX, mouseY;
	    var ww = $(window).width();
	    var wh = $(window).height();
	    var traX, traY;
	    $(document).mousemove(function (e) {
	        mouseX = e.pageX;
	        mouseY = e.pageY;
	        traX = ((14 * mouseX) / 600) + 50;
	        traY = ((14 * mouseY) / 600) + 50;
	        console.log(`Posicion: (${traX}, ${traY})`);
	        $(".sofia-container-text-image__h1").css({
	            "background-position": traX + "%" + traY + "%"
	        });
	    });
	}else{
		console.log("404");
	}
});



/**
 * 
 * @returns
 */
function viewLoginForgot(){
	let login= [...document.getElementsByClassName("sofia-view-login__container-login")];
	let forgot= [...document.getElementsByClassName("sofia-view-login__container-forgot")];
	if(login.length > 0 && forgot.length > 0 ){
		login= login[0];
		forgot= forgot[0];
		login.classList.add("sofia-view-login__container-hidden");
		login.classList.remove("sofia-view-login__container-visible");
		forgot.classList.remove("sofia-view-login__container-hidden");
		forgot.classList.add("sofia-view-login__container-visible");
	}
}

/**
 * 
 * @returns
 */
function viewLoginLogin(){
	let login= [...document.getElementsByClassName("sofia-view-login__container-login")];
	let forgot= [...document.getElementsByClassName("sofia-view-login__container-forgot")];
	if(login.length > 0 && forgot.length > 0 ){
		login= login[0];
		forgot= forgot[0];
		login.classList.remove("sofia-view-login__container-hidden");
		login.classList.add("sofia-view-login__container-visible");
		forgot.classList.add("sofia-view-login__container-hidden");
		forgot.classList.remove("sofia-view-login__container-visible");
	}
}