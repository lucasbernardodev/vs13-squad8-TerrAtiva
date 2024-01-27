package br.com.dbc.vemser.terrativa.services;

import br.com.dbc.vemser.terrativa.dto.reponses.ResponseUsuarioDTO;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class EmailService {

    private final freemarker.template.Configuration fmConfiguration;

    private final JavaMailSender emailSender;

    @Value("${spring.mail.username}")
    private String from;
    private String to = "leonardo.rauber@dbccompany.com.br";

    public void sendEmailUsuario(ResponseUsuarioDTO responseUsuario, Integer status) throws Exception {
        switch (status){
            case 1:
                Map<String, Object> listaPessoasCreate = new HashMap<>();
                listaPessoasCreate.put("nome", responseUsuario.getNome());
                listaPessoasCreate.put("id", responseUsuario.getUsuarioId());
                listaPessoasCreate.put("email", from);
                String templateCreate = "createusuario-template.ftl";
                String assuntoCreate = "Usuário criado";
                String emailCreate = responseUsuario.getEmail();
                sendEmail(listaPessoasCreate, templateCreate, assuntoCreate, emailCreate);
                break;
            case 2:
                Map<String, Object> listaPessoasUpdate = new HashMap<>();
                listaPessoasUpdate.put("nome", responseUsuario.getNome());
                listaPessoasUpdate.put("email", from);
                String templateUpdate = "updateusuario-template.ftl";
                String assuntoUpdate = "Usuário atualizado";
                String emailUpdate = responseUsuario.getEmail();
                sendEmail(listaPessoasUpdate, templateUpdate, assuntoUpdate, emailUpdate);
                break;
        }
    }


    public void sendEmail(Map mapa, String template, String assunto, String emailTO) throws Exception {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(emailTO);
            mimeMessageHelper.setSubject(assunto);
            mimeMessageHelper.setText(geContentFromTemplate(mapa, template), true);
            emailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException | IOException | TemplateException e) {
            e.getMessage();
            throw new Exception(e.getMessage());
        }
    }


    public String geContentFromTemplate(Map mapa, String tamplete) throws IOException, TemplateException {
        Template template = fmConfiguration.getTemplate(tamplete);
        String html = FreeMarkerTemplateUtils.processTemplateIntoString(template, mapa);
        return html;
    }
}

