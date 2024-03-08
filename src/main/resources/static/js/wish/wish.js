$(document).ready(function () {
    function confirmAction(title, text, url) {
        return Swal.fire({
            title: title,
            text: text,
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: '확인',
            cancelButtonText: '취소',
            preConfirm: () => {
                return new Promise((resolve) => {
                    $.ajax({
                        url: url,
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
        });
    }

    $(".wishDelete").click(function () {
        var bookId = this.id.split('_')[1];

        confirmAction('찜 목록에서', '정말 삭제하시겠습니까?', '/wish/delete/' + bookId)
            .then((result) => {
                if (result.isConfirmed) {
                    Swal.fire(
                        '삭제가 완료되었습니다.',
                        '',
                        'success'
                    ).then(() => {
                        window.location.href = '/wish/list';
                    });
                }
            });
    });

    $(".addCartItem").click(function () {
        var bookId = this.id.split('_')[1];

        confirmAction('장바구니에', '해당 상품을 담으시겠습니까?', '/cartitem/add/' + bookId)
            .then((result) => {
                if (result.isConfirmed) {
                    Swal.fire(
                        '장바구니에 상품을 담았습니다.',
                        '',
                        'success'
                    ).then(() => {
                        window.location.href = '/wish/list';
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

    showDeleteConfirmation(selectedItems);
}

function deleteSelectedItem(itemId) {
    showDeleteConfirmation([itemId]);
}

function showDeleteConfirmation(selectedItems) {
    var form = document.getElementById('deleteForm');

    if (selectedItems.length === 0) {
        Swal.fire({
            icon: 'info',
            title: '찜 목록에서',
            text: '선택한 항목이 없거나 찜이 비어있습니다.'
        });
    } else {
        Swal.fire({
            title: '찜 목록에서',
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