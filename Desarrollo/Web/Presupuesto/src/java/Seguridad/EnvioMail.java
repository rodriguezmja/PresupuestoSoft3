/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Seguridad;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
//import javax.swing.JOptionPane;

/**
 *
 * @author rodriguezja
 */
public class EnvioMail {

    private static String Username = "estacaequipetrol@gmail.com";
    private static String PassWord = "S3cretariosEquipetrol";
    private String Mensage = "Se ha solicitado la recuperacion de su contraseña, por favor haga clic en el siguiente link: http://www.google.com";
    private String To = "";
    private String Subject = "Recuperacion Password";

    public EnvioMail(String correoTo) {
        this.setTo(correoTo);
//        this.setMensage(mensaje);
//        this.setSubject(subject);
        SendMail();
    }

    public EnvioMail() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void SendMail() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "25");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(Username, PassWord);
            }
        });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(Username));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(To));
            message.setSubject(Subject);
            message.setText(Mensage);

            Transport.send(message);
            System.out.println("su mensaje ha sido enviado");
            //JOptionPane.showMessageDialog(this, "Su mensaje ha sido enviado");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String getUsername() {
        return Username;
    }

    public static void setUsername(String Username) {
        EnvioMail.Username = Username;
    }

    public static String getPassWord() {
        return PassWord;
    }

    public static void setPassWord(String PassWord) {
        EnvioMail.PassWord = PassWord;
    }

    public String getMensage() {
        return Mensage;
    }

    public void setMensage(String Mensage) {
        this.Mensage = Mensage;
    }

    public String getTo() {
        return To;
    }

    public void setTo(String To) {
        this.To = To;
    }

    public String getSubject() {
        return Subject;
    }

    public void setSubject(String Subject) {
        this.Subject = Subject;
    }

}
