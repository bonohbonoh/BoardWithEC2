
var main = {
    init : function (){
        var _this = this;
        $('#btn-registration').on('click',function (){
            _this.save();
        });
        $('#btn-update').on('click', function () {
            _this.update();
        });

        $('#btn-delete').on('click', function () {
            _this.delete();
        });
    },

    save : function (){
        var data = {
         title : $('#title').val(),
         author: $('#author').val(),
         contents: $('#content').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/board',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function (){
            alert('작성 완료');
            window.location.href='/';
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    },
    update : function (){
        var data = {
            title: $('#title').val(),
            contents: $('#content').val(),
        };
        var boardId = $('#boardId').val();
        $.ajax({
            type: 'PUT',
            url: '/api/v1/board/'+boardId,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function (){
            alert('수정 완료');
            window.location.href='/';
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    },
    delete: function (){
        var boardId = $('#boardId').val();
        $.ajax({
            type: 'DELETE',
            url: '/api/v1/board/'+boardId,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
        }).done(function (){
            alert('삭제 완료');

            window.location.href='/';
        }).fail(function (error){
            alert(JSON.stringify(error));
        });
    }
};

main.init();
