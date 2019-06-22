package org.godseop.apple.util;


import org.godseop.apple.model.Condition;
import org.godseop.apple.model.Page;

public class PageUtils {

    private static final int DEFAULT_PAGE_SIZE = 5;
    private static final int DEFAULT_PAGE_COUNT = 5;

    public static Page getPage(int pageNumber, int totalCount) {
        Page page = new Page();

        page.setPageSize(DEFAULT_PAGE_SIZE);
        page.setPageCount(DEFAULT_PAGE_COUNT);
        page.setPageNumber(pageNumber);
        page.setTotalCount(totalCount);

        int startRowNumber = (pageNumber - 1) * DEFAULT_PAGE_SIZE;
        int endRowNumber = pageNumber * DEFAULT_PAGE_SIZE;

        int totalPage = (totalCount - 1) / DEFAULT_PAGE_SIZE + 1;
        totalPage = totalPage == 0 ? 1 : totalPage;

        int startPageNumber = (DEFAULT_PAGE_COUNT * (pageNumber - 1) / DEFAULT_PAGE_COUNT) + 1;
        int endPageNumber = startPageNumber + DEFAULT_PAGE_COUNT - 1;
        endPageNumber = endPageNumber > totalPage ? totalPage : endPageNumber;

        page.setStartRowNumber(startRowNumber);
        page.setEndRowNumber(endRowNumber);
        page.setTotalPage(totalPage);
        page.setStartPageNumber(startPageNumber);
        page.setEndPageNumber(endPageNumber);

        return page;
    }

    public static Page getPage(Condition condition) {
        Page page = new Page();

        int pageSize = condition.getPageSize();
        int pageNumber = condition.getPageNumber();
        int pageCount = condition.getPageCount();
        int totalCount = condition.getTotalCount();

        page.setPageSize(pageSize);
        page.setPageNumber(pageNumber);
        page.setPageCount(pageCount);
        page.setTotalCount(totalCount);

        int startRowNumber = (pageNumber - 1) * pageSize + 1;
        int endRowNumber = pageNumber * pageSize;

        page.setStartRowNumber(startRowNumber);
        page.setEndRowNumber(endRowNumber);

        int totalPage = (totalCount - 1) / pageSize + 1;
        totalPage = totalPage == 0 ? 1 : totalPage;

        page.setTotalPage(totalPage);

        int startPageNumber = pageCount * ((pageNumber - 1) / pageCount) + 1;
        int endPageNumber = startPageNumber + pageCount - 1;
        endPageNumber = endPageNumber > totalPage ? totalPage : endPageNumber;

        page.setStartPageNumber(startPageNumber);
        page.setEndPageNumber(endPageNumber);

        return page;
    }

}
