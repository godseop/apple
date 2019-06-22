package org.godseop.apple.model;

import lombok.Data;

import java.util.List;

@Data
public class Page {
    // 페이지당 보여줄 항목 갯수
    private int pageSize;

    // 현재 페이지 번호
    private int pageNumber;

    // 이전 페이지 번호
    private int prevPageNumber;

    // 다음 페이지 번호
    private int nextPageNumber;

    // 항목 총 갯수
    private int totalCount;

    // 페이징 시작 항목 번호
    private int startRowNumber;

    // 페이징 끝 항목 번호
    private int endRowNumber;

    // 화면에 보여줄 페이지 갯수
    private int pageCount;

    // 페이지 총 갯수
    private int totalPage;

    // 페이지 시작 번호
    private int startPageNumber;

    // 페이지 끝 번호
    private int endPageNumber;

    // 페이지 배열
    private List<Integer> pageList;
}
