// 회사 autocomplet 기능
document.addEventListener("DOMContentLoaded", function () {
    const searchBar = document.getElementById("search-bar");
    const resultContainer = document.getElementById("autocomplete-results");
    const searchForm = document.querySelector(".search-area form"); // 폼 선택자 추가

    searchBar.addEventListener("input", function () {
        const query = searchBar.value;

        //2자 이상 입력한 경우에만 요청
        if (query.length < 1) {
            resultContainer.innerHTML = "";
            return;
        }

        fetch(`/api/company/autocomplete?keyword=${encodeURIComponent(query)}`)
            .then(response => response.json())
            .then(data => {
                resultContainer.innerHTML = ""; // 이전 결과 초기화
                if (data.length > 0) {
                    data.forEach(name => {
                        const resultItem = document.createElement("div");
                        resultItem.textContent = name;
                        resultItem.addEventListener("click", function () {
                            searchBar.value = name; // 클릭한 값으로 검색창 업데이트
                            resultContainer.innerHTML = "";
                            searchForm.submit(); // 폼 자동 제출
                        });
                        resultContainer.appendChild(resultItem);
                    });
                }
            })
            .catch(error => console.error("Error fetching autocomplete results:", error));
    });

    // 자동 완성 외부 클릭 시 닫기
    document.addEventListener("click", function (event) {
        if(!resultContainer.contains(event.target) && event.target !== searchBar) {
            resultContainer.innerHTML = "" //자동완성 창 닫기
        }
    })
})

// 스택  autocomplet 기능
document.addEventListener("DOMContentLoaded", function () {
    const searchBar = document.getElementById("search-bar");
    const resultContainer = document.getElementById("autocomplete-results");
    const searchForm = document.querySelector(".search-area form"); // 폼 선택자 추가

    searchBar.addEventListener("input", function () {
        const query = searchBar.value;

        //2자 이상 입력한 경우에만 요청
        if (query.length < 1) {
            resultContainer.innerHTML = "";
            return;
        }

        fetch(`/api/stack/autocomplete?keyword=${encodeURIComponent(query)}`)
            .then(response => response.json())
            .then(data => {
                resultContainer.innerHTML = ""; // 이전 결과 초기화
                if (data.length > 0) {
                    data.forEach(name => {
                        const resultItem = document.createElement("div");
                        resultItem.textContent = name;
                        resultItem.addEventListener("click", function () {
                            searchBar.value = name; // 클릭한 값으로 검색창 업데이트
                            resultContainer.innerHTML = "";
                            searchForm.submit(); // 폼 자동 제출
                        });
                        resultContainer.appendChild(resultItem);
                    });
                }
            })
            .catch(error => console.error("Error fetching autocomplete results:", error));
    });

    // 자동 완성 외부 클릭 시 닫기
    document.addEventListener("click", function (event) {
        if(!resultContainer.contains(event.target) && event.target !== searchBar) {
            resultContainer.innerHTML = "" //자동완성 창 닫기
        }
    })
})

//강의 autocomplete 기능
document.addEventListener("DOMContentLoaded", function () {
    const searchBar = document.getElementById("search-bar");
    const resultContainer = document.getElementById("autocomplete-results");
    const searchForm = document.querySelector(".search-area form"); // 폼 선택자 추가

    searchBar.addEventListener("input", function () {
        const query = searchBar.value;

        //2자 이상 입력한 경우에만 요청
        if (query.length < 1) {
            resultContainer.innerHTML = "";
            return;
        }

        fetch(`/api/course/autocomplete?keyword=${encodeURIComponent(query)}`)
            .then(response => response.json())
            .then(data => {
                resultContainer.innerHTML = ""; // 이전 결과 초기화
                if (data.length > 0) {
                    data.forEach(name => {
                        const resultItem = document.createElement("div");
                        resultItem.textContent = name;
                        resultItem.addEventListener("click", function () {
                            searchBar.value = name; // 클릭한 값으로 검색창 업데이트
                            resultContainer.innerHTML = "";
                            searchForm.submit(); // 폼 자동 제출
                        });
                        resultContainer.appendChild(resultItem);
                    });
                }
            })
            .catch(error => console.error("Error fetching autocomplete results:", error));
    });

    // 자동 완성 외부 클릭 시 닫기
    document.addEventListener("click", function (event) {
        if(!resultContainer.contains(event.target) && event.target !== searchBar) {
            resultContainer.innerHTML = "" //자동완성 창 닫기
        }
    })
})