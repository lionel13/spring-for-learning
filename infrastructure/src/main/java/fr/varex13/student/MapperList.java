package fr.varex13.student;

import java.util.List;

public interface MapperList<U, T> {

    List<U> map(List<T> objectToMap);
}
