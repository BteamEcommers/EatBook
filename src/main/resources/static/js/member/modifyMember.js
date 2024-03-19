$().ready(function () {
        $("#modifyBtn").click(function () {
            const Toast = Swal.mixin({
                toast: true,
                position: 'center-center',
                showConfirmButton: false,
                timer: 3000,
                timerProgressBar: true
            })

            Toast.fire({
                icon: 'success',
                title: '회원정보를 수정하셨습니다.'
            })
        });
    });