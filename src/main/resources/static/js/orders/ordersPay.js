function pclose2(){
// 팝업을 닫고 부모페이지는 location으로 이동
//    opener.parent.location='http://localhost:8010/event/list';
    opener.parent.location.reload();
    window.close();
}

