package server;

import model.ToDoItem;
import org.omg.CORBA.ORB;
import todo.TodoListPOA;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by chenglinyu on 2019-06-13
 * Description:
 */
public class TodoListServant extends TodoListPOA {

    private ORB orb;

    public void setOrb(ORB orb) {
        this.orb = orb;
    }

    // All todoItems
    List<ToDoItem> toDoItemList = new ArrayList<>();

    public boolean add(String startTime, String endTime, String label) {

        Date startDate = stringToDate(startTime);
        Date endDate = stringToDate(endTime);
        if (startDate == null || endDate == null) {
            return false;
        } else {
            ToDoItem toDoItem = new ToDoItem(startDate, endDate, label);
            toDoItemList.add(toDoItem);
            return true;
        }
    }

    public String query(String rangeStartTime, String rangeEndTime) {
        Date rangStartDate = stringToDate(rangeStartTime);
        Date rangEndDate = stringToDate(rangeEndTime);
        if (rangStartDate == null || rangEndDate == null) {
            return "日期不合法,e.g. 2019-06-13 14:00:00";
        }

        List<ToDoItem> resultItems = new ArrayList<>();
        for (int i = 0; i < toDoItemList.size(); i++) {
            if (toDoItemList.get(i).getStartTime().getTime() >= rangStartDate.getTime() && toDoItemList.get(i).getEndTime().getTime() <= rangEndDate.getTime()) {
                resultItems.add(toDoItemList.get(i));
            }
        }

        StringBuilder resultString = new StringBuilder();
        for (int i = 0; i < resultItems.size(); i++) {
            resultString.append(resultItems.get(i).getStartTime()).append("-------->").append(resultItems.get(i).getEndTime()).append("  ").append(resultItems.get(i).getLabel());
        }
        return resultString.toString();
    }

    public boolean delete(String itemNo) {
        if (toDoItemList.isEmpty()) {
            return false;
        }

        int itemIndex = Integer.valueOf(itemNo)-1;
        if (itemIndex >= toDoItemList.size()) {
            return false;
        }
        this.toDoItemList.remove(itemIndex);
        return true;
    }

    public boolean clear() {
        this.toDoItemList.clear();
        return true;
    }

    public String show() {
        StringBuilder resultString = new StringBuilder();
        for (int i = 0; i < this.toDoItemList.size(); i++) {

            resultString.append(this.toDoItemList.get(i).getStartTime()).append("-------->").append(this.toDoItemList.get(i).getEndTime()).append("  ").append(this.toDoItemList.get(i).getLabel());
        }
        return resultString.toString();
    }


    private Date stringToDate(String strDate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            // 设置lenient为false. 否则SimpleDateFormat会比较宽松地验证日期，比如2018-02-29会被接受，并转换成2018-03-01
            format.setLenient(false);
            return format.parse(strDate);
        } catch (Exception e) {
            return null;
        }

    }
}