function addOpenIdRow(){
	var tbody = document.getElementById("editors");
	var row = document.createElement("tr");
	
	var tds = [];
	var inputs = [];
	var names = ["editor.openid", "editor.comment"];
	for (var i = 0; i < 2; i++) {
		tds[i] = document.createElement("td");
		inputs[i] = document.createElement("input");
		inputs[i].name = names[i];
		inputs[i].type = "text";
		inputs[i].className = "text";
		inputs[i].setAttribute("style", "width:100%;");
		tds[i].appendChild(inputs[i]);
		row.appendChild(tds[i]);
	}
	tbody.appendChild(row);
}

function addSubscriptionRow() {
	var tbody = document.getElementById("subscriptions");
	var row = document.createElement("tr");

	var tds = [];
	var inputs = [];
	var names = [
		"subscription.title",
		"subscription.feedUrl",
		"subscription.siteUrl",
		"subscription.description"
	];
	for (var i = 0; i < 4; i++) {
		tds[i] = document.createElement("td");
		inputs[i] = document.createElement("input");
		inputs[i].name = names[i];
		inputs[i].className = "text";
		inputs[i].setAttribute("style", "width:100%;");
		tds[i].appendChild(inputs[i]);
		row.appendChild(tds[i]);
	}
	tbody.appendChild(row);
}