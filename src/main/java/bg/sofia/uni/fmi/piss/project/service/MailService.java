package bg.sofia.uni.fmi.piss.project.service;

import bg.sofia.uni.fmi.piss.project.domain.Mail;
import bg.sofia.uni.fmi.piss.project.service.result.Result;

public interface MailService {

    Result<Void> sendMail(Mail mail);
}
