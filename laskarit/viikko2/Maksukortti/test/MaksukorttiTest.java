import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class MaksukorttiTest {
    Maksukortti kortti;
    
    public MaksukorttiTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void hello() {}
     
     @Test
     public void konstruktoriAsettaaSaldonOikein() {
         
         assertEquals("Kortilla on rahaa 10.0 euroa", kortti.toString());
     }
     
     @Test
     public void syoEdullisestiVahentaaSaldoaOikein() {
         kortti.syoEdullisesti();
         assertEquals("Kortilla on rahaa 7.5 euroa", kortti.toString());
     }
     
     @Test
     public void syoMaukkaastiVahentaaSaldoaOikein() {
         kortti.syoMaukkaasti();
         assertEquals("Kortilla on rahaa 6.0 euroa", kortti.toString());
     }
     
     @Test
     public void syoEdullisestiEiVieSaldoaNegatiiviseksi() {
         
         kortti.syoMaukkaasti();
         kortti.syoMaukkaasti();
         
         kortti.syoEdullisesti();
         
         assertEquals("Kortilla on rahaa 2.0 euroa", kortti.toString());
         
     }
     
     @Test
     public void kortilleVoiLadataRahaa() {
         kortti.lataaRahaa(25);
         assertEquals("Kortilla on rahaa 35.0 euroa", kortti.toString());
     }
     
     @Test
     public void kortinSaldoEiYlitaMaksimiarvoa() {
         kortti.lataaRahaa(200);
         assertEquals("Kortilla on rahaa 150.0 euroa", kortti.toString());
     }
     
     @Test
     public void syoMaukkaastiEiVieSaldoaNegatiiviseksi() {
         kortti.syoEdullisesti();
         kortti.syoEdullisesti();
         kortti.syoEdullisesti();
         
         kortti.syoMaukkaasti();
         
         assertEquals("Kortilla on rahaa 2.5 euroa", kortti.toString());
     }
     
     @Test
     public void EiVoiLadataNegatiivistaArvoa() {
         kortti.lataaRahaa(-2.0);
         
         assertEquals("Kortilla on rahaa 10.0 euroa", kortti.toString());
     }
     
     @Test
     public void pystyOstamaanEdullisenSenVerranRahalla() {
         for(int i = 0; i < 4; i++) {
             kortti.syoEdullisesti();
         }
         
         assertEquals("Kortilla on rahaa 0.0 euroa", kortti.toString());
         
     }
     
     @Test
     public void pystyOstamaanMaukkaanSenVerranRahalla() {
         kortti.syoMaukkaasti();
         kortti.lataaRahaa(0.5);
         
         kortti.syoEdullisesti();
         
         kortti.syoMaukkaasti();
         
         assertEquals("Kortilla on rahaa 0.0 euroa", kortti.toString());
     }
}
