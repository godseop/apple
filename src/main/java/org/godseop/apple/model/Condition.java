package org.godseop.apple.model;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("condition")
public class Condition {

    private int pageNumber = 1;

    private int pageSize = 3;

    private int pageCount = 3;

    private int totalCount;

    private int startRowNumber;

    private String orderKey;

    private String orderSort;

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        this.startRowNumber = (this.pageNumber - 1) * this.pageSize;
    }

}
