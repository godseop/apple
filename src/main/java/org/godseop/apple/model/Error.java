package org.godseop.apple.model;

public enum Error {

    OK("0000", "정상"),

    API_NOT_FOUND("0001", "지원하지않는 API입니다."),
    DUPLICATE_MEMBER_UID("0003", "사용자 아이디 중복입니다."),
    DUPLICATE_MEMBER_NICKNAME("0004", "사용자 닉네임 중복입니다."),
    MEMBER_NOT_EXISTS("0005", "사용자가 존재하지 않습니다"),

    FILE_UPLOAD_TO_LOCAL_FAIL("1000", "서버에 파일을 업로드중 실패했습니다." ),
    FILE_UPLOAD_TO_S3_FAIL("1001", "S3 서버에 파일을 업로드중 실패했습니다."),
    MULTIPART_FILE_NOT_EXISTS("1002","이런! 멀티파트파일을 가져오지 못했습니다." ),

    AUTH_NOT_GRANTED("8000", "권한이 없습니다."),
    LOGIN_FAIL("8001", "로그인에 실패했습니다."),


    DATABASE_ERROR("9997", "데이터베이스 오류가 발생했습니다."),
    SYSTEM_EXCEPTION("9998", "시스템 에러입니다. 관리자에게 문의해주세요."),
    INTERNAL_SERVER_ERROR("9999", "서버 에러입니다. 관리자에게 문의해주세요."),
    ;

    private final String code;
    private final String message;

    Error(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    @Override
    public String toString() {
        return this.code + ":" + this.message;
    }

}
