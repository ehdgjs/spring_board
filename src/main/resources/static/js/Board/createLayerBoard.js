let main = () => {

    $("#createSubmitBtn").click(() => {
        createLayerBoard();
    });

    // 돌아가기
    $("#moveToBackBtn").click(() => {
        moveToBack();
    });

}

let moveToBack = () => {

    window.history.back();

}

let createLayerBoard = () => {

    const URL = "/board/createLayerBoard"

    let body = new URLSearchParams();

    let uid = new URLSearchParams(location.search).get('uid')*1;

    body.append("ORIGIN_NO", uid);
    body.append("TITLE", $("#inputTitle").val());
    body.append("CONTENT", $("#inputContent").val());

    axios.post(`${URL}`, body
    ).then(res => {
        if (res.data.result) {

            location.href = "/board/boardList.do";

        } else {
            
            alert(res.data.msg);

        }
    })
    
}


main();