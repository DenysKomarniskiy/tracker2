package async.actions;

import javax.servlet.AsyncContext;
import utils.Mail;
public class AsyncMailProcessor implements Runnable{
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
			mail.send();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		asyncContext.complete();
		// TODO Auto-generated method stub
		
	}
}
