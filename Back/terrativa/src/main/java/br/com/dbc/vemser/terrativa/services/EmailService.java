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

    public void sendEmailUsuario(ResponseUsuarioDTO responseUsuario, Integer status) throws Exception {
        switch (status){
            case 1:
                Map<String, Object> listaPessoasCreate = new HashMap<>();
                listaPessoasCreate.put("nome", responseUsuario.getNome() + " " + responseUsuario.getSobrenome());
                listaPessoasCreate.put("id", responseUsuario.getUsuarioId());
                listaPessoasCreate.put("email", from);
                listaPessoasCreate.put("acao", "Criação de cadastro.");
                String templateCreate = "usuario-template.ftl";
                String emailCreate = responseUsuario.getEmail();
                sendEmail(listaPessoasCreate, templateCreate, emailCreate);
                break;
            case 2:
                Map<String, Object> listaPessoasUpdate = new HashMap<>();
                listaPessoasUpdate.put("nome", responseUsuario.getNome() + " " + responseUsuario.getSobrenome());
                listaPessoasUpdate.put("email", from);
                listaPessoasUpdate.put("id", responseUsuario.getUsuarioId());
                listaPessoasUpdate.put("acao", "Atualização de cadastro.");
                String templateUpdate = "usuario-template.ftl";
                String emailUpdate = responseUsuario.getEmail();
                sendEmail(listaPessoasUpdate, templateUpdate, emailUpdate);
                break;
            case 3:
                Map<String, Object> listaPessoasDelete = new HashMap<>();
                listaPessoasDelete.put("nome", responseUsuario.getNome() + " " +  responseUsuario.getSobrenome());
                listaPessoasDelete.put("email", from);
                listaPessoasDelete.put("id", responseUsuario.getUsuarioId());
                listaPessoasDelete.put("acao", "Remoção de cadastro.");
                String templateDelete = "usuario-template.ftl";
                String emailDelete = responseUsuario.getEmail();
                sendEmail(listaPessoasDelete, templateDelete, emailDelete);
                break;
        }
    }


    public void sendEmail(Map mapa, String template, String emailTO) throws Exception {
        MimeMessage mimeMessage = emailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setTo(emailTO);
            mimeMessageHelper.setSubject("Confirmação de Ação no Site TerrAtiva");
            mimeMessageHelper.setText(geContentFromTemplate(mapa, template), true);
            emailSender.send(mimeMessageHelper.getMimeMessage());
        } catch (MessagingException | IOException | TemplateException e) {
            throw new Exception(e.getMessage());
        }
    }

    public String geContentFromTemplate(Map mapa, String tamplete) throws IOException, TemplateException {
        Template template = fmConfiguration.getTemplate(tamplete);
        return FreeMarkerTemplateUtils.processTemplateIntoString(template, mapa);
    }
}

