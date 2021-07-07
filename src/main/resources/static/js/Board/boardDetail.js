let main = () => {

    // 수정 버튼
    $("#modifyBoardBtn").click(() => {
        modifyBoard();
    });

    // 삭제 버튼
    $("#deleteBoardBtn").click(() => {
        deleteBoard();
    });

    // 취소 버튼
    $("#moveToBackBtn").click(() => {
        moveToBack();
    });

    // 댓글 작성
    $("#createComment").click(() => {
        createComment();
    });

    // 댓글 수정 활성화
    $(".modifyCommentBtn").click((e) => {
        modifyStatus(e);
    })

    // 댓글 수정 댓글 UID 가져오기
    $(".modifyComment").click((e) => {
        modifyComment(e);
    })

}

let modifyBoard = () => {
    let boardUid = new URLSearchParams(location.search).get('uid')*1;

    const URL = "/board/selectAuth?uid="+boardUid;

    axios.get(`${URL}`).then(res => {

        if (res.data) {

            window.location.href = "/board/modifyBoard.do?uid="+boardUid; 

        } else {

            alert("해당 게시글을 수정할 권한이 없습니다.");
            
        }
    });
    
}

let deleteBoard = () => {

    let boardUid = new URLSearchParams(location.search).get('uid')*1;

    const URL = '/board/deleteBoard';

    const datas = new URLSearchParams();

    datas.append("BOARD_UID", boardUid);

    axios.post(`${URL}`, datas
    ).then(res => {

        if(res.data.result){

            location.href="/board/boardList.do";

        } else {

            alert(res.data.msg);

        }
    })

}

let movePaging = (clickPage) => {

    const uid = new URLSearchParams(location.search).get("uid");

    location.href = "/board/boardDetail.do?uid="+ uid + "&curPageNo=" + clickPage;
}

let modifyStatus = (e) => {
    let target = $(e.target).parent().parent().children();

    target.eq(1).hide();
    target.eq(2).show();
}

let modifyComment = (e) => {
    const URL = "/comment/modifyComment"

    let body = new URLSearchParams();

    console.log($(e.target));

    let commentUid= $(e.target).prev().prev().prev().val();
    let content = $(e.target).prev().val();

    body.append("COMMENT_UID", commentUid);
    body.append("CONTENT", content);

    axios.put(`${URL}`, body
    ).then(res => {
        if (res.data.result) {

            location.reload();

        } else {

            alert(res.data.msg);

        }
    })
}

let deleteComment = (commentUid) => {
    
    const URL = "/comment/deleteComment";

    let body = new URLSearchParams();

    body.append("COMMENT_UID", commentUid);

    axios.post(`${URL}`,body
    ).then(res => {
        if (res.data.result) {

            location.reload();

        } else {
            alert(res.data.msg);
        }
    })

}

let moveToBack = () => {

    location.href= "/board/boardList.do";

}

let createComment = () => {
    const URL = "/comment/createComment";

    let body = new URLSearchParams();

    let uid = new URLSearchParams(location.search).get("uid")*1;

    body.append("BOARD_UID", uid);
    body.append("CONTENT", $("#commentContent").val());

    axios.post(`${URL}`, body
    ).then(res => {
        location.reload();
    })
}

main();