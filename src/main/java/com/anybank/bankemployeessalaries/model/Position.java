package com.anybank.bankemployeessalaries.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Objects;

@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "positions")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    @NotBlank
    @Column
    String name;
    @NotBlank
    @Column(name = "department_id")
    Integer departmentId;
    @NotBlank
    @Column(name = "grade_id")
    Integer gradeId;

    public void setId(Integer id) {
        if (id != null) this.id = id;
    }

    public void setName(String name) {
        if (name != null) this.name = name;
    }

    public void setDepartmentId(Integer departmentId) {
        if (departmentId != null) this.departmentId = departmentId;
    }

    public void setGradeId(Integer gradeId) {
        if (gradeId != null) this.gradeId = gradeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return Objects.equals(id, position.id)
                && Objects.equals(name, position.name)
                && Objects.equals(departmentId, position.departmentId)
                && Objects.equals(gradeId, position.gradeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, departmentId, gradeId);
    }
}
