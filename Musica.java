import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
//@Setter
@RequiredArgsConstructor        //Monta um construtor para todos aquele atributos marcados como FINAL
public class Musica {
    private int codigo;
    private final String titulo;
    private final int avaliacao;
    private int ativo = 1;

    @Override
    public String toString() {
        return String.format(
            "A música se chama %s. A sua nota é %d",
            titulo, avaliacao
        );
    }
}
