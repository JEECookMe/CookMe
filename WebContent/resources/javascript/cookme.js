$( document ).ready(function (){
	startSplash();
	window.setTimeout(function(){
		finishSplash();
	}, 5000);
});

function startSplash(){
	$('#splashScreen').show();
}

function finishSplash(){
	$('#splashScreen').hide();
	window.location.href="home.xhtml";
}