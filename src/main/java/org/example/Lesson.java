package org.example;

public class Lesson {
    Professor professor;
    String name;

    public Professor getProfessor() {
        return professor;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Lesson() {
    }

    public Lesson(Professor professor, String name) {
        this.professor = professor;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "professor=" + professor +
                ", name='" + name + '\'' +
                '}';
    }
}
