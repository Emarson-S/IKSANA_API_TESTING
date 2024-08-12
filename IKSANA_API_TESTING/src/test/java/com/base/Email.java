package com.base;

import java.io.IOException;
import java.util.Arrays;

import com.mailosaur.MailosaurClient;
import com.mailosaur.MailosaurException;
import com.mailosaur.models.Attachment;
import com.mailosaur.models.Message;
import com.mailosaur.models.MessageCreateOptions;
import com.mailosaur.models.MessageSearchParams;
import com.mailosaur.models.SearchCriteria;

public class Email {

    public static String apiKey = "rKChpQ9e0kitlfKbneI8GJEA01PN0qO0";
    public static String serverId = "grtpxbl8";
    public static String serverDomain = "grtpxbl8.mailosaur.net";



    public static String generateEmail(){
        return "user"+ System.currentTimeMillis()+"@"+serverDomain;

    }



public static String readEmail(String email) throws IOException, MailosaurException{

    MailosaurClient mailosaur = new MailosaurClient(apiKey);
    MessageSearchParams params = new MessageSearchParams();
    params.withServer(serverId);
    SearchCriteria criteria = new SearchCriteria();
    criteria.withSentTo(email);
    Message message = mailosaur.messages().get(params, criteria);
    String value =message.html().codes().get(0).value();
    return value;

}


public static void sentEmail() throws IOException, MailosaurException{


    Attachment attachment = new Attachment();
    attachment.withFileName("Release notes");
    attachment.withContentType("image/png");
    attachment.withContent("SGkgSmVldmEsCgpQbGVhc2UgZmluZSBteSB0ZXN0aW5nIGRvY3VtZW50cw==");

    MailosaurClient mailosaur = new MailosaurClient(apiKey);
    MessageCreateOptions options = new MessageCreateOptions();
    options.withTo("jeevabalin.r@mavens-i.com") // must be a verified address
    .withSubject("Testing Documents")
    .withHtml("<p>Hi anna.</p>")
    .withSend(true)
    .withAttachments(Arrays.asList(new Attachment[] { attachment }));

    mailosaur.messages().create(serverId, options);
}





public static void main(String[] args) throws IOException, MailosaurException {
    sentEmail();
}


}