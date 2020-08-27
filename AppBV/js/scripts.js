/*
    WS functions
*/
function runWS(requestURL, callback) {
	var rawFile = new XMLHttpRequest();

	rawFile.overrideMimeType("application/json");
	rawFile.open("GET", requestURL, true);
	rawFile.setRequestHeader("Content-Type", "application/json");
	rawFile.onreadystatechange = function () {
		if (rawFile.readyState === 4 && rawFile.status == 200) {
			callback(rawFile.responseText);
		}
		else {
			console.log("fail runWS:", " status:", rawFile.status, " state:", rawFile.readyState)
		}
	}
	rawFile.send();
}

function popularItens(jsonObj) {

	var listaNode = document.getElementById('divListItens');

	if (jsonObj !== undefined) {
		for (var i = 0; i < jsonObj.length; i++) {
			if (i > 0) {
				var itemNode = document.getElementById('divItem');
				var listaNode = document.getElementById('divListItens');
				var clone = itemNode.cloneNode(true);
				listaNode.appendChild(clone);
			}

			if (jsonObj[i].descricao !== undefined) {
				var urlDetalheItem = 'Detalhe.htm?id=' + jsonObj[i].idItem;
				document.getElementsByClassName("pub_urlDetalheItem1")[i].href = urlDetalheItem;
				document.getElementsByClassName("pub_urlDetalheItem2")[i].href = urlDetalheItem;
				document.getElementsByClassName("pub_urlDetalheItem3")[i].href = urlDetalheItem;
				document.getElementsByClassName("pub_imagem")[i].innerHTML = '<img src="data:image/png;base64,' + jsonObj[i].foto + '" width="60" height="60" />';
				document.getElementsByClassName("pub_titulo")[i].innerHTML = jsonObj[i].nome;
				document.getElementsByClassName("pub_descricao")[i].innerHTML = jsonObj[i].descricao;
				document.getElementsByClassName("pub_data")[i].innerHTML = jsonObj[i].dataPublicacao;
				document.getElementsByClassName("pub_reputacao")[i].innerHTML = jsonObj[i].notaProprietario;
				document.getElementsByClassName("pub_valor")[i].innerHTML = tipoMoeda(jsonObj[i].tipoValor, jsonObj[i].valor);
			}
		}
	}
}

function detalheItem(jsonObj, idItem) {
	var numItem = 0;

	if (jsonObj.idItem !== null) {
		if (jsonObj.idItem == idItem) {
			document.getElementsByClassName("pub_tags")[numItem].innerHTML = jsonObj.tags;
			document.getElementsByClassName("pub_imagem")[numItem].innerHTML = '<img src="data:image/png;base64,' + jsonObj.foto + '" width="60" height="60" />';
			document.getElementsByClassName("pub_titulo")[numItem].innerHTML = jsonObj.nome;
			document.getElementsByClassName("pub_descricao")[numItem].innerHTML = jsonObj.descricao;
			document.getElementsByClassName("pub_data")[numItem].innerHTML = jsonObj.dataPublicacao;
		}
		else {
			$('.modal-carregando').modal('hide');
			$('.modal-detalhe-semregistros').modal('show');
		}
	}
}

function detalheItem_OLD(jsonObj, idItem) {
	var numItem = 0;

	if (jsonObj !== undefined) {
		for (var i = 0; i < jsonObj.length; i++) {
			if (jsonObj[i].idItem !== undefined) {
				if (jsonObj[i].idItem == idItem) {
					document.getElementsByClassName("pub_tags")[numItem].innerHTML = jsonObj[i].tags;
					document.getElementsByClassName("pub_imagem")[numItem].innerHTML = '<img src="data:image/png;base64,' + jsonObj[i].foto + '" width="60" height="60" />';
					document.getElementsByClassName("pub_titulo")[numItem].innerHTML = jsonObj[i].nome;
					document.getElementsByClassName("pub_descricao")[numItem].innerHTML = jsonObj[i].descricao;
					document.getElementsByClassName("pub_data")[numItem].innerHTML = 'H&aacute; ' + jsonObj[i].dataSugestaoDevolucao;
					break;
				}
			}
		}
	}
}

function tipoMoeda(tipo, valor) {
	var result = '<i class="fas fa-donate" style="font-size: 25px; color: #12bbad"></i> ' + valor;
	if (tipo == '2') {
		result = '<i class="fas fa-money-bill-alt" style="font-size: 25px; color: green"></i> R$ ' + valor + ',00';
	}
	return result;
}


/*
    Cookie functions
*/
function setCookie(cnome, cvalor, exdias) {
	var d = new Date();
	d.setTime(d.getTime() + (exdias * 24 * 60 * 60 * 1000));
	var expires = "expires=" + d.toGMTString();
	document.cookie = cnome + "=" + cvalor + "; " + expires + "; SameSite=None; Secure";
}

function getCookie(cnome) {
	var name = cnome + "=";
	var ca = document.cookie.split(';');
	for (var i = 0; i < ca.length; i++) {
		var c = ca[i];
		while (c.charAt(0) == ' ') c = c.substring(1);
		if (c.indexOf(name) == 0) {
			return c.substring(name.length, c.length);
		}
	}
	return "";
}

function checkCookie() {
	var cnome = getCookie("appBV");
	if (cnome != "") {
		return cnome;
	} else {
		window.location.href = "Default.htm";
	}
}


/*
    Login functions
*/
$('#loginUsuario').on('click', function (event) {

	event.preventDefault();

	var email = $('#txtEmail').val();
	if ($('#txtSenha').val() !== undefined) {
		var senha = sha256($('#txtSenha').val());
		console.log('password  encrypted:', senha);
		sendCredentials(email, senha);
	}

	else {
		console.log("loginUsuario validation");
		$('.modal-login-invalido').modal('show');
	}
});

function sendCredentials(email, senha) {
	var data = { "login": email, "password": senha }
	console.log('sendCredentials', data);
	var jData = JSON.stringify(data);


	// Creating a XHR object
	let xhr = new XMLHttpRequest();
	let url = "http://54.163.66.128:8081/api/v1/authenticate"; //Rodando no AWS
	//let url = "http://localhost:8081/api/v1/authenticate" //Rodando no local
	// http://54.163.66.128:8080 AWS
	var response = '';

	// open a connection
	xhr.open("POST", url, true);

	// Set the request header i.e. which type of content you are sending
	xhr.setRequestHeader("Content-Type", "application/json");

	// Create a state change callback
	xhr.onreadystatechange = function () {
		console.log("state changed");
		if (xhr.readyState === 4 && xhr.status === 200) {

			// Print received data from server 
			console.log("state 200");
			response = JSON.parse(this.responseText);
			console.log('Validado com sucesso:', response);
			if (response.data["idUser"] !== undefined) {
				console.log('login succeed!, setting cookies');
				setCookie("appBV", email, 1);

				//Saving the idUser in the sessionStorage
				window.sessionStorage.setItem('idUser', response.data["idUser"]);
				window.location.href = "Itens.htm";
			}
			else {
				console.log("response data issue");
			}
		}
		else {
			console.log("Fail login: Status", xhr.status, "Response", xhr.response);
			$('.modal-login-invalido').modal('show');
		}


	};

	// Sending data with the request 
	xhr.send(jData);
	return response;

}
/*
    Search action
*/
$('#txtPesquisar').on('click', function (event) {

	event.preventDefault();

	$('#txtPesquisar').val('');
});

$('#btnPesquisar').on('click', function (event) {

	event.preventDefault();

	var pesq = $('#txtPesquisar').val();

	if (pesq.length > 2) {

		$(this).prop("disabled", true).html('<i class="fas fa-spinner" style="font-size: 20px;"></i>');
		$('.modal-carregando').modal('show');

		var clonedRows = document.querySelectorAll('.clonedrow');
		for (var i = 0; i < clonedRows.length; i++) {
			clonedRows[i].parentNode.removeChild(clonedRows[i]);
		}

		runWS('http://54.163.66.128:8080/items/search/' + pesq, function (text) {
			var data = JSON.parse(text);
			if (data.length > 0) {
				var clonedRows = document.querySelectorAll('.clonedrow');
				for (var i = 0; i < clonedRows.length; i++) {
					clonedRows[i].parentNode.removeChild(clonedRows[i]);
				}

				popularItens(data);

				$('.modal-carregando').modal('hide');
			}
			else {
				$('.modal-carregando').modal('hide');
				$('.modal-pesquisar-semregistros').modal('show');
			}

			$('#btnPesquisar').prop("disabled", false).html('<i class="fas fa-search" style="font-size: 20px;"></i>');
		});
	}
	else {
		$('.modal-pesquisar-invalido').modal('show');
	}
});



/*
    Page functions
*/
function pagItens() {
	$("#sidebar").mCustomScrollbar({
		theme: "minimal"
	});

	$('#dismiss, .overlay').on('click', function () {
		$('#sidebar').removeClass('active');
		$('.overlay').removeClass('active');
	});

	$('#sidebarCollapse').on('click', function () {
		$('#sidebar').addClass('active');
		$('.overlay').addClass('active');
		$('.collapse.in').toggleClass('in');
		$('a[aria-expanded=true]').attr('aria-expanded', 'false');
	});

	$('.modal-carregando').modal('show');

	runWS('http://54.163.66.128:8080/items/findAll', function (text) {
		var data = JSON.parse(text);
		if (data !== undefined) {
			popularItens(data);

			$('.modal-carregando').modal('hide');
		}
	});
}

function pagDetalhe() {
	$("#sidebar").mCustomScrollbar({
		theme: "minimal"
	});

	$('#dismiss, .overlay').on('click', function () {
		$('#sidebar').removeClass('active');
		$('.overlay').removeClass('active');
	});

	$('#sidebarCollapse').on('click', function () {
		$('#sidebar').addClass('active');
		$('.overlay').addClass('active');
		$('.collapse.in').toggleClass('in');
		$('a[aria-expanded=true]').attr('aria-expanded', 'false');
	});

	$('.modal-carregando').modal('show');

	var urlParams = new URLSearchParams(window.location.search);
	var idItem = 0;

	if (urlParams.has("id") === true) {
		idItem = urlParams.get("id");

		runWS('http://54.163.66.128:8080/items/findItem/' + idItem, function (text) {
			var data = JSON.parse(text);

			if (data !== undefined) {
				detalheItem(data, idItem);

				$('.modal-carregando').modal('hide');
			}
			else {
				$('.modal-carregando').modal('hide');
				$('.modal-detalhe-semregistros').modal('show');
			}
		});
	}
	else {
		$('.modal-carregando').modal('hide');
		$('.modal-detalhe-semregistros').modal('show');
	}
}


/*
    Pages loaded
*/
$(document).ready(function () {
	var nome = 'Boa Vizinhança';
	var versao = ' v0.8';
	document.title = 'APP ' + nome + versao;
	$('.appTitulo').text(nome + versao);
});

// Encryption

function sha256(ascii) {
	function rightRotate(value, amount) {
		return (value >>> amount) | (value << (32 - amount));
	};

	var mathPow = Math.pow;
	var maxWord = mathPow(2, 32);
	var lengthProperty = 'length'
	var i, j; // Used as a counter across the whole file
	var result = ''

	var words = [];
	var asciiBitLength = ascii[lengthProperty] * 8;

	//* caching results is optional - remove/add slash from front of this line to toggle
	// Initial hash value: first 32 bits of the fractional parts of the square roots of the first 8 primes
	// (we actually calculate the first 64, but extra values are just ignored)
	var hash = sha256.h = sha256.h || [];
	// Round constants: first 32 bits of the fractional parts of the cube roots of the first 64 primes
	var k = sha256.k = sha256.k || [];
	var primeCounter = k[lengthProperty];
	/*/
	var hash = [], k = [];
	var primeCounter = 0;
	//*/

	var isComposite = {};
	for (var candidate = 2; primeCounter < 64; candidate++) {
		if (!isComposite[candidate]) {
			for (i = 0; i < 313; i += candidate) {
				isComposite[i] = candidate;
			}
			hash[primeCounter] = (mathPow(candidate, .5) * maxWord) | 0;
			k[primeCounter++] = (mathPow(candidate, 1 / 3) * maxWord) | 0;
		}
	}

	ascii += '\x80' // Append Ƈ' bit (plus zero padding)
	while (ascii[lengthProperty] % 64 - 56) ascii += '\x00' // More zero padding
	for (i = 0; i < ascii[lengthProperty]; i++) {
		j = ascii.charCodeAt(i);
		if (j >> 8) return; // ASCII check: only accept characters in range 0-255
		words[i >> 2] |= j << ((3 - i) % 4) * 8;
	}
	words[words[lengthProperty]] = ((asciiBitLength / maxWord) | 0);
	words[words[lengthProperty]] = (asciiBitLength)

	// process each chunk
	for (j = 0; j < words[lengthProperty];) {
		var w = words.slice(j, j += 16); // The message is expanded into 64 words as part of the iteration
		var oldHash = hash;
		// This is now the undefinedworking hash", often labelled as variables a...g
		// (we have to truncate as well, otherwise extra entries at the end accumulate
		hash = hash.slice(0, 8);

		for (i = 0; i < 64; i++) {
			var i2 = i + j;
			// Expand the message into 64 words
			// Used below if 
			var w15 = w[i - 15], w2 = w[i - 2];

			// Iterate
			var a = hash[0], e = hash[4];
			var temp1 = hash[7]
				+ (rightRotate(e, 6) ^ rightRotate(e, 11) ^ rightRotate(e, 25)) // S1
				+ ((e & hash[5]) ^ ((~e) & hash[6])) // ch
				+ k[i]
				// Expand the message schedule if needed
				+ (w[i] = (i < 16) ? w[i] : (
					w[i - 16]
					+ (rightRotate(w15, 7) ^ rightRotate(w15, 18) ^ (w15 >>> 3)) // s0
					+ w[i - 7]
					+ (rightRotate(w2, 17) ^ rightRotate(w2, 19) ^ (w2 >>> 10)) // s1
				) | 0
				);
			// This is only used once, so *could* be moved below, but it only saves 4 bytes and makes things unreadble
			var temp2 = (rightRotate(a, 2) ^ rightRotate(a, 13) ^ rightRotate(a, 22)) // S0
				+ ((a & hash[1]) ^ (a & hash[2]) ^ (hash[1] & hash[2])); // maj

			hash = [(temp1 + temp2) | 0].concat(hash); // We don't bother trimming off the extra ones, they're harmless as long as we're truncating when we do the slice()
			hash[4] = (hash[4] + temp1) | 0;
		}

		for (i = 0; i < 8; i++) {
			hash[i] = (hash[i] + oldHash[i]) | 0;
		}
	}

	for (i = 0; i < 8; i++) {
		for (j = 3; j + 1; j--) {
			var b = (hash[i] >> (j * 8)) & 255;
			result += ((b < 16) ? 0 : '') + b.toString(16);
		}
	}
	return result.toUpperCase();
};


// $(document).ready(function () {
// 	var password = '123456789';
// 	passwordSha256 = sha256(password);
// 	console.log('password  encrypted:', passwordSha256);

// });