package org.godseop.apple.model;

import lombok.Data;
import org.apache.ibatis.type.Alias;

@Data
@Alias("condition")
public class Condition {

    private int pageSize = 5;

    private int pageNumber = 1;

    private int pageCount = 5;

    private int totalCount;

    private int startRowNumber;

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        this.startRowNumber = (this.pageNumber - 1) * this.pageSize;
    }

}
