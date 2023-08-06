let comment = {
    init: function () {
        let _this = this;
        $('#btn-comment-save').on('click', function () {
            _this.commentSave();
        });
        $('#btn-comment-update').on('click', function () {
            _this.commentUpdate();
        });
        $('#btn-comment-delete').on('click', function () {
            _this.commentDelete();
        });

    },
    commentSave: function () {
        let id = $('#id').val();
        let data = {
            comment: $('#comment').val(),
            commentWriter: $('#commentWriter').val(),
            board: {
                id: id
            }
        };
        $.ajax({
            type: 'POST',
            url: '/api/board/' + id + '/comment' ,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8,',
            data: JSON.stringify(data),
        }).done(function () {
            alert("등록");
            window.location.href = "/post/view/" + id;
        }).fail(function () {
            alert("실패");
            console.log(data)
        })
    },
    commentUpdate: function () {

        let id = $('#id').val();
        let commentId = $('#commentId').val();
        let data = {
            comment: $('#comment').val(),
            commentWriter: $('#commentWriter').val(),
            board: {
                id: id
            }
        };
        $.ajax({
            type: 'PUT',
            url: '/api/board/' + id + '/comment/' + commentId ,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8,',
            data: JSON.stringify(data),
        }).done(function () {
            alert("수정");
            window.location.href = "/post/view/" + id;
        }).fail(function () {
            alert("실패");
            console.log(data)
        })
    },
    commentDelete: function (){
        let id = $('#id').val();
        let commentId = $('#commentId').val();
        $.ajax({
            type: 'DELETE',
            url: '/api/post/' + id + '/comment/' + commentId,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8,'
        }).done(function() {
            alert("삭제");
            window.location.href = "/post/view/" + id;
        }).fail(function (){
            alert("실패");
        })
    }

};
comment.init();