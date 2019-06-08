package org.godseop.apple.model;

public enum Error {

    OK("0000", "정상"),

    API_NOT_FOUND("0001", "지원하지않는 API입니다."),
    DATABASE_ERROR("0002", "데이터베이스 오류가 발생했습니다."),
    DUPLICATE_MEMBER_UID("0003", "사용자 아이디 중복입니다."),
    DUPLICATE_MEMBER_NICKNAME("0004", "사용자 닉네임 중복입니다."),
    MEMBER_NOT_EXISTS("0005", "사용자가 존재하지 않습니다"),

    SYSTEM_EXCEPTION("5000", "서버오류 입니다."),

    AUTH_NOT_GRANTED("9990", "권한이 없습니다."),
    LOGIN_FAIL("9996", "로그인에 실패했습니다.\n아이디, 비밀번호를 확인해주세요."),
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
