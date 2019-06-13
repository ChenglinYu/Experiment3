package model;

import java.util.Date;

/**
 * Created by chenglinyu on 2019-06-13
 * Description:
 */
public class ToDoItem {

    // 待办事项的开始时间
    private Date startTime;

    // 待办事项的结束时间
    private Date endTime;

    // 待办事项内容
    private String label;

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public ToDoItem(Date startTime, Date endTime, String label) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.label = label;
    }
}