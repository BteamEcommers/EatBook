$(document).ready(function () {
    $(".cartItemDelete").click(function () {
        var bookId = this.id.split('_')[1];

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
                        url: '/cartitem/delete/' + bookId,
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
function deleteSelectedItems() {
    var form = document.getElementById('deleteForm');
    var selectedItems = [];
    var checkboxes = document.querySelectorAll('.rowCheckBox:checked');

    checkboxes.forEach(function(checkbox) {
        selectedItems.push(checkbox.value);
    });

    showDeleteConfirmation(form, selectedItems);
}

function deleteSelectedItem(itemId) {
    showDeleteConfirmation(form, [itemId]);
}

function showDeleteConfirmation(form, selectedItems) {
    if (selectedItems.length === 0) {
        Swal.fire({
            icon: 'info',
            title: '장바구니',
            text: '선택한 항목이 없거나 장바구니가 비어있습니다.'
        });
    } else {
        Swal.fire({
            title: '장바구니에서',
            text: '선택한 항목을 삭제하시겠습니까?',
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: '삭제',
            cancelButtonText: '취소'
        }).then((result) => {
            if (result.isConfirmed) {
                selectedItems.forEach(function(itemId) {
                    var input = document.createElement('input');
                    input.type = 'hidden';
                    input.name = 'selectedItems';
                    input.value = itemId;
                    form.appendChild(input);
                });
                Swal.fire({
                    title: '삭제가 완료되었습니다.',
                    icon: 'success',
                    showConfirmButton: true
                }).then(() => {
                    form.submit();
                });
            } else {
                window.location.href = '/cartitem/list';
            }
        });
    }
}
function allChecked() {

   var isChecked = document.getElementById("allCheckBox").checked;
   var checkboxes = document.getElementsByClassName("rowCheckBox");
   for (var i = 0; i < checkboxes.length; i++) {
           checkboxes[i].checked = isChecked;
   }
}

function allChecked2() {

     var isChecked = document.getElementById("allCheckBox2").checked;
     var checkboxes = document.getElementsByClassName("rowCheckBox");
     for (var i = 0; i < checkboxes.length; i++) {
            checkboxes[i].checked = isChecked;
     }
}
