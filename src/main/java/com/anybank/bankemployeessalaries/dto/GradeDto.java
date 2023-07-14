package com.anybank.bankemployeessalaries.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.util.Objects;

@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GradeDto {
    int id;
    String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GradeDto grade = (GradeDto) o;
        return id == grade.id && Objects.equals(name, grade.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
