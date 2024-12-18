package tests;

import io.restassured.response.Response;
import org.example.config.ResponseValidator;
import org.example.dto.respose.ProductsResponse;
import org.example.service.RestAssuredClient;
import org.example.utils.JsonConvert;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertTrue;

public class ProductsTest extends BaseTest {

    RestAssuredClient client = new RestAssuredClient();

    @Test
    public void ProductsList_Ok() {

        Response products = client.get("/productsList");

        reports.infoStep("Se valida el status devuelto en responseCode.");
        assertThat(products.jsonPath().getInt("responseCode"), equalTo(200));

        reports.infoStep("Se valida que la lista no este vacia.");
        ResponseValidator.matchersGreaterThan(products, "products.size()");

        reports.infoStep("Se valida todo los productos tengan el nodo id.");
        ResponseValidator.matchersEveryItem(products, "products.id");

        reports.infoStep("Se valida todo los productos tengan el nodo name.");
        ResponseValidator.matchersEveryItem(products, "products.name");

        reports.infoStep("Se valida todo los productos tengan el nodo price.");
        ResponseValidator.matchersEveryItem(products, "products.price");

        reports.infoStep("Se valida todo los productos tengan el nodo category.");
        ResponseValidator.matchersEveryItem(products, "products.category");

        reports.infoStep("Se valida todo los productos tengan el nodo usertype.");
        ResponseValidator.matchersEveryItem(products, "products.category.usertype.usertype");
    }

    @Test
    public void SearchProduct() {
        Map<String, String> formData = new HashMap<>();

        reports.infoStep("Se inicializa los parametros del usuario.");
        formData.put("search_product", "top");

        ProductsResponse productsResponse = client.post("/searchProduct", formData, ProductsResponse.class);

        reports.infoStep("Se valida el status devuelto en responseCode.");
        assertThat(productsResponse.getResponseCode(), equalTo(200));

        reports.infoStep("Se valida que la lista no este vacia.");
        assertThat(productsResponse.getProducts().size(), greaterThan(0));

        reports.infoStep("Se valida que el nodo id sea mayor a 0");
        assertTrue(productsResponse.getProducts().stream().allMatch(product -> product.getId() > 0));

        reports.infoStep("Se valida que el nodo name no sea null");
        assertTrue(productsResponse.getProducts().stream().allMatch(product -> product.getName() != null));

        reports.infoStep("Se valida que el nodo price no sea null");
        assertTrue(productsResponse.getProducts().stream().allMatch(product -> product.getPrice() != null));

        reports.infoStep("Se valida que el nodo category no sea null");
        assertTrue(productsResponse.getProducts().stream().allMatch(product -> product.getCategory() != null));

        reports.infoStep("Se valida que el nodo usertype no sea null");
        assertTrue(productsResponse.getProducts().stream().allMatch(product -> product.getCategory().getUsertype() != null));

        reports.infoStep("Se valida que todos los productos coincidan con la busqueda.");
        productsResponse.getProducts().stream().forEach(product -> {
            assertThat(String.format("Producto: %s, Categoria: %s", product.getName(), product.getCategory()), containsStringIgnoringCase(formData.get("search_product")));
        });
    }
}