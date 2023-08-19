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
    file: function(e) {

        const file = $("#file")[0].files[0];
        const formData = new FormData();
        formData.append("file", file);

        $.ajax({
            type: 'POST',
            url: "/api/fileSystem",
            data: formData,
            contentType: false,
            processData: false,
        }).done(function() {
            // 파일 업로드 성공 시 동작
            alert("파일업로드 완료");
        }).fail(function(xhr, status, error) {
            // 파일 업로드 실패 시 동작
            console.log("Ajax 요청 실패:", error);
            alert("파일업로드 실패");
        });
    }
};
board.init();