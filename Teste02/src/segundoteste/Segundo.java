package segundoteste;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Segundo {
    private static int nextId = 1;
    private Map<Integer, String> candidatos;
    private Map<Integer, String> statusCandidatos;

    public Segundo() {
        candidatos = new HashMap<>();
        statusCandidatos = new HashMap<>();
    }

    public int iniciarProcesso(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome inválido");
        }

        for (String candidato : candidatos.values()) {
            if (candidato.equalsIgnoreCase(nome)) {
                throw new IllegalArgumentException("Candidato já participa do processo.");
            }
        }

        int id = nextId++;
        candidatos.put(id, nome);
        statusCandidatos.put(id, "Recebido");
        return id;
    }

    public void marcarEntrevista(int codCandidato) {
        if (!candidatos.containsKey(codCandidato)) {
            throw new IllegalArgumentException("Candidato não encontrado.");
        }

        if (!statusCandidatos.get(codCandidato).equals("Recebido")) {
            throw new IllegalStateException("Não é possível marcar entrevista para esse candidato.");
        }

        statusCandidatos.put(codCandidato, "Qualificado");
    }

    public void desqualificarCandidato(int codCandidato) {
        if (!candidatos.containsKey(codCandidato)) {
            throw new IllegalArgumentException("Candidato não encontrado.");
        }

        candidatos.remove(codCandidato);
        statusCandidatos.remove(codCandidato);
    }

    public String verificarStatusCandidato(int codCandidato) {
        if (!candidatos.containsKey(codCandidato)) {
            throw new IllegalArgumentException("Candidato não encontrado.");
        }

        return statusCandidatos.get(codCandidato);
    }

    public void aprovarCandidato(int codCandidato) {
        if (!candidatos.containsKey(codCandidato)) {
            throw new IllegalArgumentException("Candidato não encontrado.");
        }

        if (!statusCandidatos.get(codCandidato).equals("Qualificado")) {
            throw new IllegalStateException("Não é possível aprovar esse candidato.");
        }

        statusCandidatos.put(codCandidato, "Aprovado");
    }

    public List<String> obterAprovados() {
        List<String> aprovados = new ArrayList<>();

        for (Map.Entry<Integer, String> entry : statusCandidatos.entrySet()) {
            if (entry.getValue().equals("Aprovado")) {
                aprovados.add(candidatos.get(entry.getKey()));
            }
        }

        return aprovados;
    }
}
