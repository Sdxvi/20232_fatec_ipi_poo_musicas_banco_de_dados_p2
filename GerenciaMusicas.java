import static javax.swing.JOptionPane.showInputDialog;
import static javax.swing.JOptionPane.showMessageDialog;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import static java.lang.Integer.parseInt;

public class GerenciaMusicas {
    public static void main(String[] args) {
        var musicaDAO = new MusicaDAO();
        int op = -1;
        String menu = "1-Cadastrar música\n2-Avaliar Música\n3-Listar Músicas\n4-Remover Música\n0-Sair";
        do{
            try{
            op = parseInt(showInputDialog(menu));
            switch  (op){
                case 1: {
                    String titulo = showInputDialog("Titulo?");
                    var musica = new Musica(titulo, 0);
                    musicaDAO.cadastrar(musica);
                    showMessageDialog(null, "Música cadastrada!");
                    break;
                }
                case 2: {
                    String titulo = showInputDialog("Titulo");
                    int nota = parseInt(showInputDialog("Nota?"));
                    musicaDAO.avaliar(new Musica(titulo, nota));
                    showMessageDialog(null, "Música Avaliada!");
                    break;
                }
                case 3:{
                    var musicas = musicaDAO.listar();
                    Collections.sort(musicas, new ComparadorPorAvaliacao());
                    for (int i = 0; i < musicas.size(); i++) {
                        showMessageDialog(null, musicas.get(i));
                    }
                    break;
                }
                case 4:{
                    String titulo = showInputDialog("Titulo da Música a ser removida");
                    musicaDAO.remover(new Musica(titulo, 0));
                    showMessageDialog(null, "Música Removida!");
                    break;
                }
                case 0:
                    showMessageDialog(null, "Adeus");
                break;
                default:
                showMessageDialog(null, "Opção Inválida");
                break;
            }
            }
            catch(Exception e){
                e.printStackTrace();
                showMessageDialog(null, "Não Rolou");
            }
            

        }while (op != 0);
    }
}