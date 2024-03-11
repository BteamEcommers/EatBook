# EatBook ReadMe📖
## 기획 🎬
### EBook 이커머스
- EBook 판매 사이트를 구현하고자 하였습니다.
- 사용자, 판매자, 관리자 3 가지 측면에서 기획했습니다.
- EBook을 구매하는 **사용자**
- EBook을 판매하는 **판매자**
- 사이트와 판매자를 관리하는 **관리자**

### 레퍼런스
- 알라딘
- 네이버
- yes24
### Figjam
![](https://velog.velcdn.com/images/twinogre/post/4a4ea3b0-adca-4f61-80a7-e66af68f8625/image.png)

### 라이브러리
- [부트스트랩](https://getbootstrap.kr/)
- [SweetAlert2](https://sweetalert2.github.io/) : 알림창 JS 라이브러리
- swiper : 이미지 자동 전환
- 카카오API : 카카오 1 대 1 상담 버튼
- jquery
  
## 데이터베이스 🖥
![EatBook_DB](https://velog.velcdn.com/images/twinogre/post/395b48c3-ba19-440c-8c93-837915384c41/image.png)
- 판매자 정산을 대비해 Member에 관련 정보(계좌번호, 금융기과

## 주요 기능 💻
### 메인화면
- 메인
![EatBook_MainHome](https://velog.velcdn.com/images/twinogre/post/519aedc4-db75-4cfe-8443-49caff9b5c03/image.png)

### 구현 기능
#### 회원
- 회원가입
- 로그인 / 로그아웃
- 아이디 찾기 
- 비밀번호 찾기
- 개인정보 수정
- 카카오톡상담(메인페이지 하단 톡상담 버튼)
- 판매자 신청하기

#### 관리자
- 판매자 신청 승인 / 거절
- 이벤트 작성, 수정, 삭제 (페이징)
- FAQ 작성, 수정, 삭제 (아코디언)
- 카테고리 생성 (수정 필요)

#### 판매자
- 판매글 리스트

#### EBook
- EBook 등록
- 카테고리별 리스트
- 장바구니 ( 다중 선택 > 삭제, 결제가능)
- 찜 (다중 선택 > 장바구니 보내기)
- EBook 상세 페이지
- 리뷰 등록
- 단건 결제 페이지

### 추가된 기능

- 판매자 신청하기 + 리스트
 ![판매자 신청_리스트](https://velog.velcdn.com/images/twinogre/post/b6cb1bc3-53cb-4aa1-8d5d-412f7b2d0ebe/image.png)
  
- 장바구니 + 찜 / 전체 삭제, 전체 결제
  

## 트러블 슈팅 ⚠

## 🔥 트러블 슈팅(전율택 / Twinogre)

### 🚨 Issue 1
### 🚧  이메일 전송 실패

A. 이슈 내역
<br>
- 아이디 찾기 시 코드 이메일 발송 실패.

<br>

<br>
## 🛑 원인

1.  엔티티 `Email`이 SQL 예약어 
2.  Column 명 `To`가 SQL 예약어
3.  Column 명 변경 후 HTML 수정하지 않음 
<br>
<br>

## 🚥 해결
A. 엔티티 명 변경
B. Column 명 변경(to => toEmail)
C. HTML에 해당  Column  명 변경

```html
name="to" 

=>

name="toEmail"
```

## ❗ 느낀점
- 앞으로 엔티티 및 컬럼 생성 시 SQL 예약어를 확인하고 생성하자
- 칼럼 변경 시 연관된 html 수정을 확인하자

## 🔥 트러블 슈팅(김정배 / kimjeongbae)

### 🚨 Issue 1
### 🚧  null

A. 이슈 내역
따로 작동 하는 아코디언

<br>
문제점 설명

- 부트스트랩5 에서 아코디언, Js Collapse(콜랩스)를 이용한
- FAQ 질문 게시판에서 아코디언 형식으로 게시글을 작성하고
- 클릭시 모든 아코디언이 동시에 작동 하는 현상

<br>
## 🛑 원인

- 부트스트랩에서 그대로 가져와서 적용을 하였기 때문에
- th:each로 새로 게시글이 생성 될때마다 모든 FAQ게시글이 같은 ID를 사용하여서 발생
<br>
<br>

## 🚥 해결
- 원래 코드
``` java
<div class="accordion" id="faqAccordion" th:each="faq : ${faqList}">
    <div class="accordion" id="accordionExample">
          <div class="accordion-item">
                 <h2 class="accordion-header">
                         <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                               data-bs-target="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                              <a th:text="${faq.title}"></a>
                              </button>
                     </h2>
                      <div id="collapseTwo" class="accordion-collapse collapse"
                          data-bs-parent="#accordionExample">
                      <div class="accordion-body">
              <a th:href="@{|/faq/detail/${faq.id}|}" th:text="${faq.content}"></a>
              </div>
  </div>
</div>
        
```
- 고유 ID를 지정 하지 않아서 위와 같은 코드로 실행했을때 모든 게시물의 아코디언 형식이
- 동시에 같이 움직이는 현상이 었습니다
```java
 <div>
       <a th:href="@{/faq/create}" class="btn btn-primary my-2">작성</a>
          <div class="accordion">
             <div class="accordion">
                  <div class="accordion-item"  th:each="faq, iterStat : ${faqList}">
                     <h2 class="accordion-header"  th:id="'accordionExample-' + ${iterStat.index}">
                         <button class="accordion-button collapsed" type="button" data-bs-toggle="collapse"
                                 th:data-bs-target="'#collapse-' + ${iterStat.index}" aria-expanded="false"
                                  aria-controls="'collapse-' + ${iterStat.index}">
                                    <a th:text="${faq.title}"></a>
                                </button>
                            </h2>
               <div th:id="'collapse-' + ${iterStat.index}" class="accordion-collapse collapse"
                      th:data-bs-parent="'#accordionExample-' + ${iterStat.index}">
                       <div class="accordion-body">
                         <a th:href="@{|/faq/detail/${faq.id}|}" th:text="${faq.content}"></a>
           </div>
</div>
      
```
- th:id와 th:data-bs-target, th:data-bs-parent 등에 Thymeleaf의 문법을 사용하여 
고유한 id를 동적으로 생성고  각각의 아코디언이 서로에게 영향을 주지 않도록 
이고유한 id를 할당 하여 아코디언이 각각 작동 하도록 코드를 수정 하였습니다


🔥 트러블 슈팅(류한선 / ryuhanseon)
🚨 Issue 1
🚧 null
A. 이슈 내역
리뷰를 작성한 값이 데이터베이스에 들어가는데 HTML로 불러오는게 불가능해지는 오류
 "Exception evaluating SpringEL expression: 'review.content'"

문제점 설명 
## 🛑 원인 
```
@GetMapping("/detail/{id}") //책에 대한 상세페이지
    public String bookDetail(Model model, @PathVariable("id") Integer id) {
        Book book = this.bookService.getBookById(id);
        model.addAttribute("book", book);
        return "book/book_detail";
    }
```
책에 대한 상세페이지에서 model로 reviewList의 값을 못 보내주고 있었다.




🚥 해결

```
public List<Review> findAllByBook(Book book) {

        return this.reviewRepository.findAllByBook(book);
    }
```

그래서 직접 리포지터리에서 값을 가져와서


```
@GetMapping("/detail/{id}") //책에 대한 상세페이지
    public String bookDetail(Model model, @PathVariable("id") Integer id) {
        Book book = this.bookService.getBookById(id);
        List<Review> reviewList = this.reviewService.findAllByBook(book);
        model.addAttribute("book", book);
        model.addAttribute("reviewList", reviewList);
        return "book/book_detail";
    `}`
```
model 에서 직접 reviewList 넣어줬다.
