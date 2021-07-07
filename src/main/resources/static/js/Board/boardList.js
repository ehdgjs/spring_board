let main = () => {

    // 게시글 작성페이지로 이동
    $("#createBtn").click(() => {
        moveToCreateBoard();
     });
}

let moveToCreateBoard = () => {
    location.href = "/board/createBoard.do";
}

let selCntPerPage = () => {
    let cntPerPage = $("#selCntPerPage").val();

    location.href = "/board/boardList.do?curPageNo=1&cntPerPage="+cntPerPage;
}

let moveToPaging = (curPageNo) => {

    let cntPerPage = new URLSearchParams(location.search).get('cntPerPage');

    if (cntPerPage == null) {
        location.href = "/board/boardList.do?curPageNo="+curPageNo;    
    } else {
        location.href = "/board/boardList.do?curPageNo="+curPageNo+"&cntPerPage="+cntPerPage;
    }
}

let detailBoard = (boardUid) => {
    location.href = "/board/boardDetail.do?uid="+boardUid;
}

let createLayerBoard = (boardUid) => {
    location.href = "/board/createLayerBoard.do?uid="+boardUid;
}

main();