/*
    WS functions
*/
function runWS(requestURL, callback) {
    var rawFile = new XMLHttpRequest();
    rawFile.overrideMimeType("application/json");
    rawFile.open("GET", requestURL, true);
    rawFile.onreadystatechange = function() {
        if (rawFile.readyState === 4 && rawFile.status == "200") {
            callback(rawFile.responseText);
        }
    }
    rawFile.send(null);
}

function popularItens(jsonObj) {

	var listaNode = document.getElementById('divListItens');

	if (jsonObj !== undefined) {
		for (var i = 0; i < jsonObj.length; i++) {
			if (i > 0) {
				var itemNode = document.getElementById('divItem');
				var clone   = itemNode.cloneNode(true);
				itemNode.className += ' clonedrow';
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
		else
		{
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
	if (tipo == '2')
	{
		result = '<i class="fas fa-money-bill-alt" style="font-size: 25px; color: green"></i> R$ ' + valor + ',00';
	}
	return result;
}


/*
    Cookie functions
*/
function setCookie(cnome,cvalor,exdias) {
	var d = new Date();
	d.setTime(d.getTime() + (exdias*24*60*60*1000));
	var expires = "expires=" + d.toGMTString();
	document.cookie = cnome+"="+cvalor+"; "+expires+"; SameSite=None; Secure";
}

function getCookie(cnome) {
	var name = cnome + "=";
	var ca = document.cookie.split(';');
	for(var i=0; i<ca.length; i++) {
		var c = ca[i];
		while (c.charAt(0)==' ') c = c.substring(1);
		if (c.indexOf(name) == 0) {
			return c.substring(name.length, c.length);
		}
	}
	return "";
}

function checkCookie() {
	var cnome=getCookie("appBV");
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
	var senha = $('#txtSenha').val();

	if ((email == 'usuario@email.com.br') && (senha == '12345')) {
		setCookie("appBV", email, 1);
		window.location.href = "Itens.htm";
	}
	else
	{
		$('.modal-login-invalido').modal('show');
	}
});


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

		runWS('http://localhost:8080/items/search/' + pesq, function(text){
			var data = JSON.parse(text);
			if (data.length > 0) {
				var clonedRows = document.querySelectorAll('.clonedrow');
				for (var i = 0; i < clonedRows.length; i++) {
					clonedRows[i].parentNode.removeChild(clonedRows[i]);
				}

				popularItens(data);

				$('.modal-carregando').modal('hide');
			}
			else
			{
				$('.modal-carregando').modal('hide');
				$('.modal-pesquisar-semregistros').modal('show');
			}

			$('#btnPesquisar').prop("disabled", false).html('<i class="fas fa-search" style="font-size: 20px;"></i>');
		});
	}
	else
	{
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

	runWS('http://localhost:8080/items/findAll', function(text){
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

	if (urlParams.has("id") === true)
	{
		idItem = urlParams.get("id");

		runWS('http://localhost:8080/items/findItem/' + idItem, function(text){
			var data = JSON.parse(text);

			if (data !== undefined) {
				detalheItem(data, idItem);

				$('.modal-carregando').modal('hide');
			}
			else
			{
				$('.modal-carregando').modal('hide');
				$('.modal-detalhe-semregistros').modal('show');
			}
		});
	}
	else
	{
		$('.modal-carregando').modal('hide');
		$('.modal-detalhe-semregistros').modal('show');
	}
}


/*
    Pages loaded
*/
$(document).ready(function ()
{
	var nome = 'Boa Vizinhança';
	var versao = ' v0.8';
    document.title = 'APP ' + nome + versao;
    $('.appTitulo').text(nome + versao);
});