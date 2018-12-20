//Cookieセット
function set_recent_cookie(pid,name,img_url,i) {
	alert('recent.js ready');
  // 1書き込み　0消去
  if(i==1) {
    strCookie=$.cookie('recent');
    if(strCookie==null) {
      strCookie="";
    }
    else{
      loop=strCookie.match(/`/g)
      if(loop!=null && loop.length/4 >= 6){//6個保存
        //古い方から消す
        loc=strCookie.indexOf("`", strCookie.indexOf("`", strCookie.indexOf("`", strCookie.indexOf("`")+1)+1)+1);
        strCookie=strCookie.slice(loc+1);
        $.cookie('recent', strCookie ,{expires:365});
      }
    }
    strURL="`"+pid+"`"+name+"`"+img_url+"`";
    alert('URL要素をコンソールに表示');
    console.log(pid);
    console.log(name);
    console.log(img_url);
    if(strCookie==null || strCookie=="") {
      strCookie=strURL;
      $.cookie('recent', strCookie,{expires:365});
    }
    else {
      strCookie=strCookie+strURL;
      $.cookie('recent', strCookie,{expires:365});
    }
  }
  else {
    $.cookie('recent','',{expires:-1});
    document.getElementById("recent_item").style.display='none';
  }
}

//最近チェックした商品リスト表示
function load_recentlist() {
  loc = new Array();

  //Cookie読み込み
  strCookie=$.cookie('recent'); 
  if(strCookie==null) {
    strCookie="";
  }
  loop=strCookie.match(/`/g);

  if(loop!=null){
    if(loop.length/4>6){
      n=6;
    }else{
      n=loop.length/4;
    }
  }
  else {
    n=0;
  }

  if(n !=0 ){
    loc[0]=strCookie.indexOf("`");
    loc[1]=strCookie.indexOf("`",loc[0]+1);
    loc[2]=strCookie.indexOf("`",loc[1]+1);
    loc[3]=strCookie.indexOf("`",loc[2]+1);
    str1=strCookie.substring(loc[0]+1,loc[1]);//商品ID
    str2=strCookie.substring(loc[1]+1,loc[2]);//商品名
    str3=strCookie.substring(loc[2]+1,loc[3]);//商品画像URL
    //この下の行は表示部分なので各自
    strOut="<div style='height:43px;overflow:hidden;margin-bottom:8px;width:100%;'><div style='height:43px;width:47px;float:left;'><a href='./?pid="+str1+"'><img style='width:43px;' src='"+str3+"'></a></div><div style='height:43px;'><a href='./?pid="+str1+"'>"+str2+"</a></div></div><div style='clear:both;height:1px;'><img src='http://img.shop-pro.jp/tmpl_img/11/sp.gif'></div>";
  }
  for(i=1;i<n;i++){
    loc[i*4]=strCookie.indexOf("`",loc[i*4-1]+1);
    loc[i*4+1]=strCookie.indexOf("`",loc[i*4]+1);
    loc[i*4+2]=strCookie.indexOf("`",loc[i*4+1]+1);
    loc[i*4+3]=strCookie.indexOf("`",loc[i*4+2]+1);
    str1=strCookie.substring(loc[i*4]+1,loc[i*4+1]);//商品ID
    str2=strCookie.substring(loc[i*4+1]+1,loc[i*4+2]);//商品名
    str3=strCookie.substring(loc[i*4+2]+1,loc[i*4+3]);//商品画像URL
    //この下の行は表示部分なので各自
    strOut="<div style='height:43px;overflow:hidden;margin-bottom:8px;width:100%;'><div style='height:43px;width:47px;float:left;'><a href='./?pid="+str1+"'><img style='width:43px;' src='"+str3+"'></a></div><div style='height:43px;'><a href='./?pid="+str1+"'>"+str2+"</a></div></div><div style='clear:both;height:1px;'><img src='http://img.shop-pro.jp/tmpl_img/11/sp.gif'></div>"+strOut;
  }
  if(n !=0 ){
    document.getElementById("recent_item").innerHTML=strOut;
  }
}