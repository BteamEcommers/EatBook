/*<![CDATA[*/
function movePage(bookId) {
        const options = 'width=500, height=500, top=150, left=600, scrollbars=yes'
        var page = bookId

         var link = '/orders/' + 'pay/' +page;

		window.open(link,'_blank',options)
	}
/*]]>*/
