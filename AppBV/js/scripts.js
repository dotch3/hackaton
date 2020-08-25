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

	if (jsonObj !== undefined) {
		for (var i = 0; i < jsonObj.length; i++) {
			if (i > 0) {
				var itemNode = document.getElementById('divItem');
				var listaNode = document.getElementById('divListItens');
				var clone   = itemNode.cloneNode(true);
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
				document.getElementsByClassName("pub_data")[i].innerHTML = 'H&aacute; ' + jsonObj[i].dataSugestaoDevolucao;
				document.getElementsByClassName("pub_reputacao")[i].innerHTML = '4.0';
				document.getElementsByClassName("pub_valor")[i].innerHTML = tipoMoeda(jsonObj[i].tipoValor, jsonObj[i].valor);
			}
		}
	}
}

function detalheItem(jsonObj, idItem) {
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
    Pages loaded functions
*/
$(document).ready(function ()
{
	var nome = 'Boa Vizinhança';
	var versao = ' v0.8';
    document.title = 'APP ' + nome + versao;
    $('.appTitulo').text(nome + versao);
});