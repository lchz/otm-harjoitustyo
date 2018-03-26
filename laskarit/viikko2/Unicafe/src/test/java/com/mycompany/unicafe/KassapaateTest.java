package com.mycompany.unicafe;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class KassapaateTest {
    Kassapaate paate;
    Maksukortti kortti;
    
    public KassapaateTest() {
    }
    
    
    @Before
    public void setUp() {
        paate = new Kassapaate();
        kortti = new Maksukortti(300);
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
    
    @Test
    public void luodunKassapaatteenRahamaaraOnOikea() {
        assertEquals(1000, paate.kassassaRahaa());
    }
    
    @Test
    public void luodunKassapaatteenMyytyjenLounaidenMaara() {
        assertEquals(0, paate.maukkaitaLounaitaMyyty() + paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void syoEduRiittavastaKateisestaOikeaVaihtoraha() {
        
        assertEquals(60, paate.syoEdullisesti(300));
    }
    
    @Test
    public void syoEduRiittavaKateinenKasvattaaKassaRahaa() {
        
        assertEquals(1240, paate.kassassaRahaa() + 240);
    }
    
    @Test
    public void edullisiaMyytyjenMaara() {
        paate.syoEdullisesti(300);
        paate.syoEdullisesti(1000);
        paate.syoEdullisesti(490);
        
        assertEquals(3, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void syoMauRiittavastakateisestaOikeaVaihtoRaha() {
        assertEquals(100, paate.syoMaukkaasti(500));
    }
    
    @Test
    public void syoMauRiittavaKateinenKasvattaaKassaRahaa() {
        assertEquals(1400, paate.kassassaRahaa() + 400);
    }
    
    @Test 
    public void maukkaitaMyytyjenMaara() {
        paate.syoMaukkaasti(400);
        paate.syoMaukkaasti(500);
        
        assertEquals(2, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void syoEdukateistaEiRiitaOikeaVaihtoraha() {
        assertEquals(100, paate.syoEdullisesti(100));
    }
    
    @Test
    public void syoEduKateistaEiRiitaKassarahaEiMuutu() {
        assertEquals(1000, paate.kassassaRahaa());
    }
    
    @Test 
    public void KateistaEiRiitaEdullisiaEiKasva() {
        paate.syoEdullisesti(300);
        paate.syoEdullisesti(500);
        
        paate.syoEdullisesti(10);
        
        assertEquals(2, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void syoMaukateistaEiRiitaOikeaVaihtoraha() {
        assertEquals(200, paate.syoMaukkaasti(200));
    }
    
    @Test 
    public void syoMauKateistaEiRiitaKassarahaEiMuutu() {
        assertEquals(390, paate.syoMaukkaasti(390));
    }
    
    @Test 
    public void KateistaEiRiitaMaukkaitaEiKasva() {
        paate.syoMaukkaasti(800);
        paate.syoMaukkaasti(7000);
        
        paate.syoMaukkaasti(20);
        
        assertEquals(2, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void syoEduKortillaRiittavaSaldo() {
        assertTrue(paate.syoEdullisesti(kortti) == true);
    }
    
    @Test
    public void syoEduOttaaRiittavaRahaSaldosta() {
        paate.syoEdullisesti(kortti);
        assertEquals(60, kortti.saldo());
    }
    
    @Test
    public void syoEduKortillaMyytyjaKasvaa() {
        paate.syoEdullisesti(kortti);
        assertEquals(1, paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void syoMauKortillaRiittavaSaldo() {
        kortti.lataaRahaa(200);
        assertTrue(paate.syoMaukkaasti(kortti) == true);
    }
    
    @Test
    public void syoMauVeloitaKoriltaOikeanRahan() {
        kortti.lataaRahaa(1000);
        paate.syoMaukkaasti(kortti);
        paate.syoMaukkaasti(kortti);
        
        assertEquals(500, kortti.saldo());
    }
    
    @Test
    public void syoMauKorillaMyytyjaKasvaa() {
        kortti.lataaRahaa(1000);
        paate.syoMaukkaasti(kortti);
        paate.syoMaukkaasti(kortti);
        
        assertEquals(2, paate.maukkaitaLounaitaMyyty());
    }
    
    @Test
    public void KortillaEiRiittavaSaldoaEdullisille() {
        paate.syoEdullisesti(kortti);
        
        assertTrue(paate.syoEdullisesti(kortti) == false);
    }
    
    @Test
    public void KortillaEiRiittavaaSaldoaEdullisilleSaldoEiMuutu() {
        paate.syoEdullisesti(kortti);
        paate.syoEdullisesti(kortti);
        
        assertEquals(60, kortti.saldo());
    }
    
    @Test
    public void KortillaEiRiittavaaSaldoaMaukkailleSaldoEiMuutu() {
        kortti.lataaRahaa(200);
        paate.syoMaukkaasti(kortti);
        paate.syoMaukkaasti(kortti);
        
        assertEquals(100, kortti.saldo());
    }
    
    @Test
    public void kortillaEiRiittavaaSaldoaMyytyjaEiMuutu() {
        paate.syoEdullisesti(kortti);
        paate.syoMaukkaasti(kortti);
        paate.syoEdullisesti(kortti);
        
        assertEquals(1, paate.maukkaitaLounaitaMyyty() + paate.edullisiaLounaitaMyyty());
    }
    
    @Test
    public void kortillaEiRiittavaaSaldoaEdullisille() {
        paate.syoEdullisesti(kortti);
        
        assertTrue(paate.syoEdullisesti(kortti) == false);
    }
    
    @Test 
    public void kortillaEiRiittavaaSaldoaMaukkaille() {
        assertTrue(paate.syoMaukkaasti(kortti) == false);
    }
    
    @Test
    public void KassarahaEiMuutuKortillaOstettaessa() {
        kortti.lataaRahaa(500);
        paate.syoEdullisesti(kortti);
        paate.syoMaukkaasti(kortti);
        
        assertEquals(1000, paate.kassassaRahaa());
    }
    
    @Test
    public void EiNegatiivistaSummaaRahanLataamiselle() {
        paate.lataaRahaaKortille(kortti, -10);
        assertEquals(300 ,kortti.saldo());
    }
    
    @Test 
    public void kortilleLadattaessaKortinSaldoKasvaa() {
        paate.lataaRahaaKortille(kortti, 200);
        paate.lataaRahaaKortille(kortti, 30);
        assertEquals(530, kortti.saldo());
    }
    
    @Test
    public void kortilleLadattaessaKassarahaKasvaa() {
        paate.lataaRahaaKortille(kortti, 400);
        paate.lataaRahaaKortille(kortti, 800);
        
        assertEquals(2200, paate.kassassaRahaa());
    }
}
