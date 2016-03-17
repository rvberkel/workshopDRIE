package daos;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AccountDaoTest.class, ArtikelDaoTest.class, BestellingDaoTest.class, BestellingHasArtikelDaoTest.class,
		BetaalwijzeDaoTest.class, BetalingDaoTest.class, FactuurDaoTest.class, KlantDaoTest.class })
public class AllDaoTests {
}
