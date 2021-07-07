let main = () => {

    let validationId = false;
    let validationNick = false;

    // 회원가입
    $("#signUnBtn").click(() => {
        signUp();
    });

    // 취소버튼 클릭
    $("#moveToBackBtn").click(() => {
        moveToBack();
    });

    // 아이디 중복체크 클릭
    $("#checkIdBtn").click(() => {
        checkValidationId(validationId);
    });

    // 아이디 값 변경될 경우
    $("#inputId").change(() => {
        changeInputIdVal(validationId);
    });

    // 닉네임 중복체크 클릭
    $("#checkNicnameBtn").click(function() {
        validationNick = true;
        checkValidationNickname();
        console.log(validationNick);
    });

    // 닉네임 값 변경될 경우
    $("#inputNickname").change(() => {
        changeInputNickVal(validationNick);
    });
}

let checkValidation = (validationId, validationNick) => {
    if (!validationId) {
        $("#inputId").focus();
        return;
    }
    if (!validationNick) {
        $("#inputNickname").focus();
        return;
    }
}

let signUp = () => {
    const URL = "/user/signUp";

    let body = new URLSearchParams();

    body.append("USER_ID", $("#inputId").val());
    body.append("PASSWORD", $("#inputPassword").val());
    body.append("NAME", $("#inputName").val());
    body.append("NICKNAME", $("#inputNickname").val());

    axios.post(`${URL}`, body
    ).then(res => {

        if (res.data.data == 1) {
            alert(res.data.msg);
            window.location.href="/user/login.do";
        } else if (res.data.data == 2) {
            $("#inputId").focus();

            alert(res.data.msg);
        } else {
            $("#inputNickname").focus();
            
            alert(res.data.msg);
        }

    })

}

let moveToBack = () => {
    window.history.back();
}

let checkValidationId = (validationId) => {
    const URL = "/user/checkId?id=";
    let id = $("#inputId").val();

    if (id == "") {
        $("#inputId").focus();
        return;
    }

    axios.get(`${URL}${id}`
    ).then(res => {
        if (res.data.result) {

            $("#checkIdBtn").hide();

            return true;

        } else {

            alert(res.data.msg);

            return false;

        }
    });
}

let changeInputIdVal = (validationId) => {
    
    validationId = false;
    $("#checkIdBtn").show();

}

let checkValidationNickname = (nick) => {
    const URL = "/user/checkNickname?nickname="
    let nickname = $("#inputNickname").val();

    axios.get(`${URL}${nickname}`
    ).then(res => {
        if (res.data.result) {

            this.validationNick = false;

            $("#checkNicnameBtn").hide();
            
            alert(res.data.msg);


        } else {

            this.validationNick = false;

            alert(res.data.msg);

        }
    })
}

let changeInputNickVal = (validationNick) => {

    validationNick = false;
    $("#checkNicnameBtn").show()

}

main();