function toggleCheckbox() {
  var checkbox = document.getElementById("chkId");
  var label = document.querySelector('label[for="chkId"]');
  var checkboxImage = document.getElementById("checkbox-image");

  if (checkbox.checked) {
        // 아이디 저장
        var userId = document.getElementById("userId").value;
        localStorage.setItem("savedUserId", userId);
    } else {
        // 아이디 저장 취소
        localStorage.removeItem("savedUserId");
    }
}

// 페이지 로드 시 아이디를 로드하여 필드에 설정
document.addEventListener("DOMContentLoaded", function() {
    var savedUserId = localStorage.getItem("savedUserId");

    if (savedUserId) {
        document.getElementById("userId").value = savedUserId;
        document.getElementById("chkId").checked = true;
    }
});

// 로그인 함수 정의
function login() {

    // 사용자가 입력한 아이디와 비밀번호 가져오기
    const userId = document.getElementById("userId").value;
    const userPw = document.getElementById("userPw").value;

    // API 호출
    fetch("/login", {
        method: "POST",
        headers: {
            "Content-Type": "application/x-www-form-urlencoded"
        },
        body: `userId=${userId}&userPw=${userPw}`
    })
    .then(response => response.json())
    .then(jSONOResponse => {

        // API 응답 처리
        switch (jSONOResponse.data) {
            case 1:
                alert("계정이 존재하지 않습니다.");
                break;
            case 2:
                alert("비밀번호가 일치하지 않습니다.");
                break;
            default:
                const sn = jSONOResponse.data; // 서버에서 가져온 값을 할당
//                window.location.href = `http://dreammes.iptime.org:1880/response?data=${sn}`;
//                window.location.href = "/chart/menu?sn=" + sn;
//                window.location.href = "http://221.138.36.2:1880/response?data=" + sn;
                window.location.href = "http://hj5ujbhw.iptime.org:1880/response?data=" + sn;

                break;
        }
    })
    .catch(error => {
        console.error("API 호출 중 오류 발생:", error);
        alert("오류가 발생했습니다.");
    });
}