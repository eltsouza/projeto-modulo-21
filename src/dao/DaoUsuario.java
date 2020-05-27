package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import connection.ConnectionDataBase;
import entidades.Usuario;

public class DaoUsuario {

	private Connection connection;

	public DaoUsuario() {

		connection = ConnectionDataBase.getConnection();

	}
	// criando o metodo para salvar os registros no banco de dados
	public void gravarImagem(String fileUpload) throws SQLException {
		try {
			String sql = "insert into usuario(login,senha,fotobase64,contenttype) " 
					+ " values(?,?,?,?)";
			
			String tipoArquivo = fileUpload.split(",")[0].split(";")[0].split("/")[1];
			System.out.println("tipoArquivo.:"+tipoArquivo);
			
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, "teste");
			insert.setString(2, "1234");
			insert.setString(3,fileUpload);
			insert.setString(4,tipoArquivo);
			insert.execute();
			connection.commit();
		} catch (Exception e) {
			connection.rollback();
			e.printStackTrace();
		}
	}	
	

	public List<Usuario> getUsuarios()
			throws SQLException {
		List<Usuario> listar = new ArrayList<Usuario>();
		
		String sql = "select id, login, nome,cidade, fotobase64 from usuario";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			Usuario userLogado = new Usuario();
			userLogado.setId(resultSet.getLong("id"));
			userLogado.setLogin(resultSet.getString("login"));
			userLogado.setNome(resultSet.getString("nome"));
			userLogado.setCidade(resultSet.getString("cidade"));
			userLogado.setFotobase64(resultSet.getString("fotobase64"));

			listar.add(userLogado);
		}
		
		return listar;
	}
	public Usuario buscaImagem(String iduser) {

		String sql = "select id,login,senha,fotobase64,contenttype from usuario where id = " + iduser; 
		
		try {
			PreparedStatement buscaImagem = connection.prepareStatement(sql);
			ResultSet resultSet = buscaImagem.executeQuery();
			
			while (resultSet.next()) {
				Usuario usuario = new Usuario();
				usuario.setId(resultSet.getLong("id"));
				usuario.setLogin(resultSet.getString("login"));
				usuario.setSenha(resultSet.getString("senha"));
				usuario.setFotobase64(resultSet.getString("fotobase64"));
				usuario.setContenttype(resultSet.getString("contenttype"));
				
				return usuario;
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;		
	}	
}
