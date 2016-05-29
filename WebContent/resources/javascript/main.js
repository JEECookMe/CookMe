$( document ).ready(function (){
	startSplash();
	window.setTimeout(function(){
		finishSplash();
	}, 3000);
});

function startSplash(){
	$('#splashScreen').show();
}

function finishSplash(){
	$('#splashScreen').hide();
	window.location.href="home.xhtml";
}