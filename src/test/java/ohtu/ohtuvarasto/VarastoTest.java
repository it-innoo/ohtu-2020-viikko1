package ohtu.ohtuvarasto;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class VarastoTest {

    Varasto varasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
    }

    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriLuoNollaVaraston() {
        final Varasto v = new Varasto(-1);
        assertEquals(0, v.getTilavuus(), vertailuTarkkuus);
        assertEquals(0, v.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriLuoNollaSaldoVaraston() {
        final Varasto v = new Varasto(-1, 0);
        assertEquals(0, v.getTilavuus(), vertailuTarkkuus);
        assertEquals(0, v.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void konstruktoriLuoVaraston() {
        final int expected = 1;
        Varasto v = new Varasto(expected, 0);
        assertEquals(expected, v.getTilavuus(), vertailuTarkkuus);
        assertEquals(0, v.getSaldo(), vertailuTarkkuus);

        v = new Varasto(expected, -expected);
        assertEquals(expected, v.getTilavuus(), vertailuTarkkuus);
        assertEquals(0, v.getSaldo(), vertailuTarkkuus);

        v = new Varasto(expected, 10);
        assertEquals(expected, v.getTilavuus(), vertailuTarkkuus);
        assertEquals(expected, v.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void negatiivinenLisays() {
        varasto.lisaaVarastoon(-10);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysEiLisaaSaldoa() {
        varasto.lisaaVarastoon(100);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        final double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void annetaanMitaVoidaan() {
        varasto.lisaaVarastoon(8);
        final double saatuMaara = varasto.otaVarastosta(20);

        assertEquals(8, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void negatiivinenOttaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        final double saatuMaara = varasto.otaVarastosta(-20);

        assertEquals(0, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void tulostusOikein() {
        // "saldo = " + saldo + ", vielä tilaa "
        String expected = "saldo = 0, vielä tilaa 10.0";
        String s = varasto.toString();

        assertEquals(expected, s);
    }
}