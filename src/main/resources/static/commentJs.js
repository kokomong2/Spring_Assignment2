$(document).ready(function () {
    hello();
})

function add_comments(){
    alert("댓글 저장!")
    // let comments = $('#user_comment').val();
    // let data={"memoid" : memoid,"comments":comments};
    //
    // $.ajax({
    //     type:"POST",
    //     url:"/api/memos/comments",
    //     contentType: "application/json",
    //     data: JSON.stringify(data),
    //     success: function (response) {
    //
    //     }
    //
    // })
}
function hello(){
    alert("댓글페이지에요!")
}