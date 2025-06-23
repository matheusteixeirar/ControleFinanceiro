package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DAO {
	
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://127.0.0.1:3306/controle_despesas?useTimezone=true&serverTimezone=UTC";
	private String user = "root";
	private String password = "cd@12345";
			
	/** METODO DE CONEXAO **/
	private Connection conectar() {
		Connection con = null; 
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, user, password); 
			return con; 
		} catch (Exception e) {
			System.out.println(e); 
			return null;
		}
	}
	
	/** CRUD CREATE **/
	public void criarTransacao (JavaBeans transacao) {
		String create = "INSERT INTO transacao (tipo, valor, categoria, descricao, data) VALUES (?, ?, ?, ?, ?)";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(create); 
			pst.setString(1, transacao.getTipo());
			pst.setString(2, transacao.getValor());
			pst.setString(3, transacao.getCategoria());
			pst.setString(4, transacao.getDescricao());
			pst.setString(5, transacao.getData());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/** CRUD READ (receitas/despesas) **/
	public ArrayList<JavaBeans> listarTransacoes(JavaBeans transacao) {
		ArrayList<JavaBeans> transacaolist = new ArrayList<>();
		String read = "SELECT * FROM transacao WHERE tipo = ? ORDER BY data DESC";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);
			pst.setString(1, transacao.getTipo());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String id = rs.getString(1);
				String tipo = rs.getString(2);
				String valor = rs.getString(5);
				String categoria = rs.getString(3);
				String descricao = rs.getString(4);
				String data = rs.getString(6);
				transacaolist.add(new JavaBeans (id, tipo, categoria, descricao, valor, data));
			}
			con.close();
			return transacaolist;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}
	
	/** CRUD UPDATE **/
	public void selecionarTransacao(JavaBeans transacao) {
		String read2 = "SELECT * FROM transacao WHERE id = ?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read2);
			pst.setString(1, transacao.getId());
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				transacao.setCategoria(rs.getString(3));
				transacao.setValor(rs.getString(5));
				transacao.setDescricao(rs.getString(4));
				transacao.setData(rs.getString(6));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	public void alterarTransacao(JavaBeans transacao) {
		String update = "UPDATE transacao SET valor = ?, categoria = ?, descricao = ?, data = ? WHERE id = ?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(update);
			pst.setString(1, transacao.getValor());
			pst.setString(2, transacao.getCategoria());
			pst.setString(3, transacao.getDescricao());
			pst.setString(4, transacao.getData());
			pst.setString(5, transacao.getId());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/** CRUD DELETE **/
	public void deletarTransacao (JavaBeans transacao) {
		String delete = "DELETE FROM transacao WHERE id = ?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(delete);
			pst.setString(1, transacao.getId());
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	/** SOMAR VALORES TRANSAÇÕES**/
	public int somarTotalTransacoes(String tipo) {
	    int soma = 0;
	    String sql = "SELECT SUM(valor) FROM transacao WHERE tipo = ?";
	    try {
	        Connection con = conectar();
	        PreparedStatement pst = con.prepareStatement(sql);
	        pst.setString(1, tipo);
	        ResultSet rs = pst.executeQuery();
	        while (rs.next()) {
	            soma = rs.getInt(1);
	        }
	        con.close();
	    } catch (Exception e) {
	        System.out.println(e);
	    }
	    return soma;
	}
	
}
