package com.example;

import org.junit.Rule;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.List;

import static org.junit.Assert.*;


@RunWith(Enclosed.class)
public class LionTest {

    @RunWith(Parameterized.class)
    public static class ParameterizedTest {

        @Rule public final MockitoRule rule = MockitoJUnit.rule();
        @Mock private Feline felineMock;

        @Parameterized.Parameter()
        public String sex;

        @Parameterized.Parameter(1)
        public boolean doesLionHasMane;

        @Parameterized.Parameters(name="{index}: lion({0}, feline).doesHasMane() ->{1}")
        public static Object[][] lionSexData() {
            return new Object[][] {
                    {"Самец", true},
                    {"Самка", false}
            };
        }

        @Test
        public void lionHasManeTest() throws Exception {
            Lion lion = new Lion(sex, felineMock);
            assertEquals(doesLionHasMane, lion.doesHaveMane());
        }
    }

    public static class NotParameterizedTest {

        @Rule public final MockitoRule rule = MockitoJUnit.rule();
        @Mock private Feline felineMock;

        private final String lionSex = "Самец";

        @Test
        public void lionGetKittensReturnsExactOneKitten() throws Exception {
            Lion lion = new Lion(lionSex, felineMock);
            Mockito.when(felineMock.getKittens()).thenReturn(1);
            int actualKittensCount = lion.getKittens();
            int expectedKittensCount = 1;
            Mockito.verify(felineMock).getKittens();
            assertEquals(expectedKittensCount, actualKittensCount);
        }

        @Test
        public void unknownSexLionThrowsException() {
            assertThrows(Exception.class, () -> new Lion("foo", felineMock));
        }

        @Test
        public void lionGetFoodCallsFelineGetFood() throws Exception {
            Lion lion = new Lion(lionSex, felineMock);
            Mockito.when(felineMock.getFood("Хищник")).thenReturn(List.of("Животные", "Птицы", "Рыба"));
            List<String> actualFood = lion.getFood();
            List<String> expectedFood = List.of("Животные", "Птицы", "Рыба");
            Mockito.verify(felineMock).getFood("Хищник");
            assertEquals(actualFood, expectedFood);
        }
    }
}