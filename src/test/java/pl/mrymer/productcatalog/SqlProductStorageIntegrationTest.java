package pl.mrymer.productcatalog;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class SqlProductStorageIntegrationTest {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setupDb() {
        jdbcTemplate.execute("DROP TABLE product_catalog if EXISTS");
        jdbcTemplate.execute("CREATE TABLE product_catalog (" +
                "`id` varchar(100) NOT NULL," +
                "`description` varchar(255)," +
                "PRIMARY KEY(id)" +
                ");");
    }

    @Test
    void initialProductCountIs0() {
        Integer result = jdbcTemplate.queryForObject(
                "select count(*) from product_catalog",
                Integer.class
        );

        assertEquals(0, result);
    }

    @Test
    void itAllowsToQueryMyDb() {
        String result = jdbcTemplate.queryForObject(
                "select now()",
                String.class
        );
    }


    void itSotreAndLoadProduct() {
        ProductStorage sqlProductStorage = new SqlProductStorage();
        ProductData sampleProduct = thereIsSampleProductData();

        sqlProductStorage.save(sampleProduct);

        ProductData loaded = sqlProductStorage.loadById(sampleProduct.getId());

        assertEquals(sampleProduct.getName(), loaded.getName());
    }

    private ProductData thereIsSampleProductData() {
        return new ProductData("lego-1", "nice one");
    }
}
