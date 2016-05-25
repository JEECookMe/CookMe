showMySplash();

function showMySplash(){
	$('#splashScreen').show();
}

function hideMySplash(){
	$('#splashScreen').hide();
}

function renderDoneBlockUi() {
	var renderDone = $('#renderDone').val();
	if (renderDone) {
		hideMySplash();
	} else {
		setTimeout(checkRenderDone, 1000);
	}
}