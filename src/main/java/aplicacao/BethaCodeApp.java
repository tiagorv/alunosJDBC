package aplicacao;

import DAO.AlunoDAO;
import conexao.ConexaoJDBC;
import modelo.Aluno;

import java.sql.Connection;
import java.util.List;

public class BethaCodeApp {
    public static void main(String[] args) {
        AlunoDAO alunoDAO = new AlunoDAO();

        //CRIAÇÃO DE ALUNO
        //Aluno aluno01 = new Aluno("Joao da Silva", 18, "São Paulo", "SP");
        //alunoDAO.criarAluno(aluno01);

        //Lista todos os alunos
        //List<Aluno> alunosNoBanco = alunoDAO.buscarAlunos();
        //for (Aluno aluno : alunosNoBanco) {
        //    System.out.println(aluno);
        //}

        //Aluno alunoPorId = alunoDAO.buscarPorId(2);
        //System.out.println(alunoPorId);


        /*Aluno alunoAtualizar = alunoDAO.buscarPorId(1);
        alunoAtualizar.setNome("Tiago Valério");
        alunoDAO.atualizarAluno(alunoAtualizar);*/

        Aluno alunoExcluir = alunoDAO.buscarPorId(4);
        alunoDAO.excluirAluno(alunoExcluir);

    }
}
