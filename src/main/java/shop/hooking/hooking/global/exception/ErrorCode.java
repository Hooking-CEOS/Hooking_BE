package shop.hooking.hooking.global.exception;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    /* Common */
    // Basic - C0**
    RUNTIME_EXCEPTION(BAD_REQUEST, "C001", "RUNTIME_EXCEPTION"),



    // User - U0**
    DUPLICATE_NICKNAME(CONFLICT, "U001", "DUPLICATE_NICKNAME_EXISTS"),
    DUPLICATE_EMAIL(CONFLICT, "U002", "DUPLICATE_EMAIL_EXISTS"),
    USER_NOT_FOUND(NOT_FOUND, "U003", "USER_NOT_FOUND"),
    PASSWORD_NOT_MATCH(BAD_REQUEST, "U004", "PASSWORD_NOT_MATCH"),
    BEFORE_PASSWORD_NOT_MATCH(BAD_REQUEST, "U005", "BEFORE_PASSWORD_NOT_MATCH"),
    PASSWORDS_NOT_EQUAL(BAD_REQUEST, "U006", "PASSWORDS_NOT_EQUAL"),
    CERTIFICATION_CODE_NOT_MATCH(BAD_REQUEST, "U007", "CERTIFICATIONCODE_NOT_MATCH"),


    // Exception
    // File Exception - E0**
    S3_UPLOAD_FAILURE(INTERNAL_SERVER_ERROR, "E001", "NETWORK_FAILURE"),
    FILE_UPLOAD_FAILURE(BAD_REQUEST, "E002", "WRONG_FILE_TYPE"),
    // TOKEN - E1**
    TOKEN_VALIDATE_FAILURE(BAD_REQUEST, "E101", "INVALID_TOKEN"),
    REFRESHTOKEN_EXPIRED(BAD_REQUEST, "E102", "REFRESHTOKEN_EXPIRED"),
    // SSE - E2**
    SSE_CONNECTION_FAILURE(INTERNAL_SERVER_ERROR, "E201", "SSE_CONNECTION_FAILURE"),

    //SUCCESS
    LOGOUT_SUCCESS(OK, "S001", "LOGOUT_SUCCESS");

    private final HttpStatus status;
    private final String code;
    private final String message;
}

