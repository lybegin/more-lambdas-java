package com.github.phantomthief.util;

import static com.github.phantomthief.util.MoreSuppliers.lazy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

import com.github.phantomthief.util.MoreSuppliers.CloseableSupplier;

/**
 * @author w.vela
 * Created on 2017-02-22.
 */
class MoreSuppliersTest {

    @Test
    void test() {
        int[] counter = { 0 };
        CloseableSupplier<String> supplier = lazy(() -> {
            counter[0]++;
            return "test";
        });
        assertEquals(supplier.get(), "test");
        assertEquals(counter[0], 1);
        supplier.tryClose();
        assertEquals(supplier.get(), "test");
        assertEquals(counter[0], 2);
    }

    @Test
    void tesNull() {
        int[] counter = { 0 };
        CloseableSupplier<String> supplier = lazy(() -> {
            counter[0]++;
            return null;
        });
        assertNull(supplier.get());
        assertNull(supplier.get());
        assertEquals(counter[0], 1);
        supplier.tryClose();
        assertNull(supplier.get());
        assertNull(supplier.get());
        assertEquals(counter[0], 2);
    }
}