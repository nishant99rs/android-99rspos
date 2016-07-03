package Pojo;

/**
 * Created by Rahul on 2/22/2016.
 */
public class ShowModel {
    String TICKET_ID;
    String SUPPORT_TICKET_STATUS;
    String SUBJECT_DESC;
    String TEAM_MEMBER;

    public String getTimeStamp() {
        return TimeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        TimeStamp = timeStamp;
    }

    String TimeStamp;

    public String getTICKET_ID() {
        return TICKET_ID;
    }

    public void setTICKET_ID(String TICKET_ID) {
        this.TICKET_ID = TICKET_ID;
    }

    public String getSUPPORT_TICKET_STATUS() {
        return SUPPORT_TICKET_STATUS;
    }

    public void setSUPPORT_TICKET_STATUS(String SUPPORT_TICKET_STATUS) {
        this.SUPPORT_TICKET_STATUS = SUPPORT_TICKET_STATUS;
    }

    public String getSUBJECT_DESC() {
        return SUBJECT_DESC;
    }

    public void setSUBJECT_DESC(String SUBJECT_DESC) {
        this.SUBJECT_DESC = SUBJECT_DESC;
    }

    public String getTEAM_MEMBER() {
        return TEAM_MEMBER;
    }

    public void setTEAM_MEMBER(String TEAM_MEMBER) {
        this.TEAM_MEMBER = TEAM_MEMBER;
    }


}
