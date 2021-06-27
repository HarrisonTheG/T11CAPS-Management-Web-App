package sg.edu.iss.caps.model;

import lombok.Data;

@Data
public class MailVo {

    public MailVo(){

    }

    public MailVo(String from, String to, String subject, String text) {
        this.from = from;
        this.to = to;
        this.subject = subject;
        this.text = text;
    }

    //sent from whom
    private String from;

    //sent to whom
    private String to;

    private String subject;

    //content
    private String text;
}