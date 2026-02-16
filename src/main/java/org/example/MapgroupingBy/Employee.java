package org.example.MapgroupingBy;

public record Employee
        (
                String name,
                Department department,
                PositionLevel positionLevel,
                double salary
        ) {
}
