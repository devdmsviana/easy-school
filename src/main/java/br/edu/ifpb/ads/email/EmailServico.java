package br.edu.ifpb.ads.email;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.MultiPartEmail;

import br.edu.ifpb.ads.dto.AlunoDTO;
import br.edu.ifpb.ads.model.Aluno;
import br.edu.ifpb.ads.observer.Observador;

public class EmailServico implements Observador {

    private static String remetente = "myseriespoo@gmail.com";
    private static String senha = "eaxv gnrp lcpr ohft";
    private static String assunto = "Agradecemos sua inscrição";

    public String enviarContrato(AlunoDTO aluno) throws Exception {

        MultiPartEmail email = new MultiPartEmail();
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator(remetente, senha));
        email.setSSLOnConnect(true);

        try {
            email.setFrom(remetente);
            email.setSubject(assunto);
            email.setMsg("Olá, " + aluno.getNome() + "!\n\n" + "Seja bem-vindo a Easy! Escola de Idiomas.\n\n"
                    + "Segue em anexo o contrato de matrícula.\n\n" + "Atenciosamente,\n"
                    + "Equipe Easy! Escola de Idiomas");

            EmailAttachment contratoAnexo = new EmailAttachment();
            contratoAnexo.setPath("contrato-easy.pdf");
            contratoAnexo.setName("contrato-matricula.pdf");
            email.attach(contratoAnexo);

            email.addTo(aluno.getEmail());

            email.send();
            return "Email enviado com sucesso!";

        } catch (Exception e) {
            return "Falha ao enviar email!";
        }
    }

    public String enviarCobrancaMensalidade(AlunoDTO aluno) throws Exception {
        MultiPartEmail email = new MultiPartEmail();
        email.setHostName("smtp.gmail.com");
        email.setSmtpPort(465);
        email.setAuthenticator(new DefaultAuthenticator(remetente, senha));
        email.setSSLOnConnect(true);

        try {
            email.setFrom(remetente);
            email.setSubject("Mensalidade Atrasada");
            email.setMsg("Prezado(a) " + aluno.getNome() + ",\n\n" +
                    "Identificamos que sua mensalidade está atrasada. " +
                    "Por favor, regularize sua situação o quanto antes.\n\n" +
                    "Atenciosamente,\n" +
                    "Equipe Easy! Escola de Idiomas");

            email.addTo(aluno.getEmail());

            email.send();
            return "Email de cobrança enviado com sucesso!";
        } catch (Exception e) {
            throw new Exception("Falha ao enviar email de cobrança!");
        }
    }

    @Override
    public void notificar(AlunoDTO aluno) {
        try {
            this.enviarCobrancaMensalidade(aluno);
        } catch (Exception e) {
            e.printStackTrace(); 
        }
    }
}
