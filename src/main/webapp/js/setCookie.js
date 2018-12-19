function setCookie(){

	if(document.cookie){

		var infos = document.cookie.replace('itemInfo=', '');
		var infosAry = infos.split(',');
		
		var url = location.href;
		url = encodeURIComponent(url);

		var array = [];

		for(var i = 0; i < infosAry.length; i++){
			if(infosAry[i].match(url)){
				continue;
			}else{
				if(i > 5){break;}
				array.push(infosAry[i]);
			}

		}
	}else{
		var array = [];
	}

	var itemInfos = '';
	var itemUrl  = location.href;
	var itemName  = encodeURIComponent('<div><a href="'+itemUrl+'">')+encodeURIComponent($('#itemName').html())+encodeURIComponent('</a></div>');
	var itemImg  = encodeURIComponent('<div><a href="'+itemUrl+'">')+encodeURIComponent($('#img').html())+encodeURIComponent('</a></div>');
	array.unshift(itemImg + itemName);
	var itemInfos = 'itemInfo=' + array;
	
	var c = '';
	c += array;