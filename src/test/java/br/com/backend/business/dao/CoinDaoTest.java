package br.com.backend.business.dao;

import br.com.backend.business.model.Coin;
import javax.inject.Inject;
import org.jboss.arquillian.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 *
 * @author Marcelo Soares <marcelo.suares@yahoo.com.br>
 */
@RunWith(Arquillian.class)
public class CoinDaoTest {

    @Deployment
    public static JavaArchive createDeployment() {
        return ShrinkWrap.create(JavaArchive.class, "test.jar")

                .addClasses(CoinDao.class, Coin.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml")
                .addAsManifestResource("META-INF/persistence.xml", "persistence.xml");
    }

    @Inject
    private CoinDao coinDao;

    @Test
    public void findByCodeTest() throws Exception {

        Coin coin = coinDao.findByCode("USD");
        System.out.println("coin: " + coin);
    }
}
