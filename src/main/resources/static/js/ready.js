$(document).ready(function () {
    $(document).on("click", "input[type='checkbox']", function () {
        let count = $("input:checked[type='checkbox']").length; // 선택된 체크박스 수 계산
        console.log(count);
        if (count > 3) {
            this.checked = false; // 현재 체크박스를 선택 해제
            alert("3개까지만 선택이 가능합니다.");
        }
    });
});
