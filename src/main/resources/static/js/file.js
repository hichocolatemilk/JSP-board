let file ={
    init: function () {
        let _this = this;
        $('#btn-file').on('click', function () {
            _this.file();
        });
        $('#icon-download').on('click', function () {
            _this.download();
        });
        $('#btn-file-delete').on('click', function () {
            _this.delete();
        });
        $('#btn-file-update').on('click', function () {
            _this.update();
        });
    },
    file: function() {

        const boardId = $('#boardId').val();

        const file = $("#file")[0].files[0];
        const formData = new FormData();
        formData.append("file", file);

        $.ajax({
            type: 'POST',
            url: '/api/board/'+ boardId +'/fileSystem',
            data: formData,
            contentType: false,
            processData: false,
        }).done(function() {
            // 파일 업로드 성공 시 동작
            alert("파일업로드 완료");
            window.location.href = "/board/view/" + boardId;
        }).fail(function(xhr, status, error) {
            // 파일 업로드 실패 시 동작
            console.log("Ajax 요청 실패:", error);
            alert("파일업로드 실패");
            console.log(formData.get("file"));
        });
    },
    download: function (){

        const fileName = $("#fileName").text();
        Url = '/api/board/fileSystem/' + fileName;

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
                let link = document.createElement('a');
                link.href = window.URL.createObjectURL(blob);
                link.download = fileName;
                link.click();

            }
        })
    },
    delete: function (){
        const fileId = $('#fileId').val();
        const boardId = $('#boardId').val();
        $.ajax({
            type: 'DELETE',
            url: '/api/board/'+ boardId +'/fileSystem/' + fileId,
            dataType: 'json',
            contentType: 'application/json; charset=utf-8,',
        }).done(function() {
            alert("삭제");
            window.location.href = "/board/view/" + boardId;
        }).fail(function (){
            alert("실패");
        })
    },
    update: function() {
        const fileId = $('#fileId').val(); // 수정할 파일의 ID를 얻어옴
        const boardId = $('#boardId').val();
        const file = $("#file")[0].files[0];
        const formData = new FormData();
        formData.append("file", file);

        $.ajax({
            type: 'PUT', // PUT 요청
            url: '/api/board/'+ boardId +'/fileSystem/' + fileId,
            data: formData,
            contentType: false,
            processData: false,
        }).done(function() {
            // 파일 수정 성공 시 동작
            alert("파일 수정 완료");
            window.location.href = "/board/view/" + boardId;
        }).fail(function(xhr, status, error) {
            // 파일 수정 실패 시 동작
            console.log("Ajax 요청 실패:", error);
            alert("파일 수정 실패");
        });
    }
}
file.init();
