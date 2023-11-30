package kr.smart.hj5.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class MailUtil {

    public static void sendMail() {
        // 세션 생성
        Authenticator auth = new SMTPAuthenticator();
        Session session = Session.getInstance(properties(), auth);

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("hsyle817@gmail.com"));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse("sonic817@hanmail.net")); // 수신자 이메일 주소
            message.setSubject("제목");
            message.setText("내용");

            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 이메일 전송에 필요한 설정 값을 설정하는 메서드
     *
     * @return
     */
    private static Properties properties() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true"); // SMTP 서버 인증을 활성화합니다.
        props.put("mail.smtp.starttls.enable", "true"); // TLS 연결을 활성화합니다. (보안 연결)
        props.put("mail.smtp.host", "smtp.gmail.com"); // Gmail SMTP 서버 호스트 주소
        props.put("mail.smtp.port", "587"); // Gmail SMTP 서버 포트 번호
        return props;
    }

    /**
     * SMTP 서버에 인증하기 위한 사용자 지정 Authenticator 클래스
     * JavaMail API를 사용하여 이메일을 보낼 때 사용됩니다
     */
    private static class SMTPAuthenticator extends javax.mail.Authenticator {
        public PasswordAuthentication getPasswordAuthentication() {
            // 여기서 실제 Gmail 이메일 주소와 앱 비밀번호를 사용합니다.
            return new PasswordAuthentication("hsyle817@gmail.com", "wfxrstvfdsvzjfox");
        }
    }

    /**
     * HTML 템플릿 파일을 읽어와 문자열 형태로 반환합니다.
     *
     * @param templatePath
     * @return
     * @throws IOException
     */
    private static String readHtmlTemplate(String templatePath) throws IOException {
        Path path = Paths.get(templatePath);
        return new String(Files.readAllBytes(path));
    }
}