import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class MusicaDAO{
    public void cadastrar(Musica musica) throws Exception{
       //1. Especificar o comando SQL
        String sql = "INSERT INTO tb_musica(titulo, ativo) VALUES(?,1)";

       //2. Estabelecer uma conexão com o SGDB (postgreSQL)
        var conexao = ConnectionFactory.conectar();

       //3. Preparar o comando
        PreparedStatement ps = conexao.prepareStatement(sql);

       //4. Substituir os eventuais placeholders
        ps.setString(1, musica.getTitulo());

       //5. Executar o Comando
       ps.execute();

       //6. Fechar os Recursos
        ps.close();
        conexao.close();
    }
    public void avaliar(Musica musica) throws Exception{
        //1. Especificar o comando SQL
        var sql = "UPDATE tb_musica SET avaliacao=? WHERE titulo=?";
        //2. Estabelecer uma conexão com o banco
        // var conexao = ConnectionFactory.conectar(); desta maneira é necssario fechar o recurso
        //try-with-resources abrir os recursos sem precisar fechar
        try(
            var conexao = ConnectionFactory.conectar();
            //3. Preparar o comando
            PreparedStatement ps = conexao.prepareStatement(sql);
        ){
            //4. Substituir os eventuais placeholder
            ps.setInt(1, musica.getAvaliacao());
            ps.setString(2, musica.getTitulo());
            //5. Executar
            ps.execute();
            //6. Fechar os recursos
            //O try já fecha
        }
        
    }
    public ArrayList listar() throws Exception{

        var musicas = new ArrayList<Musica>();
        //1. Especificar o comando SQL
        var sql = "SELECT titulo, avaliacao FROM tb_musica WHERE ativo = 1";
        //2. Abrir uma conexão com o banco
        try(
            var conexao = ConnectionFactory.conectar();
            //3. Preparar o comando
            PreparedStatement ps = conexao.prepareStatement(sql);
        ){
            try(
                //5. Executar o comando
                ResultSet rs = ps.executeQuery();
            )
            {
                //6. Manipular os dados da tabela resultante
                while(rs.next()){
                    String titulo = rs.getString("titulo");
                    int avaliacao = rs.getInt("avaliacao");
                    var musica = new Musica(titulo, avaliacao);
                    musicas.add(musica);
                }
            }
            
        //7. Fechar tudo
        //o try-with-resources já fez
        }
        return musicas;
    }
    public void remover(Musica musica) throws Exception{
        //1. Especificar o comando SQL
        var sql = "UPDATE tb_musica SET ativo=0 WHERE titulo=?";
        //2. Estabelecer uma conexão com o banco
        try(
            var conexao = ConnectionFactory.conectar();
            //3. Preparar o comando
            PreparedStatement ps = conexao.prepareStatement(sql);
        ){
            //4. Substituir os eventuais placeholder
            ps.setString(1, musica.getTitulo());
            //5. Executar
            ps.execute();
            //6. Fechar os recursos
            //O try já fecha
        }
    }
}