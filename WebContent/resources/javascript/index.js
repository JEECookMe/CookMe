$( document ).ready(function() {
	showMySplash();
});

function showMySplash(){
	
}

function hideMySplash(){
	
}

function renderDoneBlockUi() {
	var renderDone = $('#renderDone').val();
	if (renderDone) {
		hideMySplash();
	} else {
		setTimeout(checkRenderDone, 1000);
	}
}