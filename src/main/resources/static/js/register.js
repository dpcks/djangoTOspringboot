<!-- 아이디 중복 확인 및 비밀번호 확인 스크립트 -->

function checkUsername() {
    const username = document.getElementById("username").value;
    fetch(`/auth/check-username?username=${username}`)
    .then(response => response.text())
    .then(data => {
    const message = document.getElementById("usernameMessage");
    if (data === "duplicate") {
    message.textContent = "이미 존재하는 아이디입니다.";
    message.className = "error";
} else {
    message.textContent = "사용 가능한 아이디입니다.";
    message.className = "success";
}
});
}

function validatePassword() {
    const password = document.getElementById("password").value;
    const passwordCheck = document.getElementById("passwordCheck").value;
    const message = document.getElementById("passwordMessage");

    if (password === passwordCheck) {
    message.textContent = "비밀번호가 일치합니다.";
    message.className = "success";
} else {
    message.textContent = "비밀번호가 일치하지 않습니다.";
    message.className = "error";
}
}