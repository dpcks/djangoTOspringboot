# 장고를 스프링부트로 변환 프로젝트

- 장고 프로젝트 링크: [GitHub Repository](https://github.com/dpcks/kokonenne)

# Django와 Spring Boot의 주요 구성요소 비교

| Django 구성요소       | Spring Boot 구성요소         | 설명                                           |
|-----------------------|-----------------------------|----------------------------------------------|
| **프로젝트(project)** | **프로젝트(Maven/Gradle)**  | Django의 프로젝트와 유사한 역할 수행          |
| **앱(app)**           | **패키지(package)**         | Django 앱은 Spring에서의 패키지와 유사        |
| **views.py**          | **Controller**             | URL 요청을 처리하는 부분 매핑                 |
| **models.py**         | **Entity + Repository**    | 데이터베이스와의 인터페이스 제공              |
| **urls.py**           | **@RequestMapping**        | URL과 컨트롤러 메서드 연결                    |
| **settings.py**       | **application.yml/properties** | 환경 설정 파일                              |
| **템플릿(template)**  | **Thymeleaf/Freemarker**   | HTML 렌더링을 위한 템플릿 엔진               |
