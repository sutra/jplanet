function addOpenIdRow(){
	var tbody = document.getElementById("editors");
	var row = document.createElement("tr");
	var td1 = document.createElement("td");
	var input1 = document.createElement("input");
	input1.name = "editor.openid";
	input1.type = "text";
	input1.className = "text";
	input1.setAttribute("style", "width:100%;");
	td1.appendChild(input1);
	row.appendChild(td1);
	tbody.appendChild(row);
}

function addSubscriptionRow() {
	var tbody = document.getElementById("subscriptions");
	var row = document.createElement("tr");

	var tds = new Array();
	var inputs = new Array();
	for (var i = 0; i < 4; i++) {
		tds[i] = document.createElement("td");
		inputs[i] = document.createElement("input");
		inputs[i].className = "text";
		inputs[i].setAttribute("style", "width:100%;");
		tds[i].appendChild(inputs[i]);
		row.appendChild(tds[i]);
	}
	tbody.appendChild(row);

	inputs[0].name = "subscription.title";
	inputs[1].name = "subscription.feedUrl";
	inputs[2].name = "subscription.siteUrl";
	inputs[3].name = "subscription.description";
}