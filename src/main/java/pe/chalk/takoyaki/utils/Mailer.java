/*
 * Copyright 2014-2015 ChalkPE
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package pe.chalk.takoyaki.utils;

import pe.chalk.takoyaki.Takoyaki;
import pe.chalk.takoyaki.data.Data;
import pe.chalk.takoyaki.data.Member;
import pe.chalk.takoyaki.data.Violation;
import pe.chalk.takoyaki.logger.Logger;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Arrays;
import java.util.Date;
import java.util.Properties;
import java.util.stream.Collectors;

/**
 * @author ChalkPE <amato0617@gmail.com>
 * @since 2015-04-19
 */
public class Mailer {
    public static String USERNAME = "mcpekorea.takoyaki@gmail.com";
    public static String PASSWORD = null;

    public static final String FORMAT_HTML =
            "<link href='http://fonts.googleapis.com/css?family=Inconsolata' rel='stylesheet' type='text/css' />" +
            "<link href='http://fonts.googleapis.com/earlyaccess/nanumgothic.css' rel='stylesheet' type='text/css' />" +
            "<table align=\"center\" width=\"696\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" style=\"padding: 20px; background-color: white;\">" +
                "<tbody>" +
                    "<tr>" +
                        "<td align=\"left\">" +
                            "<img src=\"http://i.imgur.com/M9peKSu.png\">" +
                            "<br>" +
                        "</td>" +
                    "</tr>" +
                    "<tr>" +
                        "<td style=\"line-height: 0; height: 4px; font-size: 0px;\">" +
                            "&nbsp;" +
                        "</td>" +
                    "</tr>" +
                    "<tr>" +
                        "<td style=\"line-height: 0; height: 3px; border-top-width: 3px; border-top-style: solid; border-top-color: rgb(85, 79, 76); font-size: 0px;\">" +
                            "&nbsp;" +
                        "</td>" +
                    "</tr>" +
                    "<tr>" +
                        "<td>" +
                            "<table cellpadding=\"0\" cellspacing=\"0\" border=\"0\">" +
                                "<tbody>" +
                                    "<tr>" +
                                        "<td style=\"width: 515px;\">" +
                                            "<img src=\"http://i.imgur.com/4R1O3ou.png\">" +
                                            "<br>" +
                                        "</td>" +
                                        "<td>" +
                                            "<img src=\"http://i.imgur.com/uGKJ5mx.png\">" +
                                            "<br>" +
                                        "</td>" +
                                    "</tr>" +
                                "</tbody>" +
                            "</table>" +
                        "</td>" +
                    "</tr>" +
                    "<tr>" +
                        "<td style=\"line-height: 0; height: 3px; font-size: 0px;\">" +
                            "<img src=\"http://i.imgur.com/Y2n9zpJ.png\">" +
                            "<br>" +
                        "</td>" +
                    "</tr>" +
                    "<tr>" +
                        "<td style=\"line-height: 0; height: 25px; font-size: 0px;\">" +
                            "&nbsp;" +
                        "</td>" +
                    "</tr>" +
                    "<tr>" +
                        "<td style=\"font-size: 16px; font-weight: bold;\">" +
                            "<img src=\"http://i.imgur.com/1NS81yU.png\">" +
                            "<br>" +
                        "</td>" +
                    "</tr>" +
                    "<tr>" +
                        "<td style=\"line-height: 0; height: 22px; font-size: 0px;\">" +
                            "&nbsp;" +
                        "</td>" +
                    "</tr>" +
                    "<tr>" +
                        "<td style=\"border: 5px solid #555555; background: #2B2B2B;\">" +
                            "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\">" +
                                "<tbody>" +
                                    "<tr>" +
                                        "<td style=\"line-height: 0; width: 29px; height: 22px; font-size: 0px;\">" +
                                            "&nbsp;" +
                                        "</td>" +
                                        "<td></td>" +
                                    "</tr>" +
                                    "<tr>" +
                                        "<td>" +
                                            "&nbsp;" +
                                        "</td>" +
                                        "<td>" +
                                            "<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\">" +
                                                "<tbody>" +
                                                    "<tr>" +
                                                        "<td>" +
                                                            "<span style=\"color: #BBBBBB; font-family: 'Inconsolata', 'Nanum Gothic', 'NanumGothic'; line-height: 19px;\">" +
                                                                "%s" +
                                                            "</span>" +
                                                        "</td>" +
                                                    "</tr>" +
                                                "</tbody>" +
                                            "</table>" +
                                        "</td>" +
                                    "</tr>" +
                                    "<tr>" +
                                        "<td style=\"line-height: 0; height: 20px; font-size: 0px;\">" +
                                            "&nbsp;" +
                                        "</td>" +
                                        "<td></td>" +
                                    "</tr>" +
                                "</tbody>" +
                            "</table>" +
                        "</td>" +
                    "</tr>" +
                    "<tr>" +
                        "<td style=\"line-height: 0; height: 20px; font-size: 0px;\">" +
                            "&nbsp;" +
                        "</td>" +
                    "</tr>" +
                    "<tr>" +
                        "<td style=\"line-height: 19px; font-size: 12px; color: rgb(102, 102, 102);\">" +
                            "해당 메일을 확인하시고 별도로 주의 및 경고 처리 해 주세요!" +
                        "</td>" +
                    "</tr>" +
                    "<tr>" +
                        "<td style=\"line-height: 0; height: 20px; font-size: 0px;\">" +
                            "&nbsp;" +
                        "</td>" +
                    "</tr>" +
                    "<tr>" +
                        "<td style=\"line-height: 19px; font-size: 12px; color: rgb(102, 102, 102);\">" +
                            "%s" +
                        "</td>" +
                    "</tr>" +
                    "<tr>" +
                        "<td style=\"line-height: 0; height: 20px; font-size: 0px;\">" +
                            "&nbsp;" +
                        "</td>" +
                    "</tr>" +
                    "<tr>" +
                        "<td align=\"center\" style=\"font-size: 16px; font-weight: bold; color: rgb(102, 102, 102)\">" +
                            "© 2014-2015 ChalkPE. All rights reserved." +
                        "</td>" +
                    "</tr>" +
                    "<tr>" +
                        "<td style=\"line-height: 0; height: 4px; font-size: 0px;\">" +
                            "&nbsp;" +
                        "</td>" +
                    "</tr>" +
                    "<tr>" +
                        "<td style=\"line-height: 0; height: 0px; border-top-width: 2px; border-top-style: solid; border-top-color: rgb(85, 79, 76); font-size: 0px;\">" +
                            "&nbsp;" +
                        "</td>" +
                    "</tr>" +
                "</tbody>" +
            "</table>";

    private Mailer(){}

    public static void send(String subject, String body, Object[] recipients){
        new Thread(() -> {
            if(Mailer.PASSWORD == null){
                throw new IllegalStateException("Mailer.PASSWORD must not be null");
            }

            Properties properties = new Properties();
            properties.put("mail.smtps.auth", "true");

            Session session = Session.getDefaultInstance(properties);
            MimeMessage message = new MimeMessage(session);

            try{
                message.setSubject(subject);
                message.setHeader("Content-Type", "text/html; charset=\"utf-8\"");
                message.setContent(TextFormat.replaceTo(TextFormat.Type.HTML, body), "text/html; charset=\"utf-8\"");
                message.setFrom(new InternetAddress(Mailer.USERNAME));

                if(recipients == null){
                    throw new IllegalArgumentException("recipients must not be null");
                }

                try{
                    for(Object recipient : recipients){
                        if(recipient instanceof Address){
                            message.addRecipient(Message.RecipientType.TO, (Address) recipient);
                        }else if(recipient instanceof Member){
                            message.addRecipient(Message.RecipientType.TO, ((Member) recipient).getInternetAddress());
                        }else if(recipient instanceof String){
                            message.addRecipient(Message.RecipientType.TO, new InternetAddress((String) recipient));
                        }
                    }
                }catch(MessagingException e){
                    Takoyaki.getInstance().getLogger().error(e.getMessage());
                }

                Transport transport = session.getTransport("smtps");
                transport.connect("smtp.gmail.com", Mailer.USERNAME, Mailer.PASSWORD);
                transport.sendMessage(message, message.getAllRecipients());
                transport.close();

                Takoyaki.getInstance().getLogger().info(String.format("메일이 발송되었습니다: %s -> %s", subject, Arrays.asList(message.getAllRecipients()).stream().map(Address::toString).collect(Collectors.joining(", "))));
            }catch(Exception e){
                Takoyaki.getInstance().getLogger().error(e.getMessage());
            }
        }).start();
    }

    public static void sendMail(String prefix, String subject, String body, Object[] recipients){
        send(String.format("[%s] [%s] %s", Takoyaki.getInstance().getPrefix(), prefix, subject), String.format(Mailer.FORMAT_HTML, body, Mailer.getFooter()), recipients);
    }

    public static void sendViolation(Violation violation, Object[] recipients){
        String body = String.format("[%s] %s\n\n%s\n\n작성자: %s", violation.getLevel().toString(), violation.getName(), String.join("\n", Arrays.asList(violation.getViolations()).stream().map(Data::toString).collect(Collectors.toList())), violation.getViolator());

        violation.getTarget().getLogger().warning(body);
        violation.getTarget().getLogger().newLine();

        sendMail(violation.getPrefix(), violation.getName(), body, recipients);
    }

    public static String getFooter(){
        return String.format("발신 시각: %s\n서버 정보: %s - %s %s\n자바 버전: %s\n타코야키 버전: %s",
                Logger.SIMPLE_DATE_FORMAT.format(new Date()), System.getProperty("user.name"), System.getProperty("os.name"), System.getProperty("os.arch"), System.getProperty("java.version"), Takoyaki.VERSION
        );
    }
}