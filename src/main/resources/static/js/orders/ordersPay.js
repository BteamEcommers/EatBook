function movePage(page) {
        const options = 'width=500, height=500, top=150, left=600, scrollbars=yes'

         var link = '/orders/' + 'pay' + page+'\'';

		window.open(link,'_blank',options)
	}

function pclose1(){
// 팝업을 닫고 부모페이지는 location으로 이동
//    opener.parent.location='http://localhost:8010/event/list';
    opener.parent.location.reload();
    window.close();
}

