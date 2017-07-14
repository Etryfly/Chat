package Chat;

import java.util.Date;

/**
 * Created by etryfly on 14.07.17.
 */
public class Message {
    private String data;
    private String user;
    private Date date;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
