[JSP] 게시판(글, 댓글, 페이징, 파일 업로드) 구현 프로젝트
===
--------------------------------------

* #### 프로젝트 소개: 백엔드는 Spring Boot를 이용하고 프런트엔드는 JSP, Ajax, BootStrap 활용하여 게시판의 기능(게시글, 댓글, 파일 업로드)를 구현한다.
----------------------------------

### 사용 기술

* #### 백엔드:
  * JPA(Spring Data JPA)
  * Java 19
  * SpringBoot 3.1.2
  * Swagger 2.1

* #### 프런트엔드:
  * JSP
  * JavaScript(Ajax)
  * Bootstrap

* #### DataBase
  * MySQL 8.0

* #### Build Tool:
  * Gradle 7.6
------------------------------
* #### DB
  ![DB](https://github.com/hichocolatemilk/JSP-board/assets/111757770/fc4baa92-6825-4df0-9c0f-3d62be299e81)
------------------------------
* #### API
  ![게시판api](https://github.com/hichocolatemilk/JSP-board/assets/111757770/82e9df44-6d5a-49b4-a6f3-8df0f713b116)
  ![댓글 API](https://github.com/hichocolatemilk/JSP-board/assets/111757770/ad8b2f4f-950f-4db4-a770-f58b3fc39b65)
  ![파일 API](https://github.com/hichocolatemilk/JSP-board/assets/111757770/404f0e17-2c3f-4e4a-ba16-3af9bfe8ea8b)
------------------------------

* #### 개발 정리
  * 페이징 관련 참조 블로그: https://amongthestar.tistory.com/173
  * 파일 업로드 관련 참조 영상: https://www.youtube.com/watch?v=XUL60-Ke-L8&t=475s


  * 조회 수 쿼리 
    * 스프링 부트 2.5 이상부터는 @Param을 사용해 주어야 함.
      <pre>
      <code>
      public interface BoardRepository extends JpaRepository< Board, Long>  {
        @Modifying
        @Query("update Board b set b.hit = b.hit +1 where b.id = :id ")
        int updateHit(@Param("id") Long id);
        }
      </code>
      </pre>
  
    * 조회 수 default를 위하여 @DynamicInsert @DynamicUpdate 추가 


  * @oneToMany 에서 List를 DTO에서 받는법
      <pre>
      <code>
      private List< FileResDTO> fileList;
    
      .this.fileList = board.getFileList().stream()
      .map(FileResDTO:: new).collect(Collectors.toList());
      </code>
      </pre>

  * 빈값이 들어가는걸 방지하기 위해 @NotBlank @Valid를 추가
    * implementation 'org.springframework.boot:spring-boot-starter-validation'
    <pre>
    <code>
        DTO 예시
        @Schema(description = "댓글 내용")
        @NotBlank
        private String comment;
    
        Controller 예시
        @RequestBody @Valid CommentUpdateDTO commentUpdateDTO
    </code>
    </pre>
  * JSP header, footer 분리
    <pre>
    <code>
      <%@include file ="../view/header.jsp" %>
        <내용>
        </내용>
      <%@include file ="../view/footer.jsp" %>
    </code>
    </pre>
  
  * JSP forEach, length 사용법
    <pre>
    <code>
      <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 추가

      < c:forEach items="${아이템}" var="변수명" varStatus="status">
      </c:forEach>
    
      <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 추가
        ${fn:length(뽑고 싶은 리스트)}
    </code>
    </pre>


 * application.properties 설정 추가
   * hibernate.ddl.auto = create에서 FK 때문에 꼬이는 현상 발생 
   * spring.jpa.defer-datasource-initialization=true 추가 후 증상 없어짐
------------------------------

 *  #### 앞으로 추가 보완 사항
    1. 조회 수 중복 방지
    2. jwt 로그인 방식 추가
------------------------------




