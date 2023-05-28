package com.example.myapplication;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class zzz_DaoOld {
    Connection con;

    public zzz_DaoOld() {
        con = zzz_ConexaoOld.getConexaoMySQL();
    }

    public ArrayList<Alimento> readAlimentos() throws SQLException {
            Statement st = con.createStatement();
            String sql = ("SELECT * FROM alimentos ORDER BY nome;");
            ResultSet rs = st.executeQuery(sql);
            ArrayList<Alimento> alimentos = new ArrayList<Alimento>();
            while(rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String medida = rs.getString("med");
                Double quantidade = rs.getDouble("qtd");
                Double carboidratos = rs.getDouble("carb");
                Double proteinas = rs.getDouble("prot");
                Double gorduras = rs.getDouble("gord");
                Alimento alimento = new Alimento(id, nome, medida, quantidade, carboidratos, proteinas, gorduras);
                alimentos.add(alimento);
            }
           return alimentos;
        }
}
