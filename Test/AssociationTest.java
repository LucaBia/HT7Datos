import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AssociationTest {

    @Test
    public void buscarTest(){
        BinaryTree insancia = new BinaryTree();
        String palabra = "";
        String expected = null;
        String result = insancia.buscar(palabra);
        assertEquals(expected, result);
    }
}
