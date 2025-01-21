package org.example;

import java.util.stream.IntStream;

/**
 * Вам дан массив prices с ценами prices[i] на акции в день ith
 * Вы хотите максимизировать свою прибыль, выбрав один день для покупки акций и другой день в будущем для их продажи.
 * Верните максимальную прибыль, которую вы можете получить от этой сделки. Если вы не можете получить никакой прибыли, верните 0.
 * Пример 1:
 * Входные данные: цены = [7,1,5,3,6,4]
 * Выходные данные: 5
 * Пояснение: купите на второй день (цена = 1) и продайте на пятый день (цена = 6), прибыль = 6-1 = 5.
 *  Обратите внимание, что покупка на второй день и продажа на первый день недопустимы, потому что вы должны купить до продажи.
 * Пример 2:
 * Ввод: цены = [7,6,4,3,1]
 * Вывод: 0
 * Объяснение: В этом случае сделки не совершаются и максимальная прибыль = 0.
 * Ограничения:
 * 1 <= prices.length <= 105
 * 0 <= prices[i] <= 104
 */
public class MaxProfit {
    public static void main(String[] args) {
        int[] prices1 = {7, 1, 5, 3, 6, 4};
        int[] prices2 = {7, 6, 4, 3, 1};

        System.out.println(maxProfit(prices1));
        System.out.println(maxProfit(prices2));
    }
    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int[] result = IntStream.range(0, prices.length)
                .mapToObj(i -> new int[]{prices[i], 0})
                .reduce(new int[]{Integer.MAX_VALUE, 0}, (acc, priceInfo) -> {

                    acc[0] = Math.min(acc[0], priceInfo[0]);
                    acc[1] = Math.max(acc[1], priceInfo[0] - acc[0]);
                    return acc;
                });

        return result[1];
    }
}