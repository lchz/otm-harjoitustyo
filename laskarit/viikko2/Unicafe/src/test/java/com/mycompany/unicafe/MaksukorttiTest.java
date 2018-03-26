package com.mycompany.unicafe;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MaksukorttiTest {

    Maksukortti kortti;

    @Before
    public void setUp() {
        kortti = new Maksukortti(10);
    }

    @Test
    public void luotuKorttiOlemassa() {
        assertTrue(kortti!=null);   
    }
    
    @Test
    public void saldoOikeinAlussa() {
        assertEquals("saldo: 0.10", kortti.toString());
    }
    
    @Test
    public void rahanSummaOikeinLataamisenJalkeen() {
        kortti.lataaRahaa(100);
        
        assertEquals("saldo: 1.10", kortti.toString());
    }
    
    @Test
    public void rahanOttaminenTarpeellisestaSaldosta() {
        kortti.lataaRahaa(100);
        boolean riittava = kortti.otaRahaa(100);
        
        assertEquals("saldo: 0.10", kortti.toString());
        assertTrue(riittava == true);
    }
    
    @Test
    public void rahanOttaminenPuuttuvastaSaldosta() {
        boolean riittava = kortti.otaRahaa(100);
        
        assertEquals("saldo: 0.10", kortti.toString());
        assertTrue(riittava == false);
    }
    
    @Test
    public void saldoAlussaOikein() {
        
        assertEquals(10, kortti.saldo());
    }
}
