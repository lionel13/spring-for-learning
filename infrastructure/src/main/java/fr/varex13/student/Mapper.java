package fr.varex13.student;

public interface Mapper<U, T> {

    U map(T objectToMap);
}
