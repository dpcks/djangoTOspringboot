// 페이지 로드 시 기본 "언어" 스택 로드
document.addEventListener("DOMContentLoaded", function () {
    updateStacks('언어', document.querySelector('.btn.on'));
});

function updateStacks(assort, element) {
    // 버튼 상태 관리: 기존 "on" 클래스 제거 . 새로 클릭된 버튼에 추가
    const buttons = document.querySelectorAll('.btn-l-wrap .btn')
    buttons.forEach((btn) => btn.classList.remove('on'));
    element.classList.add('on');

    fetch(`/course/stacks?assort=${assort}`, {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
        },
    })
        .then((response) => response.json())
        .then((data) => renderStacks(data))
        .catch((error) => console.error('Error fetching stacks:', error));
}

function renderStacks(data) {
    const stackList = document.getElementById('stack-list');
    stackList.innerHTML = ''; // 기존 스택 목록 초기화

    data.forEach((stack) => {
        const stackDiv = document.createElement('span');
        stackDiv.className = `filterDiv ${stack.assort}`;
        stackDiv.innerHTML = `
                <i class="skill-logo" style="background-image: url('${stack.logo}');"></i>
            <input type="checkbox" class="btn-check" id="btncheck${stack.id}" 
                   autocomplete="off" name="s" value="${stack.name}">
            <label class="btn btn-outline-primary" for="btncheck${stack.id}">${stack.name}</label>
        `;
        stackList.appendChild(stackDiv);
    });
}
