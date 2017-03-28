package my.luxoft.assignment.second;

import java.util.Collection;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * We have a class : class Element { int num; String name; int age; }
 * please implement java method taking Collection<Element> as an argument and returning Collection<Element>
 * where all elements are unique by "num" and age>20. In case of ambiguity, put in target collection any of input elements.
 *
 * @author Anton Borovyk
 */
public class Element {
    int num;
    String name;
    int age;

    public Element(int num, String name, int age) {
        this.num = num;
        this.name = name;
        this.age = age;
    }

    public int getNum() {
        return num;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Element element = (Element) o;

        if (num != element.num) return false;
        if (age != element.age) return false;
        return name != null ? name.equals(element.name) : element.name == null;
    }

    @Override
    public int hashCode() {
        int result = num;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + age;
        return result;
    }

    @Override
    public String toString() {
        return "Element{" +
                "num=" + num +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public static Collection<Element> filterElements(Collection<Element> elements) {
        return elements.stream()
                .filter( e -> e.age > 20)
                .collect(Collectors.toMap(Element::getNum, el -> el, (e1, e2) -> e1)).values();
    }
}
