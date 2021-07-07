let main = () => {

    $("input[name=select]").change((e) => {
        radioSelect($(e.target).val());
    })

    $("#modifyPwBtn").click(() => {
        modifyPw();
    });

    $("#modifyNameBtn").click(() => {
        modifyName();
    });

    // $("#checkNickname").click(() => {
    //     checkValidationNickname(validationNick);
    // });

    $("#modifyNicknameBtn").click(() => {
        modifyNick();
    });

    // $("#inputNewNickname").change(() => {
    //     changeInputNickVal(validationNick);
    // });

    $("#deleteUserBtn").click(() => {
        deleteUser();
    });

}

let radioSelect = (val) => {
    if (val == "pw") {

        $("#nameChanger").hide();
        $("#nicknameChanger").hide();
        $("#pwChanger").show();

    } else if (val == "name") {

        $("#pwChanger").hide();
        $("#nicknameChanger").hide();
        $("#nameChanger").show();

    } else {

        $("#pwChanger").hide();
        $("#nameChanger").hide();
        $("#nicknameChanger").show();

    }
}

let modifyPw = () => {
    if ($("#inputNewPw").val() == $("#inputNewPwConfirm").val()) {

        let body = new URLSearchParams();

        const URL = "/user/modifyUserInfo";

        body.append("PASSWORD", $("#inputNewPwConfirm").val());

        axios.post(`${URL}`, body
        ).then(res => {

            alert("비밀번호가 변경되었습니다.");

            location.reload();
        })

    } else {
        alert("비밀번호가 서로 다릅니다.");
    }
}

let modifyName = () => {

    let body = new URLSearchParams();

    const URL = "/user/modifyUserInfo";

    body.append("NAME", $("#inputNewName").val());

    axios.post(`${URL}`, body
    ).then(res => {

        alert("이름이 변경되었습니다.");

        location.reload();
    })

}

// let checkValidationNickname = () => {
//     const URL = "http://localhost:8080/user/checkNickname?nickname="
//     let nickname = $("#inputNewNickname").val();

//     axios.get(`${URL}${nickname}`
//     ).then(res => {
//         if (res.data.result) {

//             validationNick = false;
//             $("#checkNickname").hide();

//             alert(res.data.msg);

//         } else {

//             validationNick = false;

//             alert(res.data.msg);

//         }
//     })
// }

// let changeInputNickVal = (validationNick) => {

//     validationNick = false;

//     $("#checkNickname").show();

// }

let modifyNick = () => {

    let body = new URLSearchParams();

    const URL = "/user/modifyUserInfo";

    body.append("NICKNAME", $("#inputNewNickname").val());

    axios.post(`${URL}`, body
    ).then(res => {

        if(res.data.result) {
            alert("닉네임이 변경되었습니다.");

            location.reload();
        } else {
            $("#inputNewNickname").focus();
            alert(res.data.msg);
        }
    })
}

let deleteUser = () => {

    const URL = "/user/deleteUser";

    axios.post(`${URL}`
    ).then(res => {

        alert(res.data.msg);

        location.href="/user/login.do";

    })

}

main();