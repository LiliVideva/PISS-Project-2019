package bg.sofia.uni.fmi.piss.project.service.result;

import bg.sofia.uni.fmi.piss.project.service.BaseService;
import bg.sofia.uni.fmi.piss.project.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;


@Service
@PropertySource(value = "classpath:mail.properties")
public class MailServiceImpl extends BaseService implements MailService {
    private static final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

    @Autowired
    private JavaMailSender javaMailSender;

    @Override
    public Result<Void> sendMail(String message, String subject, String receiverEmail) {
        MimeMessage mailMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mailMessage);
        try {
            helper.setTo(receiverEmail);
            helper.setSubject(subject);
            helper.setText(message);
            javaMailSender.send(mailMessage);
        } catch (MessagingException e) {
            logger.error("Error occured while sending email", e);
            return serverError();
        }
        return ok();
    }
}
