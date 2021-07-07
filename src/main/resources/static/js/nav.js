let navInit = () => {

    // 로그인 버튼 클릭 시
    $("#loginBtn").click(() => {
        moveToLogin();
    })

    // 로그아웃 버튼 클릭 시
    $("#logoutBtn").click(() => {
        doLogout();
    })

    // 마이페이지 버튼 클릭 시
    $("#mypageBtn").click(() => {
        moveToMypage();
    })
}

let moveToLogin = () => {

    location.href = "/user/login.do";

}

let doLogout = () => {

    const URL = "/user/logout";

    axios.post(`${URL}`
    ).then(
        location.href="/user/login.do"
    )

}

let moveToMypage = () => {
    location.href = "/user/mypage.do"
}

navInit();