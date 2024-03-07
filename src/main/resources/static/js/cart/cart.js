$(document).ready(function () {
    $(".cartItemDelete").click(function () {
        var eventId = this.id.split('_')[1];

        Swal.fire({
            title: '장바구니 에서',
            text: '정말 삭제하시겠습니까?',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: '삭제',
            cancelButtonText: '취소',
            preConfirm: () => {
                return new Promise((resolve) => {
                    // Ajax 요청을 통한 서버에서의 삭제 작업
                    $.ajax({
                        url: '/cartitem/delete/' + eventId,
                        type: 'GET',
                        success: function (data) {
                              resolve(true);
                        },
                        error: function () {
                            resolve(false);
                        }
                    });
                });
            }
        }).then((result) => {
            if (result.isConfirmed) {
                // SweetAlert2 확인 버튼을 누르면 실행되는 부분
                Swal.fire(
                    '삭제가 완료되었습니다.',
                    '',
                    'success'
                ).then(() => {
                    window.location.href = '/cartitem/list';
                });
            }
        });
    });
});