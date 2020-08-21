var requestURL = 'https://raw.githubusercontent.com/reinaldots/boavizinhanca/master/itens.json';

var request = new XMLHttpRequest();
request.open('GET', requestURL);
request.responseType = 'json';
request.send();

request.onload = function() {
	var listItens = request.response;
	popularItens(listItens);
}


function popularItens(jsonObj) {
  //var myH1 = document.createElement('h1');
  //myH1.textContent = jsonObj['squadName'];
  //header.appendChild(myH1);

  //var myPara = document.createElement('p');
  //myPara.textContent = 'Hometown: ' + jsonObj['homeTown'] + ' // Formed: ' + jsonObj['formed'];
  //header.appendChild(myPara);
  /*
  document.getElementsByClassName("pub_titulo")[0].innerHTML = jsonObj['titulo'][0];
  document.getElementsByClassName("pub_descricao")[0].innerHTML = jsonObj['descricao'][0];
  document.getElementsByClassName("pub_data")[0].innerHTML = 'H&aacute; ' + jsonObj['dataPublicacao'][0];
  document.getElementsByClassName("pub_reputacao")[0].innerHTML = jsonObj['reputacao'][0];
  document.getElementsByClassName("pub_valor")[0].innerHTML = jsonObj['valor'][0];

  var seuNode = document.getElementById('divItem');
  var clone   = seuNode.cloneNode(true);
  seuNode.appendChild(clone);

  var x = document.getElementsByClassName('divItem');

*/

var numItem = 0;
$.each(jsonObj, function () {

	if (numItem > 0) {
	  var itemNode = document.getElementById('divItem');
	  var listaNode = document.getElementById('divListItens');
	  var clone   = itemNode.cloneNode(true);
	  listaNode.appendChild(clone);
	}

    // 'this' is the Object you are iterating over
    if (this.titulo !== undefined) {

		document.getElementsByClassName("pub_imagem")[numItem].innerHTML = '<img src="' + this.imgUrl + '" width="60" height="60" />';
		document.getElementsByClassName("pub_titulo")[numItem].innerHTML = this.titulo;
		document.getElementsByClassName("pub_descricao")[numItem].innerHTML = this.descricao;
		document.getElementsByClassName("pub_data")[numItem].innerHTML = 'H&aacute; ' + this.dataPublicacao;
		document.getElementsByClassName("pub_reputacao")[numItem].innerHTML = this.reputacao;
		document.getElementsByClassName("pub_valor")[numItem].innerHTML = this.valor;
		numItem++;
    }
});


/*
if (document.getElementsByClassName("pub_titulo").length > 0) {
  document.getElementsByClassName("pub_titulo")[1].innerHTML = 'Ok Ok';
  document.getElementsByClassName("pub_descricao")[1].innerHTML = jsonObj['descricao'];
  document.getElementsByClassName("pub_data")[1].innerHTML = 'H&aacute; ' + jsonObj['dataPublicacao'];
  document.getElementsByClassName("pub_reputacao")[1].innerHTML = jsonObj['reputacao'];
  document.getElementsByClassName("pub_valor")[1].innerHTML = jsonObj['valor'];
*/


}


