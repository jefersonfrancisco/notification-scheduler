package com.jeferson.notification.adapter.api;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.jeferson.notification.adapter.api.dto.CreateNotificationDto;
import io.restassured.RestAssured;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.UUID;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.jdbc.JdbcTestUtils;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NotificationControllerTest {

  private static final DateTimeFormatter ISO_DATE_TIME_FORMAT = DateTimeFormatter
      .ofPattern("yyyy-MM-dd'T'HH:mm:ss.SS");

  @LocalServerPort
  private int port;

  @Autowired
  private JdbcTemplate jdbcTemplate;

  private LocalDateTime scheduleDate;


  @BeforeAll
  static void beforeAll() {
    RestAssured.baseURI = "http://localhost";
    RestAssured.basePath = "/v1/notifications";
    RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
  }

  @BeforeEach
  void setUp() {
    JdbcTestUtils.deleteFromTables(jdbcTemplate, "NOTIFICATION");
    RestAssured.port = port;
    scheduleDate = LocalDateTime.now().plusMinutes(10);
  }

  @Test
  void givenAMessageWhenPostThenReturnResponse() {
    var createNotificationDto = buildCreateNotificationDto();

    RestAssured
        .given()
        .contentType(APPLICATION_JSON_VALUE)
        .body(createNotificationDto)
        .when()
        .post()
        .then()
        .statusCode(201)
        .body("id", Matchers.notNullValue())
        .body("body", equalTo("notification"))
        .body("channel", equalTo("SMS"))
        .body("recipientMail", equalTo("jeferson.gh@gmail.com"))
        .body("recipientName", equalTo("Jeferson Francisco"))
        .body("scheduleDate", equalTo(scheduleDate.format(ISO_DATE_TIME_FORMAT)))
        .body("recipientPhoneNumber", equalTo("47999875611"));
  }


  @Test
  void givenANotificationWhenPostThenReturnBadRequest() {
    var schedule = LocalDateTime.now().plusMinutes(10);
    RestAssured
        .given()
        .contentType(APPLICATION_JSON_VALUE)
        .body(CreateNotificationDto.builder().build())
        .when()
        .post()
        .then()
        .statusCode(400)
        .body("$", hasSize(6));
  }

  @Test
  void givenANotificationIdWhenDeleteThenReturnNoContent() {
    var id = createNotification();

    RestAssured
        .given()
        .pathParam("id", id)
        .when()
        .delete("/{id}")
        .then()
        .statusCode(204);
  }

  @Test
  void givenANotificationIdWhenGetThenReturnNotification() {
    var id = createNotification();
    RestAssured
        .given()
        .pathParam("id", id)
        .when()
        .get("/{id}")
        .then()
        .statusCode(200)
        .body("id", Matchers.notNullValue())
        .body("body", equalTo("notification"))
        .body("channel", equalTo("SMS"))
        .body("recipientMail", equalTo("jeferson.gh@gmail.com"))
        .body("recipientName", equalTo("Jeferson Francisco"))
        .body("scheduleDate", equalTo(scheduleDate.format(ISO_DATE_TIME_FORMAT)))
        .body("recipientPhoneNumber", equalTo("47999875611"));
  }


  @Test
  void givenANotificationIdWhenGetThenReturnNotFound() {
    RestAssured
        .given()
        .pathParam("id", UUID.randomUUID().toString())
        .when()
        .get("/{id}")
        .then()
        .statusCode(404);
  }

  @Test
  void givenANotificationIdWhenDeleteThenReturnNotFound() {
    RestAssured
        .given()
        .pathParam("id", UUID.randomUUID().toString())
        .when()
        .delete("/{id}")
        .then()
        .statusCode(404);
  }

  @Test
  void givenANotificationIdWhenDeleteReturnException() {
    var id = createNotification();
    updateNotificationStatus();
    RestAssured
        .given()
        .pathParam("id", id)
        .when()
        .delete("/{id}")
        .then()
        .statusCode(400);
  }

  private void updateNotificationStatus() {
    jdbcTemplate.update("UPDATE NOTIFICATION SET notification_status = 'SENT'");
  }


  private String createNotification() {
    return RestAssured
        .given()
        .contentType(APPLICATION_JSON_VALUE)
        .body(buildCreateNotificationDto())
        .when()
        .post()
        .then()
        .statusCode(201)
        .extract()
        .jsonPath()
        .getString("id");
  }

  private CreateNotificationDto buildCreateNotificationDto() {
    return CreateNotificationDto.builder()
        .body("notification")
        .channel("SMS")
        .recipientMail("jeferson.gh@gmail.com")
        .recipientName("Jeferson Francisco")
        .scheduleDate(scheduleDate)
        .recipientPhoneNumber("47999875611")
        .build();
  }
}
