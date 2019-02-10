package lwp.bibliotecas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://www.lawebdelprogramador.com/foros/Java/1683412-Ayuda-con-este-proyecto.html
 * @author billy.johnson
 */
public class PalabrasAleatorias {

    List<String> palabras;

    private static PalabrasAleatorias p = null;

    private PalabrasAleatorias() {
        String s = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed porta cursus orci id pharetra. Praesent laoreet hendrerit lacus sed elementum. Suspendisse potenti. Donec eu ex vel est hendrerit feugiat. Nunc in enim luctus orci venenatis sagittis. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae; Pellentesque ac posuere libero, et mollis dolor. Morbi a lectus justo. In non diam a justo suscipit viverra. Nullam feugiat vestibulum est et mollis. Aenean sit amet posuere elit. Pellentesque malesuada purus venenatis leo gravida, nec congue purus pretium. Vivamus tempor metus in mi imperdiet, et elementum augue iaculis. Ut malesuada mattis lorem, ac pharetra purus porta ac. Quisque turpis lorem, sollicitudin quis lorem ut, hendrerit suscipit est. "
                + "Vestibulum suscipit lectus vel nunc convallis, sit amet dignissim sem malesuada. Nulla rutrum mauris non ipsum efficitur rhoncus. Phasellus convallis orci ante, quis facilisis tortor interdum non. Duis semper nulla eu tellus interdum pulvinar. Donec tristique volutpat mi at rutrum. In tincidunt, sem in faucibus ullamcorper, purus risus sodales nunc, et egestas lorem quam nec nibh. Donec accumsan nisl eu dolor vestibulum tincidunt. Fusce euismod lorem finibus ligula imperdiet vehicula. Suspendisse fermentum est non enim egestas, eget dictum nulla blandit. Mauris aliquet in tortor eu aliquam. Donec non leo eu lorem tincidunt molestie at laoreet sapien. Quisque efficitur augue est, a porttitor magna congue id. Morbi sit amet tellus arcu. Suspendisse potenti. "
                + "Sed sed sapien vitae dolor luctus cursus. Suspendisse elementum interdum consectetur. Nulla aliquet auctor augue id accumsan. In mattis risus in felis tempor, in ullamcorper massa ultricies. Aenean ultricies, risus at ullamcorper volutpat, eros risus viverra arcu, quis condimentum leo quam nec dui. Suspendisse iaculis urna vitae ultricies pulvinar. Mauris libero risus, scelerisque non libero et, facilisis interdum leo. Orci varius natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus. Morbi leo nunc, scelerisque sit amet neque volutpat, pretium molestie ligula. Sed efficitur faucibus lorem, sit amet porta nisl gravida a. Donec a finibus turpis. Duis suscipit magna libero, vitae pulvinar massa scelerisque dignissim. Nunc accumsan diam quam, vel cursus tellus hendrerit nec. Aliquam semper nulla a ipsum facilisis vulputate. "
                + "Maecenas ultrices ante nisl, id dapibus urna elementum euismod. Phasellus interdum lorem ut rutrum iaculis. Suspendisse ullamcorper eros at dolor pellentesque commodo. Vivamus scelerisque aliquet ipsum vitae posuere. Morbi placerat est lorem, non suscipit nulla mollis at. Mauris in facilisis ex, eu pretium nunc. Sed lobortis, ipsum non lobortis faucibus, lectus felis placerat velit, ac porta nisl justo eu tortor. Suspendisse ultricies et tortor eget gravida. Ut aliquam arcu a tortor eleifend faucibus. Sed ultricies lacinia semper. Nam at fermentum urna. Donec lacinia venenatis orci a tempus. "
                + "Donec eget ante at purus vehicula fermentum eu quis erat. Phasellus nec ullamcorper mi. Donec cursus risus ac metus rutrum imperdiet. Quisque in enim nisl. Praesent id dignissim metus, vel commodo lectus. Praesent nec dapibus leo. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia Curae ";
        
        String [] a = s.replace(",", "")
                .replace(".", "")
                .replace(";", "")
                .split(" ");
        palabras = new ArrayList();
        palabras.addAll(Arrays.asList(a));
    }

    public static PalabrasAleatorias getInstance() {
        if (p == null) {
            p = new PalabrasAleatorias();
        }
        return p;
    }
    
    public int maxPalabras(){
        return palabras.size() - 1;
    }
    
    public String getPalabras(int index, int cantidad){
        String s = "";
        for (int i = index; i < index + cantidad; i++) {
            s += palabras.get(i);
        }
        return s;
    }
    
    public String getPalabra(int index){
        return palabras.get(index);
    }
            

}
