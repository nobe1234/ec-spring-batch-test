function checkNijyuSubmit(){
  var obj = document.getElementById("btnSubmit");
  if(obj.disabled){
    //ボタンがdisabledならsubmitしない
    return false;
  }else{
    //ボタンがdisabledでなければ、ボタンをdisabledにした上でsubmitする
    obj.disabled = true;
    return true;
  }
}