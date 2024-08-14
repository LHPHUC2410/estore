package com.estore.bean;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class MailInfo {
	@NotBlank(message = "Email gửi không được để trống")
	@Email(message = "Email gửi không đúng định dạng")
	String from;
	@Email(message = "Email nhận không đúng định dạng")
	String to;
	String cc;
	String bcc;
	@NotBlank(message = "Chủ đề không được để trống")
	String subject;
	@NotBlank(message = "Nội dung không được để trống")
	String body;
	String files;

	public MailInfo() {
		super();
	}

	public MailInfo(String from, String to, String subject, String body) {
		super();
		this.from = from;
		this.to = to;
		this.subject = subject;
		this.body = body;
	}

	public MailInfo(String from, String to, String cc, String bcc, String subject, String body, String files) {
		super();
		this.from = from;
		this.to = to;
		this.cc = cc;
		this.bcc = bcc;
		this.subject = subject;
		this.body = body;
		this.files = files;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getCc() {
		return cc;
	}

	public void setCc(String cc) {
		this.cc = cc;
	}

	public String getBcc() {
		return bcc;
	}

	public void setBcc(String bcc) {
		this.bcc = bcc;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public String getFiles() {
		return files;
	}

	public void setFiles(String files) {
		this.files = files;
	}

	@Override
	public String toString() {
		return "MailInfo [from=" + from + ", to=" + to + ", cc=" + cc + ", bcc=" + bcc + ", subject=" + subject
				+ ", body=" + body + ", files=" + files + "]";
	}

}
