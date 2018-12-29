package bg.sofia.uni.fmi.piss.project.service;

import bg.sofia.uni.fmi.piss.project.service.result.Result;

public interface MailService {

    Result<Void> sendMail(String message, String subject, String receiverEmail);
}
