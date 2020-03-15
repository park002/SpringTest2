package util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class Gmail  extends Authenticator{

	protected PasswordAuthentication  getPasswordAuthentication() {
		return  new PasswordAuthentication("ekekekem159@gmail.com","thsdhrhd12@");
	}
}
