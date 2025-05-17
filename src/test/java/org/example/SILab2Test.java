package org.example;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

public class SILab2Test {

    @Test
    public void testEveryStatement() {

        // Тест Случај 1: Валидна кошничка без попуст
        List<Item> items1 = List.of(new Item("Milk", 2, 100, 0));
        String cardNumber1 = "1234567890123456";
        assertEquals(200, SILab2.checkCart(items1, cardNumber1));

        // Тест Случај 2: allItems == null
        RuntimeException e = assertThrows(RuntimeException.class, () -> SILab2.checkCart(null, "1234567890123456"));
        assertEquals("allItems list can't be null!", e.getMessage());

        // Тест Случај 3: Item со невалидно име
        List<Item> items3 = List.of(new Item("", 1, 100, 0));
        RuntimeException e3 = assertThrows(RuntimeException.class, () -> SILab2.checkCart(items3, "1234567890123456"));
        assertEquals("Invalid item!", e3.getMessage());

        // Тест Случај 4: sum -= 30 + пресметка со попуст
        List<Item> items4 = List.of(new Item("Expensive", 2, 400, 0.1));
        double expected = -30 + 2 * 400 * 0.9; // -30 + 720 = 690
        assertEquals(expected, SILab2.checkCart(items4, "1234567890123456"));

        // Тест Случај 5: невалиден број на картичка (краток)
        List<Item> items5 = List.of(new Item("Item", 1, 100, 0));
        RuntimeException e5 = assertThrows(RuntimeException.class, () -> SILab2.checkCart(items5, "1234"));
        assertEquals("Invalid card number!", e5.getMessage());

        // Тест Случај 6: невалиден карактер во број на картичка
        RuntimeException e6 = assertThrows(RuntimeException.class, () -> SILab2.checkCart(items5, "123456789012345a"));
        assertEquals("Invalid character in card number!", e6.getMessage());
    }

    @Test
    public void testMultipleCondition() {
        String validCard = "1234567890123456";

        // T1: false || false || false
        Item t1 = new Item("Item1", 5, 200, 0.0);
        assertDoesNotThrow(() -> SILab2.checkCart(List.of(t1), validCard));

        // T2: false || false || true
        Item t2 = new Item("Item2", 11, 200, 0.0);
        assertDoesNotThrow(() -> SILab2.checkCart(List.of(t2), validCard));

        // T3: false || true || false
        Item t3 = new Item("Item3", 5, 200, 0.1);
        assertDoesNotThrow(() -> SILab2.checkCart(List.of(t3), validCard));

        // T4: false || true || true
        Item t4 = new Item("Item4", 15, 200, 0.2);
        assertDoesNotThrow(() -> SILab2.checkCart(List.of(t4), validCard));

        // T5: true || false || false
        Item t5 = new Item("Item5", 5, 350, 0.0);
        assertDoesNotThrow(() -> SILab2.checkCart(List.of(t5), validCard));

        // T6: true || false || true
        Item t6 = new Item("Item6", 12, 400, 0.0);
        assertDoesNotThrow(() -> SILab2.checkCart(List.of(t6), validCard));

        // T7: true || true || false
        Item t7 = new Item("Item7", 5, 350, 0.2);
        assertDoesNotThrow(() -> SILab2.checkCart(List.of(t7), validCard));

        // T8: true || true || true
        Item t8 = new Item("Item8", 15, 350, 0.2);
        assertDoesNotThrow(() -> SILab2.checkCart(List.of(t8), validCard));
    }
}

