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
        $('#icon-download').on('click', function () {
            _this.download();
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
        const id = $('#id').val();
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
    file: function() {

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
    },
    download: function (){

        const fileName = $("#fileName").text();
        Url = "/api/fileSystem/" + fileName;

        $.ajax({
            type: 'GET',
            url: Url,
            xhrFields: {  //response 데이터를 바이너리로 처리한다.
                responseType: 'blob'
            },
            success : function(data) {
                console.log("완료");
                let blob = new Blob([data]);
                //파일저장
                if (navigator.msSaveBlob) {
                    return navigator.msSaveBlob(blob, url);
                } else {
                    let link = document.createElement('a');
                    link.href = window.URL.createObjectURL(blob);
                    link.download = Url;
                    link.click();
                }
            }
        })
    }
};
board.init();