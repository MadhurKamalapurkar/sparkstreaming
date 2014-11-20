package com.daksh.project.agent;

public interface MailAgent {

	public boolean sendMail(String name, String mailID, String subject,
			String msg);

}
