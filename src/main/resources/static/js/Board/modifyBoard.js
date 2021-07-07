let main = () => {
    modifyBoard();
    moveToBack();
}


let modifyBoard = () => {
    const URL = "/board/modifyBoard"
    let boardUid = new URLSearchParams(location.search).get("uid")*1;
    
    $("#modifyBtn").click(() => {

        let params = new URLSearchParams();

        params.append('BOARD_UID', boardUid);
        params.append('TITLE', $("#inputTitle").val());
        params.append('CONTENT', $("#inputContent").val());

        axios.put(`${URL}`, params
        ).then((res) => {

            if (res.data.result) {
    
                window.location.href = "/board/boardList.do"
    
            } else {
    
                alert(res.data.msg);
    
            }
        }).catch((e) => {

            console.log(JSON.stringify(e));

        })
    })
}

let moveToBack = () => {

    $("#backBtn").click(() => {

        window.history.back();

    });
}

main();