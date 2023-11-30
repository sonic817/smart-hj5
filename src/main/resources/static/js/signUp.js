document.addEventListener("DOMContentLoaded", function () {
    const submitButton = document.getElementById('submit-button');
    submitButton.disabled = true;
    submitButton.style.backgroundColor = "#9FA19F"; // 버튼 비활성화 색상
    submitButton.style.cursor = "not-allowed";
});

function checkPw() {
    const userPw = document.getElementById('userPw').value;
    const confirmPw = document.getElementById('confirmPw').value;
    const submitButton = document.getElementById('submit-button');

    if (userPw === confirmPw) {
        submitButton.disabled = false;
        submitButton.style.backgroundColor = "#3498db"; // 버튼 활성화 색상
        submitButton.style.cursor = "pointer";
    } else {
        submitButton.disabled = true;
        submitButton.style.backgroundColor = "#9FA19F"; // 버튼 비활성화 색상
        submitButton.style.cursor = "not-allowed";
    }
}

function showConfirmation() {
    if (window.confirm("관리사무소에 등록된 신청자의 전화번호를 이용하여 등록자를 확인합니다.")) {
        const userId = document.getElementById('userId').value;
        const tempPw = document.getElementById('tempPw').value;
        const userPw = document.getElementById('userPw').value;
        const hp = document.getElementById('hp').value;
        const dong = document.getElementById('dong').value;
        const ho = document.getElementById('ho').value;

        // 임시 비밀번호 입력 여부 검사
        if (!tempPw) {
            alert("임시 비밀번호를 입력해주세요.");
            return;
        }

        // 동과 호수의 길이를 검사
        if (dong.length !== 3 || ho.length > 4) {
            alert("동은 3자리, 호수는 4자리 이하로 입력해주세요.");
            return;
        }

        const signUpData = {
            userId: userId,
            tempPw: tempPw,
            userPw: userPw,
            hp: hp,
            dong: parseInt(dong),
            ho: parseInt(ho)
        };

        // AJAX POST 요청 보내기
        $.ajax({
            url: "/user/sign-up",
            type: "POST",
            data: JSON.stringify(signUpData),
            contentType: "application/json",
            success: function(response) {

                // API 응답 처리
                switch (response.data) {
                    case 0:
                        alert(
                            "아이디 : " + userId + "\n" +
                            "PW : " + userPw + "\n" +
                            "사용자 등록이 완료되었습니다.");
                        window.location.href = "/";
                        break;
                    case 1:
                        alert("이미 등록된 계정입니다.");
                        break;
//                    case 2:
//                        alert("호수 길이 4자리를 초과합니다.");
//                        break;
//                    case 3:
//                        alert("임시 비밀번호를 입력해주세요.");
//                        break;
                    case 4:
                        alert("사전 등록된 사용자가 아닙니다.");
                        break;
                }
            },
            error: function(error) {
                console.error("POST 요청 실패입니다.");
            }
        });
    } else {

    }
}