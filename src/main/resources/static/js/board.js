let board = {
    init: function () {
        let _this = this;
        $('#btn-save').on('click', function () {
            _this.save();
        });
        $('#btn-update').on('click', function () {
            _this.update();
        });
        $('#btn-delete').on('click', function () {
            _this.delete();
        });
        $('#btn-file').on('click', function () {
            _this.file();
        });
    },
    save: function (){
        let data = {
            title: $('#title').val(),
            content: $('#content').val(),
            writer: $('#writer').val(),
        };
        $.ajax({
            type: 'POST',
            url: '/api/board',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8,',
            data:JSON.stringify(data),
        }).done(function() {
            alert("등록");
            window.location.href = "/";
        }).fail(function (){
            alert("실패");
        })
    },
    update: function (){
        let data = {
            title: $('#title').val(),
            content: $('#content').val()
        };
        let id = $('#id').val();
        $.ajax({
            type: 'PUT',
            url: '/api/board/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8,',
            data: JSON.stringify(data),
        }).done(function() {
            alert("수정");
            window.location.href = "/";
        }).fail(function (){
            alert("실패");
        })
    },
    delete: function (){
        let id = $('#id').val();
        $.ajax({
            type: 'DELETE',
            url: '/api/board/' + id,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8,',
        }).done(function() {
            alert("삭제");
            window.location.href = "/";
        }).fail(function (){
            alert("실패");
        })
    },
    file: function(){
        const file = $("#file")[0].files[0];

        const formData = new FormData();
        formData.append("file", file);
        formData.append("json", new Blob([JSON.stringify(data)], {type: "application/json"}));

        $.ajax({
            url: "/api/fileSystem",
            type: 'POST',
            data: formData,
            contentType: false,
            processData: false,
            enctype: "multipart/form-data",
            success: function(result) {
                alert("등록 성공");
                location.reload();
            },
            error: function(xhr) {
                alert("등록 실패")
                console.log(xhr);
            }
        });

    }
};
board.init();