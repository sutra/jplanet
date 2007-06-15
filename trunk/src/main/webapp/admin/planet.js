function addOpenIdRow(){
	var tbody = document.getElementById("editors");
	var row = document.createElement("tr");
	var td1 = document.createElement("td");
	var input1 = document.createElement("input");
	input1.name = "editor.openid";
	input1.className = "text";
	input1.size = "100"
	td1.appendChild(input1);
	row.appendChild(td1);
	tbody.appendChild(row);
}
function addSubscriptionRow() {
	var tbody = document.getElementById("subscriptions");
	var row = document.createElement("tr");
	var td1 = document.createElement("td");
	var td2 = document.createElement("td");
	var td3 = document.createElement("td");
	var td4 = document.createElement("td");
	var input1 = document.createElement("input");
	var input2 = document.createElement("input");
	var input3 = document.createElement("input");
	var input4 = document.createElement("input");
	input1.name = "subscription.title";
	input2.name = "subscription.feedUrl";
	input3.name = "subscription.siteUrl";
	input4.name = "subscription.description";
	input1.className = "text";
	input2.className = "text";
	input3.className = "text";
	input4.className = "text";
	input1.size = "15";
	input2.size = "30";
	input3.size = "30";
	input4.size = "40";
	td1.appendChild(input1);
	td2.appendChild(input2);
	td3.appendChild(input3);
	td4.appendChild(input4);
	row.appendChild(td1);
	row.appendChild(td2);
	row.appendChild(td3);
	row.appendChild(td4);
	tbody.appendChild(row);
}