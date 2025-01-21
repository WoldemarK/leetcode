package org.example;

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
        int minPrice = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int price : prices) {
            if (price < minPrice) {
                minPrice = price;
            } else if (price - minPrice > maxProfit) {
                maxProfit = price - minPrice;
            }
        }
        return maxProfit;
    }
}