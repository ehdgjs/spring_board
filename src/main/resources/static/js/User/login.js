let main = () => {

    // 로그인
    $("#loginBtn").click(() => {
        processLogin();
    })

    // 회원가입으로 이동
    $("#signUpBtn").click(() => {
        moveToSignUp();
    })
}

let processLogin = () => {
    const URL = "/user/login"

    let body = new URLSearchParams();

    body.append("USER_ID", $("#inputId").val());
    body.append("PASSWORD", $("#inputPassword").val());

    axios.post(`${URL}`, body
    ).then(res => {
        if (res.data.result == 1) {
            
            window.location.href="/board/boardList.do"

        } else {

            alert(res.data.msg);

        }
    })
}

let moveToSignUp = () => {
    window.location.href="/user/signUp.do"
}

main();