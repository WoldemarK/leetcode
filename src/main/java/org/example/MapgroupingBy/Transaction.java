package org.example.MapgroupingBy;

import java.math.BigDecimal;
import java.time.LocalDate;

public record Transaction
        (
                long id,
                BigDecimal amount,
                TransactionStatus status,
                LocalDate date
        ) {
}
