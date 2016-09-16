package async.actions;

import javax.servlet.AsyncContext;
import utils.Mail;

public class AsyncMailProcessor implements Runnable {
	private AsyncContext asyncContext;
	private Mail mail;

	public AsyncMailProcessor() {
	}

	public AsyncMailProcessor(AsyncContext asyncCtx, Mail mail) {
		this.asyncContext = asyncCtx;
		this.mail = mail;

	}

	@Override
	public void run() {
		try {
			asyncContext.complete();
			mail.send();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub

	}
}
