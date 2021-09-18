package DAO;

import conexao.ConexaoJDBC;
import modelo.Aluno;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AlunoDAO {
    private Connection minhaConexao;

    public AlunoDAO() {
        this.minhaConexao = ConexaoJDBC.getConnection();
    }

    public void criarAluno(Aluno novoAluno) {
        String meuSql = "insert into aluno(nome, idade, cidade, estado) values (?,?,?,?)";
        try {
            PreparedStatement preparedStatement = minhaConexao.prepareStatement(meuSql);
            preparedStatement.setString(1, novoAluno.getNome());
            preparedStatement.setInt(2, novoAluno.getIdade());
            preparedStatement.setString(3, novoAluno.getCidade());
            preparedStatement.setString(4, novoAluno.getEstado());
            int linhaInserida = preparedStatement.executeUpdate();
            System.out.println("Aluno inserido ao banco de dados!!!Inserido " + linhaInserida + ".");
        } catch (SQLException e) {
            System.out.println("Erro de Banco de Dados: " + e.getMessage());
        }
    }

    public List<Aluno> buscarAlunos(){
        List<Aluno> alunosCadastrados = new ArrayList<>();
        String meuSql = "select * from aluno";
        try{
            PreparedStatement preparedStatement = minhaConexao.prepareStatement(meuSql);
            ResultSet resultadoSql = preparedStatement.executeQuery();
            while(resultadoSql.next()){
                Aluno cadastrado = new Aluno();
                cadastrado.setId(resultadoSql.getInt("id"));
                cadastrado.setNome(resultadoSql.getString("nome"));
                cadastrado.setIdade(resultadoSql.getInt("idade"));
                cadastrado.setCidade(resultadoSql.getString("cidade"));
                cadastrado.setEstado(resultadoSql.getString("estado"));
                alunosCadastrados.add(cadastrado);
            }
        }catch (SQLException e){
            System.out.println("Erro com o banco de dados: " + e.getMessage());
        }
        return alunosCadastrados;
    }

    public Aluno buscarPorId(int id){
        Aluno alunoCadastrado = new Aluno();
        String meuSql = "select * from aluno where id = ?";
        try{
            PreparedStatement preparedStatement = minhaConexao.prepareStatement(meuSql);
            preparedStatement.setInt(1, id);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado.next()){
                alunoCadastrado.setId(resultado.getInt("id"));
                alunoCadastrado.setNome(resultado.getString("nome"));
                alunoCadastrado.setIdade(resultado.getInt("idade"));
                alunoCadastrado.setCidade(resultado.getString("cidade"));
                alunoCadastrado.setEstado(resultado.getString("estado"));
           }
        }catch (SQLException e){
            System.out.println("Erro com banco de dados: " + e.getMessage());
        }
        return alunoCadastrado;
    }

    public void atualizarAluno(Aluno novoAluno) {
        String meuSql = "update aluno set nome = ?," +
                " idade = ?, cidade = ?, estado = ?  where id = ?";
        try {
            PreparedStatement preparedStatement = minhaConexao.prepareStatement(meuSql);
            preparedStatement.setString(1, novoAluno.getNome());
            preparedStatement.setInt(2, novoAluno.getIdade());
            preparedStatement.setString(3, novoAluno.getCidade());
            preparedStatement.setString(4, novoAluno.getEstado());
            preparedStatement.setInt(5, novoAluno.getId());
            int linhaAtualizada = preparedStatement.executeUpdate();
            System.out.println("Aluno: " + novoAluno.getId() + " atualizado com sucesso!!");
        } catch (SQLException e) {
            System.out.println("Erro de Banco de Dados: " + e.getMessage());
        }
    }

    public void excluirAluno(Aluno novoAluno) {
        String meuSql = "delete from aluno where id = ?";
        try {
            PreparedStatement preparedStatement = minhaConexao.prepareStatement(meuSql);
            preparedStatement.setInt(1, novoAluno.getId());
            int linhaAtualizada = preparedStatement.executeUpdate();
            System.out.println("Aluno: " + novoAluno.getId() + " excluídocom sucesso!!");
        } catch (SQLException e) {
            System.out.println("Erro de Banco de Dados: " + e.getMessage());
        }
    }


}
