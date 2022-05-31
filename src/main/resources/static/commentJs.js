let static_comment_username = "comment_user_name";

$(document).ready(function () {

    const searchParams = new URL(location.href).searchParams;
    const memoIdparam = searchParams.get('memoid');
    getComments(memoIdparam);
})

function add_comments(memoId){

    let comments = $('#user_comment').val();
    let data={"memoId" : memoId,"comments":comments};

    $.ajax({
        type: "POST",
        url: "/api/memos/comments",
        contentType: "application/json",
        data: JSON.stringify(data),
        success: function (response) {
            alert('댓글이 성공적으로 저장되었습니다.');
            window.location.reload();
        }
    });
}
function getComments(memoId){

    $('#users_comments').empty();
    $.ajax({
        type: "GET",
        url: `/api/comments/${memoId}`,
        success: function (response) {
            for (let i = 0; i < response.length; i++) {
                let comment = response[i];
                let comment_contents=comment.comments;
                getCommentsUsername(comment.id);
                let comment_username = static_comment_username;
                addCommentHTML(comment_contents,comment_username);
            }
        }
    });
}
function addCommentHTML(comment_contents,comment_username) {
    let tempHtml = makeComment(comment_contents,comment_username);
    $('#users_comments').append(tempHtml);
}

function makeComment(comment_contents,comment_username) {
    return `<div class="users_comments" id="users_comments" style="margin-top: 15px;">
                <div class="card" style="box-shadow: 2px 2px 10px rgba(0, 0, 0, 0.3);">
                    <!-- date/username 영역 -->
                    <div class="metadata" style="margin-bottom: 10px;">
                        <div class="username" style="margin-right: 5px;">
                            ${comment_username}
                        </div>
                    </div>
                    <!-- contents 조회/수정 영역-->
                    <div class="contents">
                        <div  class="text" style="display: inline-block; width: 500px; white-space: nowrap; overflow: hidden; text-overflow: ellipsis;">
                            ${comment_contents}
                        </div>
                        <div class="edit">
                            <textarea class="te-edit" name="" id="" cols="30" rows="5"></textarea>
                        </div>
                    </div>
                    <!-- 버튼 영역-->
                    <div class="footer">
                    </div>
                </div>
            </div>`;
}
function getCommentsUsername(id){
    $.ajax({
        type:"GET",
        url:`/api/comments/username/${id}`,
        async:false,
        success:function(response){
            static_comment_username=response;
        }
    })
}