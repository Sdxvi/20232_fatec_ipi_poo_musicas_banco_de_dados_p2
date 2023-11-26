import java.util.Comparator;
public class ComparadorPorAvaliacao implements Comparator <Musica> {
  @Override
  public int compare(Musica m1, Musica m2) {
    return m2.getAvaliacao() - m1.getAvaliacao();
  } 
}
