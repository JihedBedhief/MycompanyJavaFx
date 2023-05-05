/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeBodyPart;
import javax.activation.DataHandler;
import javax.mail.Multipart;
import javax.mail.internet.MimeMultipart;
import java.net.URL;




/**
 *
 * @author aymen
 */
public class MailVente {
    public static void envoi(String rev, String subject, String text, String imageURL) throws Exception {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        String time = dtf.format(now);

        char heure = time.charAt(0);
        char heure1 = time.charAt(1);
        int wa9t = Character.getNumericValue(heure) + Character.getNumericValue(heure1);

        // Add the image to the email body
       String imageHTML = "<div style=\"width: 200px; margin: 0 auto;\"><img src=\"" + imageURL + "\" alt=\"badge\" style=\"width: 400px; height: 400px; float: right;\"></div>";



        // Build the email body
        String body = "<h2 style=\"color: green;\">Bonsoir, </h2>"
                + "<h1 style=\"color: green;\">" + text + "</h1>"
                + "<h2 style=\"color: green;\">Merci pour vos efforts.</h2>"
                + imageHTML;

        // Send the email
        new Email("anas.basta@esprit.tn", "ezpuhxfaqyuoeytg", rev, subject, body);
    }
}

        
        
    


class Email {
    private String host, port = "587";

    Email(String mailFrom, String password, String mailTo, String subject, String message) throws Exception {
        this.host = "smtp.gmail.com";
        sendEmail(host, port, mailFrom, password, mailTo, subject, message, null);
    }

    private void sendEmail(String host, String port, final String userName, final String password, String toAddress, String subject, String message, String[] attachFiles) throws Exception {
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.user", userName);
        properties.put("mail.password", password);

        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        };
        Session session = Session.getInstance(properties, auth);

        Message msg = new MimeMessage(session);

        msg.setFrom(new InternetAddress(userName));
        InternetAddress[] toAddresses = {new InternetAddress(toAddress)};
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(subject);
        msg.setSentDate(new Date());

        // Créer une partie de message pour l'image
        MimeBodyPart imagePart = new MimeBodyPart();
        imagePart.setHeader("Content-ID", "<logo>");
        URL imageUrl = new URL("https://scontent.xx.fbcdn.net/v/t1.15752-9/324943107_727844712198640_5952788068570103838_n.png?_nc_cat=101&ccb=1-7&_nc_sid=aee45a&_nc_ohc=L9r2DUjzChgAX9h2-di&_nc_ad=z-m&_nc_cid=0&_nc_ht=scontent.xx&oh=03_AdQx9hfEknVIywvznUHzX-TMK_bMzU_NHPwzQhB1a_lbUQ&oe=647A5B3D"); // Remplacez l'URL par votre propre URL d'image
        imagePart.setDataHandler(new DataHandler(imageUrl));

        // Créer une partie de message pour le contenu HTML
        String htmlMessage = "<html><body>" + message + "<br/><img src=\"cid:logo\"/></body></html>";
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(htmlMessage, "text/html");

        // Créer un objet Multipart et y ajouter les parties de message
        Multipart multipart = new MimeMultipart("related");
        multipart.addBodyPart(messageBodyPart);
        multipart.addBodyPart(imagePart);

        // Utiliser la balise <img> pour afficher l'image dans l'e-MailVente
        messageBodyPart.setContent(htmlMessage, "text/html");

        if (attachFiles != null && attachFiles.length > 0) {
            for (String filePath : attachFiles) {
                MimeBodyPart attachPart = new MimeBodyPart();

                try {
                    attachPart.attachFile(filePath);
                } finally {
                    multipart.addBodyPart(attachPart);
                }
            }
        }
        msg.setContent(multipart);

        


    Transport.send(msg);
}
}
