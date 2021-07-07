let main = () => {

    // 게시글 생성
    $("#createSubmitBtn").click(() => {
        submitCreateBoard();
    });

    // 취소 버튼
    $("#moveToBackBtn").click(() => {
        moveToBack();
    });

}

let submitCreateBoard = () => {
    const URL = "/board/createBoard"
    
    const datas = new URLSearchParams();

    let title = $("#inputTitle").val();
    let content = $("#inputContent").val();

    if (title == "") {
        alert("제목을 입력해주세요");
        $("#inputTitle").focus();
        return;
    }
    if (content == "") {
        alert("내용을 입력해주세요");
        $("#inputContent").focus();
        return;
    }

    datas.append('TITLE', title);
    datas.append('CONTENT', content);

    axios.post(`${URL}`, 
        datas
    ).then(res => {
        if(res.data.result) {

            window.location.href= "/board/boardList.do";

        } else {

            alert(res.data.msg);

        }
    })
}

let moveToBack = () => {

    history.back();
    
}

main();